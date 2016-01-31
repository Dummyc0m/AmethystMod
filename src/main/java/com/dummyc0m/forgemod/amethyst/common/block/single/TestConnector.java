package com.dummyc0m.forgemod.amethyst.common.block.single;

import com.dummyc0m.forgemod.amethyst.common.block.BlockBase;
import com.dummyc0m.forgemod.amethyst.common.item.ItemBlockBase;
import com.dummyc0m.forgemod.amethyst.common.tile.TileEntityBase;
import com.dummyc0m.forgemod.amethyst.common.tile.TileTestConnector;
import com.dummyc0m.forgemod.amethyst.common.util.BlockStateUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public abstract class TestConnector extends BlockBase {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    private int counter = 1;
    public ExtendedBlockState state = new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[]{B3DLoader.B3DFrameProperty.instance});

    public TestConnector(String name) {
        super(Material.rock, name, ItemBlockBase.class);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.DOWN));
    }


    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isVisuallyOpaque() {
        return false;
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, BlockStateUtil.getFacingFromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return BlockStateUtil.getMetaFromFacing(state.getValue(FACING));
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        //Only return an IExtendedBlockState from this method and createState(), otherwise block placement might break!
        B3DLoader.B3DState newState = new B3DLoader.B3DState(null, counter);
        return ((IExtendedBlockState) state).withProperty(B3DLoader.B3DFrameProperty.instance, newState);
    }

    @Override
    protected BlockState createBlockState() {
        //return new BlockState(this, FACING);
        return new ExtendedBlockState(this, new IProperty[]{FACING}, new IUnlistedProperty[]{B3DLoader.B3DFrameProperty.instance});
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        ((TileEntityBase) worldIn.getTileEntity(pos)).onUnload();
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (world.isRemote) {
            if (player.isSneaking()) counter--;
            else counter++;
            //if(counter >= model.getNode().getKeys().size()) counter = 0;
            world.markBlockRangeForRenderUpdate(pos, pos);
            System.out.println("click " + counter);
        }
        return false;
    }




    public static class TestProducerConnector extends TestConnector {

        public TestProducerConnector() {
            super("producerConnector");
        }

        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new TileTestConnector.TileProducerConnector();
        }
    }

    public static class TestConsumerConnector extends TestConnector {

        public TestConsumerConnector() {
            super("consumerConnector");
        }

        @Override
        public TileEntity createNewTileEntity(World worldIn, int meta) {
            return new TileTestConnector.TileConsumerConnector();
        }
    }
}
