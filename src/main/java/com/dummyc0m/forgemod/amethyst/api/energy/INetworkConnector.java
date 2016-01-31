package com.dummyc0m.forgemod.amethyst.api.energy;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

/**
 * Created by Dummyc0m on 1/2/16.
 */
public interface INetworkConnector {
    BlockPos getPos();

    BlockPos getConnectedPos();

    World getWorld();

    String getNetworkId();

    void setNetworkId(String uuid);

    interface IConnectorProducer extends INetworkConnector {
        int produceCharge();
    }

    interface IConnectorConsumer extends INetworkConnector {
        void consumeCharge(int charge);

        int getConsumption();
    }

    enum ConnectorPriority {
        HIGH, NORMAL, LOW
    }
}
