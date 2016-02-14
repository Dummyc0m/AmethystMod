package com.dummyc0m.forgemod.amethyst.common.block.single;

import com.dummyc0m.forgemod.amethyst.common.tile.TileTestGenerator;
import com.dummyc0m.forgemod.amethyst.core.block.BlockBase;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * Created by Dummyc0m on 1/3/16.
 */
public class TestGenerator extends BlockBase {
    public TestGenerator(String name) {
        super(Material.rock, name, ItemBlockBase.class);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileTestGenerator();
    }
}
