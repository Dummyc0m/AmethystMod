package com.dummyc0m.forgemod.amethyst.common.tile;

import com.dummyc0m.forgemod.amethyst.core.energy.INetworkDevice;
import com.dummyc0m.forgemod.amethyst.core.tile.TileEntityBase;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public class TileTestStorage extends TileEntityBase implements INetworkDevice {
    private int chargeStored;

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        chargeStored = compound.getInteger("chargeStored");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        compound.setInteger("chargeStored", chargeStored);
    }

    @Override
    public int getConsumption() {
        return 80;
    }

    @Override
    public int produceCharge() {
        chargeStored -= 80;
        System.out.println("Storage Producing charge of " + 80 + ", total charge is " + chargeStored);
        markDirty();
        return 80;
    }

    @Override
    public void consumeCharge(int charge) {
        chargeStored += charge;
        System.out.println("Storing charge of " + charge + ", total charge is " + chargeStored);
        markDirty();
    }
}
