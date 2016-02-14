package com.dummyc0m.forgemod.amethyst.core.block;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.core.IScrewdriverHarvestable;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Dummyc0m on 11/28/15.
 * Amethyst Block with TileEntity
 */
public abstract class BlockBase extends Block implements IBlockBase, IScrewdriverHarvestable, ITileEntityProvider {
    private String blockName;
    private String[] subNames;
    private boolean flammability;

    public BlockBase(Material materialIn, String name, Class<? extends ItemBlockBase> itemBlock, String... subNames) {
        super(materialIn);
        setUnlocalizedName(AmethystMod.MODID + "." + name);
        setCreativeTab(AmethystMod.creativeTab);
        setHarvestLevel("pickaxe", 3);
        setHardness(1.5f);
        setResistance(3f);
        blockName = name;
        isBlockContainer = true;
        this.subNames = subNames != null && subNames.length > 0 ? subNames : new String[]{blockName};
        GameRegistry.registerBlock(this, itemBlock, name);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return false;
    }

    @Override
    public void setFlammable(boolean flammability) {
        this.flammability = flammability;
    }

    @Override
    public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return flammability;
    }

    @Override
    public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return flammability ? 20 : 0;
    }

    @Override
    public int getFireSpreadSpeed(IBlockAccess world, BlockPos pos, EnumFacing face) {
        return flammability ? 5 : 0;
    }

    @Override
    public boolean canScrewdriverHarvest() {
        return true;
    }

    @Override
    public boolean isToolEffective(String type, IBlockState state) {
        return (canScrewdriverHarvest() && AMContent.TOOL_SCREWDRIVER.equals(type)) || super.isToolEffective(type, state);
    }

    @Override
    public String getBlockName() {
        return blockName;
    }

    @Override
    public String[] getSubNames() {
        return subNames;
    }

    @Override
    public boolean isTooltip() {
        return false;
    }

    @Override
    public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam) {
        super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        return tileentity != null && tileentity.receiveClientEvent(eventID, eventParam);
    }
}
