package com.dummyc0m.forgemod.amethyst.api.energy;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public interface INetworkDevice {
    int getConsumption();

    int produceCharge();

    void consumeCharge(int charge);
}
