package com.dummyc0m.forgemod.amethyst.common.energy;

import com.dummyc0m.forgemod.amethyst.api.energy.INetwork;
import com.dummyc0m.forgemod.amethyst.api.energy.INetworkConnector;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dummyc0m on 1/10/2016.
 */
public class NetManager {
    public static final NetManager INSTANCE = new NetManager();

    private Map<String, INetwork> networkMap = new HashMap<>();

    public void addToNetwork(INetworkConnector.IConnectorProducer producer) {
        INetwork network = networkMap.get(producer.getNetworkId());
        if (network == null) {
            network = new ChargeNetwork(producer.getNetworkId(), producer.getWorld());
            networkMap.put(producer.getNetworkId(), network);
        }
        network.addConnector(producer);
        //DEBUG
        System.out.println("Added Producer Connector to " + network.getNetworkId());
    }


    public void addToNetwork(INetworkConnector.IConnectorConsumer consumer) {
        INetwork network = networkMap.get(consumer.getNetworkId());
        if (network == null) {
            network = new ChargeNetwork(consumer.getNetworkId(), consumer.getWorld());
            networkMap.put(consumer.getNetworkId(), network);
        }
        network.addConnector(consumer);
        //DEBUG
        System.out.println("Added Consumer Connector to " + network.getNetworkId());
    }

    public void removeFromNetwork(INetworkConnector.IConnectorProducer producer) {
        networkMap.get(producer.getNetworkId()).removeProducerConnector(producer);
    }

    public void removeFromNetwork(INetworkConnector.IConnectorConsumer consumer) {
        networkMap.get(consumer.getNetworkId()).removeConsumerConnector(consumer);
    }

    public void updateNetworks() {
        for (Map.Entry<String, INetwork> entry : networkMap.entrySet()) {
            if (entry.getValue().update()) {
                networkMap.remove(entry.getKey());
            }
        }
    }
}
