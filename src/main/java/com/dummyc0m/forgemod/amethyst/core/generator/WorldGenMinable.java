package com.dummyc0m.forgemod.amethyst.core.generator;

import com.dummyc0m.forgemod.amethyst.core.IWorldGen;
import com.google.common.base.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

import java.util.Random;

/**
 * Created by Dummyc0m on 12/5/15.
 */
public abstract class WorldGenMinable implements IWorldGen {
    private final int veinSize;
    private final int branchSize;
    private final int subBranchSize;
    private final int triesPerChunk;
    private final int maxY;
    private final int minY;
    private final int rarity;
    private final int density;
    private final Predicate replaceBlock;

    /**
     * @param veinSize obvious
     * @param triesPerChunk obvious
     * @param maxY maximum
     * @param minY minimum
     * @param rarity determines whether it spawns in a certain chunk, 1 would be always true, higher the rarer
     * @param density default 1, higher value result in more scattered ores
     * @param replaceBlock the predicate indicating the block to be replaced
     */
    public WorldGenMinable(int veinSize, int triesPerChunk, int maxY, int minY, int rarity, int density, Block replaceBlock) {
        this.veinSize = veinSize;
        this.branchSize = veinSize / 30 + 1;
        this.subBranchSize = branchSize / 5 + 1;
        this.triesPerChunk = triesPerChunk;
        this.maxY = maxY;
        this.minY = minY;
        this.rarity = rarity;
        this.density = density + 1;
        this.replaceBlock = BlockHelper.forBlock(replaceBlock);
    }

    /**
     * @param veinSize obvious
     * @param triesPerChunk obvious
     * @param maxY maximum
     * @param minY minimum
     * @param rarity determines whether it spawns in a certain chunk, 1 would be always true, higher the rarer
     * @param density default 1, higher value result in more scattered ores
     */
    public WorldGenMinable(int veinSize, int triesPerChunk, int maxY, int minY, int rarity, int density) {
        this( veinSize, triesPerChunk, maxY, minY, rarity, density, Blocks.stone);
    }

    @Override
    public void generate(World world, Random random, int xChunk, int zChunk) {
        if (rarity < 2 || random.nextInt(rarity) == 0) {
            for(int i = 0; i < triesPerChunk; i++) {
                int startX = xChunk * 16 + random.nextInt(16);
                int startY = minY + random.nextInt(maxY - minY);
                int startZ = zChunk * 16 + random.nextInt(16);
                generateVein(world, random, startX, startY, startZ);
                //TODO
                FMLLog.info("generating vein at: " + startX + " " + startY + " " + startZ);
            }
        }
    }

    //blame mojang for unchecked
    @SuppressWarnings("unchecked")
    private void generateVein(World world, Random random, int posX, int posY, int posZ) {
        int veinLeft = veinSize;
        int branchLeft;
        int subBranchLeft;
        //branch
        int branchX;
        int branchY;
        int branchZ;
        boolean branchDirX;
        boolean branchDirY;
        boolean branchDirZ;
        int branchDirChange;

        //sub branch
        int subBranchX;
        int subBranchY;
        int subBranchZ;
        boolean subBranchDirX;
        boolean subBranchDirY;
        boolean subBranchDirZ;
        int dirChangeSubBranch;

        while(veinLeft > 0) {
            branchDirChange = random.nextInt(6);
            branchDirX = random.nextBoolean();
            branchDirY = random.nextBoolean();
            branchDirZ = random.nextBoolean();
            branchLeft = branchSize;
            while(branchLeft > 0) {
                branchX = shiftPosition(branchDirChange, 0, branchDirX, posX, random);
                branchY = shiftPosition(branchDirChange, 1, branchDirY, posY, random);
                branchZ = shiftPosition(branchDirChange, 2, branchDirZ, posZ, random);
                if(random.nextInt(3) == 0) {
                    dirChangeSubBranch = random.nextInt(6);
                    subBranchDirX = random.nextBoolean();
                    subBranchDirY = random.nextBoolean();
                    subBranchDirZ = random.nextBoolean();
                    subBranchX = shiftPosition(dirChangeSubBranch, 0, subBranchDirX, branchX, random);
                    subBranchY = shiftPosition(dirChangeSubBranch, 1, subBranchDirY, branchY, random);
                    subBranchZ = shiftPosition(dirChangeSubBranch, 2, subBranchDirZ, branchZ, random);
                    subBranchLeft = subBranchSize;
                    while(subBranchLeft > 0) {
                        subBranchX = shiftPosition(dirChangeSubBranch, 0, subBranchDirX, subBranchX, random);
                        subBranchY = shiftPosition(dirChangeSubBranch, 1, subBranchDirY, subBranchY, random);
                        subBranchZ = shiftPosition(dirChangeSubBranch, 2, subBranchDirZ, subBranchZ, random);

                        BlockPos blockPos = new BlockPos(subBranchX, subBranchY, subBranchZ);
                        if (world.getBlockState(blockPos).getBlock().isReplaceableOreGen(world, blockPos, replaceBlock)) {
                            world.setBlockState(blockPos, getBlockState(random), 2);
                        }
                        subBranchLeft--;
                    }
                    branchLeft -= subBranchSize;
                }

                BlockPos blockPos = new BlockPos(branchX, branchY, branchZ);
                if (world.getBlockState(blockPos).getBlock().isReplaceableOreGen(world, blockPos, replaceBlock)) {
                    world.setBlockState(blockPos, getBlockState(random), 2);
                }
                branchLeft--;
            }
            veinLeft -= branchSize;
            posX += random.nextInt(3) - 1;
            posY += random.nextInt(3) - 1;
            posZ += random.nextInt(3) - 1;
        }
    }

    public abstract IBlockState getBlockState(Random random);

    private int shiftPosition(int changeDisc, int value, boolean dir, int pos, Random random) {
        if(changeDisc != value) {
            return dir ? pos + random.nextInt(density) : pos - random.nextInt(density);
        }
        return pos;
    }
}
