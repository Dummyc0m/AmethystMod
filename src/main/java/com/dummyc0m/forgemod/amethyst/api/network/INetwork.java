package com.dummyc0m.forgemod.amethyst.api.network;

import net.minecraft.util.BlockPos;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dummyc0m on 1/2/16.
 */
public interface INetwork {
    void update();

    List<ChunkCoordIntPair> getChunkCoords();

    World getWorld();

    UUID getNetworkUUID();

    void addProducerConnector(INetworkConnector.IConnectorProducer connector);

    void addConsumerConnector(INetworkConnector.IConnectorConsumer connector);

    void removeConnector(BlockPos blockPos);
}
