package org.vaadin.quagga;

import com.vaadin.annotations.JavaScript;
import com.vaadin.shared.Registration;
import com.vaadin.shared.communication.SharedState;
import com.vaadin.ui.AbstractJavaScriptComponent;

import org.vaadin.quagga.client.QuaggaClientRpc;
import org.vaadin.quagga.client.QuaggaServerRpc;
import org.vaadin.quagga.client.QuaggaState;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JavaScript({"quagga.min.js", "quagga.connector.js"})
public class Quagga extends AbstractJavaScriptComponent {

    private final QuaggaClientRpc clientRpc = getRpcProxy(QuaggaClientRpc.class);

    public interface BarcodeScanHandler{
        void onScanCompleted(String code, String format);
    }

    private final List<BarcodeScanHandler> scanHandlers = new ArrayList<>();

    public Quagga() {
        registerRpc((QuaggaServerRpc) (code, format) -> scanHandlers.forEach(scanHandler -> scanHandler.onScanCompleted(code, format)));
    }

    public Quagga(String[] readers){
        this();
        getState().readers = readers;
    }

    public Registration addScanHandler(BarcodeScanHandler handler){
        Objects.requireNonNull(handler);

        scanHandlers.add(handler);

        return () -> scanHandlers.remove(handler);
    }

    public void scan(){
        clientRpc.start();
    }

    public void stopScanning(){
        clientRpc.stop();
    }

    @Override
    public Class<? extends SharedState> getStateType() {
        return QuaggaState.class;
    }

    @Override
    protected QuaggaState getState() {
        return (QuaggaState)super.getState();
    }
}
