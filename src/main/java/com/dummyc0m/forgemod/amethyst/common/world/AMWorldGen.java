package com.dummyc0m.forgemod.amethyst.common.world;

import com.dummyc0m.forgemod.amethyst.api.IWorldGen;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.*;

/**
 * Created by Dummyc0m on 11/27/15.
 */
public class AMWorldGen implements IWorldGenerator {
    public final List<IWorldGen> worldGens = new ArrayList<>();
    public final List<Integer> dimensionBlacklist = new ArrayList<>();
    public final Map<Integer, List<IWorldGen>> dimensionSpecificGens = new HashMap<>();

    public void addDimensionSpecificGen(int id, IWorldGen worldGen) {
        if(dimensionSpecificGens.get(id) == null) {
            List<IWorldGen> list = new ArrayList<>();
            list.add(worldGen);
            dimensionSpecificGens.put(id, list);
        } else {
            dimensionSpecificGens.get(id).add(worldGen);
        }
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if(dimensionBlacklist.contains(world.provider.getDimensionId())) {
            if(dimensionSpecificGens.get(world.provider.getDimensionId()) != null) {
                for(IWorldGen gen : dimensionSpecificGens.get(world.provider.getDimensionId())) {
                    gen.generate(world, random, chunkX, chunkZ);
                }
            }
        } else {
            for(IWorldGen gen : worldGens) {
                gen.generate(world, random, chunkX, chunkZ);
            }
        }
    }
}
