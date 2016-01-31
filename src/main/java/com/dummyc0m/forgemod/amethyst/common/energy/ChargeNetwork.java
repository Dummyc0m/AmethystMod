package com.dummyc0m.forgemod.amethyst.common.energy;

import com.dummyc0m.forgemod.amethyst.api.energy.INetwork;
import com.dummyc0m.forgemod.amethyst.api.energy.INetworkConnector;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dummyc0m on 1/10/2016.
 */
public class ChargeNetwork implements INetwork {
    private String networkId;
    private World world;
    private List<INetworkConnector.IConnectorProducer> producers = new ArrayList<>();
    private List<INetworkConnector.IConnectorConsumer> consumers = new ArrayList<>();
    private boolean dirty;

    public ChargeNetwork(String networkId, World world) {
        this.networkId = networkId;
        this.world = world;
    }

    @Override
    public boolean update() {
        int chargeProduced = 0;
        for (INetworkConnector.IConnectorProducer producer : producers) {
            chargeProduced += producer.produceCharge();
        }

        int chargeConsuming = 0;
        for (INetworkConnector.IConnectorConsumer consumer : consumers) {
            chargeConsuming += consumer.getConsumption();
        }

        float sufficiency = 1;
        if (chargeConsuming > chargeProduced) {
            sufficiency = (float) chargeProduced / chargeConsuming;
        }

        for (INetworkConnector.IConnectorConsumer consumer : consumers) {
            consumer.consumeCharge((int) (sufficiency * consumer.getConsumption()));
        }

        return producers.isEmpty() && consumers.isEmpty();
    }

    @Override
    public World getWorld() {
        return world;
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public void addConnector(INetworkConnector.IConnectorProducer connector) {
        producers.add(connector);
    }

    @Override
    public void addConnector(INetworkConnector.IConnectorConsumer connector) {
        consumers.add(connector);
    }

    @Override
    public void removeProducerConnector(BlockPos blockPos) {
        Iterator<INetworkConnector.IConnectorProducer> it = producers.iterator();
        while (it.hasNext()) {
            if (it.next().getPos().equals(blockPos)) {
                it.remove();
            }
        }
    }

    @Override
    public void removeConsumerConnector(BlockPos blockPos) {
        Iterator<INetworkConnector.IConnectorConsumer> it = consumers.iterator();
        while (it.hasNext()) {
            if (it.next().getPos().equals(blockPos)) {
                it.remove();
            }
        }
    }

    @Override
    public void removeProducerConnector(INetworkConnector.IConnectorProducer connector) {
        producers.remove(connector);
    }

    @Override
    public void removeConsumerConnector(INetworkConnector.IConnectorConsumer connector) {
        consumers.remove(connector);
    }

    @Override
    public void setDirty() {
        dirty = true;
    }
}
