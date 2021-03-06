package com.dummyc0m.forgemod.amethyst.core.block;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBlockBase;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
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

    public BlockSimple(Material materialIn, String name, Class<? extends ItemBlockBase> itemBlock, String... subNames) {
        super(materialIn);
        setUnlocalizedName(AmethystMod.MODID + "." + name);
        setCreativeTab(AmethystMod.creativeTab);
        setHardness(3f);
        setResistance(5f);
        blockName = name;
        this.subNames = subNames != null && subNames.length > 0 ? subNames : new String[]{blockName};
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
    public boolean isTooltip() {
        return false;
    }
}
