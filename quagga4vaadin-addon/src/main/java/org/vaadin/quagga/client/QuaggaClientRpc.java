package org.vaadin.quagga.client;

import com.vaadin.shared.communication.ClientRpc;

public interface QuaggaClientRpc extends ClientRpc {

    void start();
    void stop();

}