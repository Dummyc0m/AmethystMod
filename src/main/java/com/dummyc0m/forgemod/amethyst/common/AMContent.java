package com.dummyc0m.forgemod.amethyst.common;

import com.dummyc0m.forgemod.amethyst.common.block.BlockSimple;
import com.dummyc0m.forgemod.amethyst.common.item.ItemBase;
import com.dummyc0m.forgemod.amethyst.common.item.ItemTool;
import com.dummyc0m.forgemod.amethyst.common.world.AMWorldGen;
import com.dummyc0m.forgemod.amethyst.common.world.generator.WorldGenMinable;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Dummyc0m on 11/26/15.
 */
public class AMContent {
    //Blocks
    public static BlockSimple blockAmethystOre;

    //Items
    public static ItemBase itemMaterial;
    public static ItemBase itemTool;

    public static AMWorldGen worldGen;

    public static final String TOOL_SCREWDRIVER = "AMETHYST_SCREWDRIVER";

    public static void preInit() {
        //Run before anything else. Read your config, create block, item, etc, and register them with the GameRegistry.
        //item block oredict fluid mining worldgen
        itemMaterial = new ItemBase("material", 64, "amethyst");
        itemTool = new ItemTool();

        //blocks
        blockAmethystOre = new BlockSimple(Material.rock, "oreAmethyst", ItemBlock.class);

        worldGen = new AMWorldGen();
        worldGen.minableGens.add(new WorldGenMinable(blockAmethystOre.getDefaultState(), 60, 2, 60, 20, 200, 4));
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