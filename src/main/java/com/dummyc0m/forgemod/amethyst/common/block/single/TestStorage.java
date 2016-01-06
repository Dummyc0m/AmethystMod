package com.dummyc0m.forgemod.amethyst.common.block.single;

import com.dummyc0m.forgemod.amethyst.common.block.BlockBase;
import com.dummyc0m.forgemod.amethyst.common.item.ItemBlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public class TestStorage extends BlockBase {
    public TestStorage(Material materialIn, String name, Class<? extends ItemBlockBase> itemBlock, String... subNames) {
        super(materialIn, name, itemBlock, subNames);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
}
