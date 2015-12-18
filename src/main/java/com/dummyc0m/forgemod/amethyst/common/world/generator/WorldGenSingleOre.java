package com.dummyc0m.forgemod.amethyst.common.world.generator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;

import java.util.Random;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public class WorldGenSingleOre extends WorldGenMinable {
    private IBlockState blockState;

    public WorldGenSingleOre(int veinSize, int triesPerChunk, int maxY, int minY, int rarity, int density, Block replaceBlock, IBlockState blockState) {
        super(veinSize, triesPerChunk, maxY, minY, rarity, density, replaceBlock);
        this.blockState = blockState;
    }

    public WorldGenSingleOre(int veinSize, int triesPerChunk, int maxY, int minY, int rarity, int density, IBlockState blockState) {
        super(veinSize, triesPerChunk, maxY, minY, rarity, density);
        this.blockState = blockState;
    }

    @Override
    public IBlockState getBlockState(Random random) {
        return blockState;
    }
}
