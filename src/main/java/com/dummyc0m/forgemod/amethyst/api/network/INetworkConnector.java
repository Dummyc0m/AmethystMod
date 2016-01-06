package com.dummyc0m.forgemod.amethyst.api.network;

import java.util.UUID;

/**
 * Created by Dummyc0m on 1/2/16.
 */
public interface INetworkConnector {
    INetworkDevice getConnectedDevice();

    UUID getParentNetworkUUID();

    interface IConnectorProducer extends INetworkConnector {
        int produceCharge();
    }

    interface IConnectorConsumer extends INetworkConnector {
        int consumeCharge();
    }
}
