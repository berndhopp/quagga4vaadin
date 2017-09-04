package org.vaadin.quagga.client;

import com.vaadin.shared.communication.ServerRpc;

public interface QuaggaServerRpc extends ServerRpc {
    void onDetected(String code, String format);
}
