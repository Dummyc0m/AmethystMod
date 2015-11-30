package com.dummyc0m.forgemod.amethyst.common.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;

/**
 * Created by Dummyc0m on 11/28/15.
 */
public abstract class BlockAMBase extends BlockContainer {
    private String blockName;
    private String[] subNames;

    public BlockAMBase(Material materialIn, String blockName, String... subNames) {
        super(materialIn);
    }


}
