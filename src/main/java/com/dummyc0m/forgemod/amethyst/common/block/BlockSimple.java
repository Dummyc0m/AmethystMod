package com.dummyc0m.forgemod.amethyst.common.block;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.api.IBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Dummyc0m on 12/1/15.
 */
public class BlockSimple extends Block implements IBlockBase {
    private String blockName;
    private String[] subNames;
    private boolean flammability;
    private boolean tooltip;

    public BlockSimple(Material materialIn, String name, Class<? extends ItemBlock> itemBlock, String... subNames) {
        super(materialIn);
        this.setUnlocalizedName(AmethystMod.MODID + "." + name);
        this.setCreativeTab(AmethystMod.creativeTab);
        blockName = name;
        this.subNames = subNames != null && subNames.length > 0 ? subNames : null;
        if(itemBlock == null) {
            GameRegistry.registerBlock(this, name);
        } else {
            GameRegistry.registerBlock(this, itemBlock, name);
        }
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
    public String getBlockName() {
        return blockName;
    }

    @Override
    public String[] getSubNames() {
        return subNames;
    }

    @Override
    public void setTooltip(boolean showToolTip) {
        tooltip = showToolTip;
    }

    @Override
    public boolean isTooltip() {
        return tooltip;
    }
}
