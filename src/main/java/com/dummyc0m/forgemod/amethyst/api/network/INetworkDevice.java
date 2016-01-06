package com.dummyc0m.forgemod.amethyst.api.network;

import net.minecraft.util.EnumFacing;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public interface INetworkDevice {
    void connectTo(EnumFacing facing);

    int produceCharge();

    int consumeCharge(int charge);
}
