package com.dummyc0m.forgemod.amethyst.core.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public abstract class TileEntityBase extends TileEntity {
    @Override
    public final void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readCustomNBT(compound);
    }

    @Override
    public final void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        writeCustomNBT(compound);
    }

    public abstract void readCustomNBT(NBTTagCompound compound);

    public abstract void writeCustomNBT(NBTTagCompound compound);

    public void onLoad() {

    }

    @Override
    public final void onChunkUnload() {
        onUnload();
    }

    public void onUnload() {

    }

    public IBlockState getBlockState() {
        return worldObj.getBlockState(pos);
    }
//    @Override
//    public Packet getDescriptionPacket() {
//        return super.getDescriptionPacket();
//    }
//
//    @Override
//    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
//        super.onDataPacket(net, pkt);
//    }
}
