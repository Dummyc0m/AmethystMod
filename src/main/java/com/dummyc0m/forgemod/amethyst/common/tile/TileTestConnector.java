package com.dummyc0m.forgemod.amethyst.common.tile;

import com.dummyc0m.forgemod.amethyst.api.network.INetworkConnector;
import com.dummyc0m.forgemod.amethyst.api.network.INetworkDevice;
import net.minecraft.nbt.NBTTagCompound;

import java.util.UUID;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public class TileTestConnector extends TileEntityBase implements INetworkConnector {
    @Override
    public void readCustomNBT(NBTTagCompound compound) {

    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {

    }

    @Override
    public INetworkDevice getConnectedDevice() {
        return null;
    }

    @Override
    public UUID getParentNetworkUUID() {
        return null;
    }
}
