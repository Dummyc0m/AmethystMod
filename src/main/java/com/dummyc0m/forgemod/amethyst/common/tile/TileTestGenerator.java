package com.dummyc0m.forgemod.amethyst.common.tile;

import com.dummyc0m.forgemod.amethyst.api.network.INetworkDevice;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

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
    public void connectTo(EnumFacing facing) {

    }

    @Override
    public int produceCharge() {
        return 0;
    }

    @Override
    public int consumeCharge(int charge) {
        return 0;
    }
}
