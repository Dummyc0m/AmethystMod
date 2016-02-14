package com.dummyc0m.forgemod.amethyst.common.tile;

import com.dummyc0m.forgemod.amethyst.common.block.single.TestConnector;
import com.dummyc0m.forgemod.amethyst.core.energy.INetworkConnector;
import com.dummyc0m.forgemod.amethyst.core.energy.INetworkDevice;
import com.dummyc0m.forgemod.amethyst.core.energy.NetManager;
import com.dummyc0m.forgemod.amethyst.core.tile.TileEntityBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraftforge.fml.common.FMLLog;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public abstract class TileTestConnector extends TileEntityBase implements INetworkConnector {
    private String networkId;

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        String uuid = compound.getString("uuid");
        if (!"".equals(uuid)) {
            networkId = uuid;
        }
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        if (networkId != null) {
            compound.setString("uuid", networkId);
        }
    }

    @Override
    public String getNetworkId() {
        return networkId;
    }

    @Override
    public BlockPos getConnectedPos() {
        return pos.offset(getBlockState().getValue(TestConnector.TestConsumerConnector.FACING).getOpposite());
    }

    @Override
    public void setNetworkId(String uuid) {
        networkId = uuid;
        markDirty();
    }

    public static class TileProducerConnector extends TileTestConnector implements IConnectorProducer {
        @Override
        public int produceCharge() {
            if (isInvalid()) {
                return 0;
            }
            TileEntity te = worldObj.getTileEntity(getConnectedPos());
            if (te != null && te instanceof INetworkDevice) {
                return ((INetworkDevice) te).produceCharge();
            }
            return 0;
        }

        @Override
        public void onLoad() {
            if (getNetworkId() != null && !worldObj.isRemote && !NetManager.INSTANCE.addToNetwork(this)) {
                setNetworkId(null);
                FMLLog.warning("Connector at %s was unable to add to its network", this.pos);
            }
        }

        @Override
        public void onUnload() {
            if (getNetworkId() != null && !worldObj.isRemote) {
                NetManager.INSTANCE.removeFromNetwork(this);
            }
        }
    }

    public static class TileConsumerConnector extends TileTestConnector implements IConnectorConsumer {

        @Override
        public void onLoad() {
            if (getNetworkId() != null && !worldObj.isRemote && !NetManager.INSTANCE.addToNetwork(this)) {
                setNetworkId(null);
                FMLLog.warning("Connector at %s was unable to add to its network", this.pos);
            }
        }

        @Override
        public void onUnload() {
            if (getNetworkId() != null && !worldObj.isRemote) {
                NetManager.INSTANCE.removeFromNetwork(this);
            }
        }

        @Override
        public void consumeCharge(int charge) {
            if (isInvalid()) {
                return;
            }
            TileEntity te = worldObj.getTileEntity(getConnectedPos());
            if (te != null && te instanceof INetworkDevice) {
                ((INetworkDevice) te).consumeCharge(charge);
            }
        }

        @Override
        public int getConsumption() {
            if (isInvalid()) {
                return 0;
            }
            TileEntity te = worldObj.getTileEntity(getConnectedPos());
            if (te != null && te instanceof INetworkDevice) {
                return ((INetworkDevice) te).getConsumption();
            }
            return 0;
        }
    }
}
