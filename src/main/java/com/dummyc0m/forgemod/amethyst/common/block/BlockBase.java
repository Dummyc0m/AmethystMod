package com.dummyc0m.forgemod.amethyst.common.block;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.api.IBlockBase;
import com.dummyc0m.forgemod.amethyst.api.IScrewdriverHarvestable;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.common.item.ItemBlockBase;
import com.dummyc0m.forgemod.amethyst.common.util.ToolUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Dummyc0m on 11/28/15.
 */
public abstract class BlockBase extends BlockContainer implements IBlockBase, IScrewdriverHarvestable {
    private String blockName;
    private String[] subNames;
    private boolean flammability;
    private boolean tooltip;

    public BlockBase(Material materialIn, String name, Class<? extends ItemBlockBase> itemBlock, String... subNames) {
        super(materialIn);
        this.setUnlocalizedName(AmethystMod.MODID + "." + name);
        this.setCreativeTab(AmethystMod.creativeTab);
        blockName = name;
        this.subNames = subNames != null && subNames.length > 0 ? subNames : null;
        GameRegistry.registerBlock(this, itemBlock, name);
    }

    @Override
    public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, EntityLiving.SpawnPlacementType type) {
        return true;
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
        return (canScrewdriverHarvest() && AMContent.TOOL_SCREWDRIVER.equals(type)) || getHarvestTool(state).equals(type);
    }

    @Override
    public boolean canHarvestBlock(IBlockAccess world, BlockPos pos, EntityPlayer player) {
        return this.getMaterial().isToolNotRequired() || ToolUtil.isToolEffective(player.getHeldItem(), world.getBlockState(pos));
    }

    @Override
    public MovingObjectPosition collisionRayTrace(World worldIn, BlockPos pos, Vec3 start, Vec3 end) {
        return super.collisionRayTrace(worldIn, pos, start, end);
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
        return tooltip;
    }

    @Override
    public void setTooltip(boolean showToolTip) {
        tooltip = showToolTip;
    }
}
