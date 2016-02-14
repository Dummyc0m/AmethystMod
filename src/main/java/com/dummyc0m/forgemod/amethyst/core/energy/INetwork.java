package com.dummyc0m.forgemod.amethyst.core.energy;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Dummyc0m on 1/2/16.
 */
public interface INetwork {
    boolean update();

    World getWorld();

    String getNetworkId();

    boolean addConnector(INetworkConnector.IConnectorProducer connector);

    boolean addConnector(INetworkConnector.IConnectorConsumer connector);

    void removeProducerConnector(BlockPos blockPos);

    void removeConsumerConnector(BlockPos blockPos);

    void removeProducerConnector(INetworkConnector.IConnectorProducer connector);

    void removeConsumerConnector(INetworkConnector.IConnectorConsumer connector);

    void setDirty();
}
