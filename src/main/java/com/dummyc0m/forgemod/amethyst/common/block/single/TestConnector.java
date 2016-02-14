package com.dummyc0m.forgemod.amethyst.common.block.single;

import com.dummyc0m.forgemod.amethyst.common.tile.TileTestConnector;
import com.dummyc0m.forgemod.amethyst.core.block.BlockBase;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBlockBase;
import com.dummyc0m.forgemod.amethyst.core.tile.TileEntityBase;
import com.dummyc0m.forgemod.amethyst.core.util.BlockStateUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public abstract class TestConnector extends BlockBase {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");

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
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, BlockStateUtil.getFacingFromMeta(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return BlockStateUtil.getMetaFromFacing(state.getValue(FACING));
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, FACING);
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
