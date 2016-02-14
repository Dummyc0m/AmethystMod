package com.dummyc0m.forgemod.amethyst.core.energy;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dummyc0m on 1/10/2016.
 */
public class NetManager {
    public static final NetManager INSTANCE = new NetManager();

    private Map<String, INetwork> networkMap = new HashMap<>();

    public boolean addToNetwork(INetworkConnector.IConnectorProducer producer) {
        INetwork network = networkMap.get(producer.getNetworkId());
        if (network == null) {
            network = new ChargeNetwork(producer.getNetworkId(), producer.getWorld());
            networkMap.put(producer.getNetworkId(), network);
        }
        System.out.println("Adding Producer Connector to " + network.getNetworkId());
        return network.addConnector(producer);
        //DEBUG
    }

    public boolean addToNetwork(INetworkConnector.IConnectorConsumer consumer) {
        INetwork network = networkMap.get(consumer.getNetworkId());
        if (network == null) {
            network = new ChargeNetwork(consumer.getNetworkId(), consumer.getWorld());
            networkMap.put(consumer.getNetworkId(), network);
        }
        System.out.println("Adding Consumer Connector to " + network.getNetworkId());
        return network.addConnector(consumer);
        //DEBUG
    }

    public boolean addToNetwork(INetworkConnector connector) {
        if (connector instanceof INetworkConnector.IConnectorProducer) {
            return addToNetwork((INetworkConnector.IConnectorProducer) connector);
        } else {
            return addToNetwork((INetworkConnector.IConnectorConsumer) connector);
        }
    }

    public void removeFromNetwork(INetworkConnector.IConnectorProducer producer) {
        networkMap.get(producer.getNetworkId()).removeProducerConnector(producer);
    }

    public void removeFromNetwork(INetworkConnector.IConnectorConsumer consumer) {
        networkMap.get(consumer.getNetworkId()).removeConsumerConnector(consumer);
    }

    public void updateNetworks() {
        Iterator<Map.Entry<String, INetwork>> iterator = networkMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, INetwork> entry = iterator.next();
            if (entry.getValue().update()) {
                iterator.remove();
            }
        }
    }
}
