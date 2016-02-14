package com.dummyc0m.forgemod.amethyst.common.tile;

import com.dummyc0m.forgemod.amethyst.core.energy.INetworkDevice;
import com.dummyc0m.forgemod.amethyst.core.tile.TileEntityBase;
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
        System.out.println("Generator Producing charge of " + 80);
        markDirty();
        return 80;
    }

    @Override
    public void consumeCharge(int charge) {
    }
}
