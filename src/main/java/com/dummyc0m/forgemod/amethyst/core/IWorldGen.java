package com.dummyc0m.forgemod.amethyst.core;

import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public interface IWorldGen {
    void generate(World world, Random random, int xChunk, int zChunk);


}
