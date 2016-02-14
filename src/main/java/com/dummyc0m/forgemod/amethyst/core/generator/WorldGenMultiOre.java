package com.dummyc0m.forgemod.amethyst.core.generator;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.WeightedRandom;
import scala.actors.threadpool.Arrays;

import java.util.List;
import java.util.Random;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public class WorldGenMultiOre extends WorldGenMinable {
    private List weightedBlocks;
    private int weight;

    public WorldGenMultiOre(int veinSize, int triesPerChunk, int maxY, int minY, int rarity, int density, Block replaceBlock, WeightedBlock... weightedBlocks) {
        super(veinSize, triesPerChunk, maxY, minY, rarity, density, replaceBlock);
        this.weightedBlocks = Arrays.asList(weightedBlocks);
        weight = WeightedRandom.getTotalWeight(this.weightedBlocks);
    }

    public WorldGenMultiOre(int veinSize, int triesPerChunk, int maxY, int minY, int rarity, int density, WeightedBlock... weightedBlocks) {
        super(veinSize, triesPerChunk, maxY, minY, rarity, density);
        this.weightedBlocks = Arrays.asList(weightedBlocks);
        weight = WeightedRandom.getTotalWeight(this.weightedBlocks);
    }

    @Override
    public IBlockState getBlockState(Random random) {
        return ((WeightedBlock) WeightedRandom.getRandomItem(random, weightedBlocks, weight)).blockState;
    }

    public static class WeightedBlock extends WeightedRandom.Item {
        IBlockState blockState;

        public WeightedBlock(IBlockState blockState, int itemWeightIn) {
            super(itemWeightIn);
            this.blockState = blockState;
        }
    }
}
