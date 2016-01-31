package com.dummyc0m.forgemod.amethyst.common.tile;

import com.dummyc0m.forgemod.amethyst.api.energy.INetworkDevice;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public class TileTestGenerator extends TileEntityBase implements INetworkDevice {
    @Override
    public void readCustomNBT(NBTTagCompound compound) {

    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {

    }

    @Override
    public int getConsumption() {
        return 0;
    }

    @Override
    public int produceCharge() {
        System.out.println("Producing charge of " + 80);
        return 80;
    }

    @Override
    public void consumeCharge(int charge) {
    }
}
