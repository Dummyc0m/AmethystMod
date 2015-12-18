package com.dummyc0m.forgemod.amethyst.common;

import com.dummyc0m.forgemod.amethyst.common.block.BlockOre;
import com.dummyc0m.forgemod.amethyst.common.block.BlockSimple;
import com.dummyc0m.forgemod.amethyst.common.item.ItemBase;
import com.dummyc0m.forgemod.amethyst.common.item.ItemTool;
import com.dummyc0m.forgemod.amethyst.common.world.AMWorldGen;
import com.dummyc0m.forgemod.amethyst.common.world.generator.WorldGenMultiOre;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Dummyc0m on 11/26/15.
 */
public class AMContent {
    //Blocks
    public static BlockSimple blockAmethystOre;
    public static BlockSimple blockAmethystPoorOre;

    //Items
    public static ItemBase itemMaterial;
    public static ItemBase itemFragment;
    public static ItemBase itemTool;

    public static AMWorldGen worldGen;

    public static final String TOOL_SCREWDRIVER = "AMETHYST_SCREWDRIVER";

    public static void preInit() {
        //Run before anything else. Read your config, create block, item, etc, and register them with the GameRegistry.
        //item block oredict fluid mining worldgen
        itemMaterial = new ItemBase("material", 64, "amethyst");
        itemFragment = new ItemBase("fragment", 64, "amethystFragment");
        itemTool = new ItemTool();

        //blocks
        blockAmethystOre = new BlockOre("oreAmethyst", 3, itemMaterial, 0, 1, 2);
        blockAmethystPoorOre = new BlockOre("oreAmethystPoor", 2, itemFragment, 0, 2, 3);

        worldGen = new AMWorldGen();
        worldGen.worldGens.add(new WorldGenMultiOre(60, 2, 60, 20, 300, 3, new WorldGenMultiOre.WeightedBlock(blockAmethystOre.getDefaultState(), 1), new WorldGenMultiOre.WeightedBlock(blockAmethystPoorOre.getDefaultState(), 9)));
        worldGen.dimensionBlacklist.add(-1);
        worldGen.dimensionBlacklist.add(1);
        GameRegistry.registerWorldGenerator(worldGen, 0);
        // /fill ~20 ~10 ~20 ~-20 ~-8 ~-20 air 0 replace stone 0
    }

    public static void init() {
        //Do your mod setup. Build whatever data structures you care about. Register recipes.
        //tile entities entities modded stuff
    }

    public static void postInit() {
        //Handle interaction with other mods, complete your setup based on this.
    }
}