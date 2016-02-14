package com.dummyc0m.forgemod.amethyst.common;

import com.dummyc0m.forgemod.amethyst.common.block.BlockOre;
import com.dummyc0m.forgemod.amethyst.common.block.single.TestConnector;
import com.dummyc0m.forgemod.amethyst.common.block.single.TestGenerator;
import com.dummyc0m.forgemod.amethyst.common.block.single.TestStorage;
import com.dummyc0m.forgemod.amethyst.common.item.ItemTool;
import com.dummyc0m.forgemod.amethyst.common.tile.TileTestConnector;
import com.dummyc0m.forgemod.amethyst.common.tile.TileTestGenerator;
import com.dummyc0m.forgemod.amethyst.common.tile.TileTestStorage;
import com.dummyc0m.forgemod.amethyst.common.world.AMWorldGen;
import com.dummyc0m.forgemod.amethyst.core.block.BlockBase;
import com.dummyc0m.forgemod.amethyst.core.block.BlockSimple;
import com.dummyc0m.forgemod.amethyst.core.generator.WorldGenMultiOre;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBase;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBlockBase;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Dummyc0m on 11/26/15.
 */
public class AMContent {
    //Ores
    public static BlockOre blockAmethystOre;
    public static BlockOre blockAmethystPoorOre;
    public static BlockSimple blockMetalOre;
//    public static BlockOre blockBauxite; aluminum
//    public static BlockOre blockChalcopyrite; copper
//    public static BlockOre blockSphalerite; Zinc
//    public static BlockOre blockArgentite; silver
//    public static BlockOre blockCassiterite; tin
//    public static BlockOre blockGelena; lead
    //Garnierite nickel
    //Chromite chromium
    //hematite iron

    //Items
    public static ItemBase itemMaterial;
    public static ItemBase itemFragment;
    public static ItemBase itemTool;
    public static ItemBase itemIngot;

    //AC Programmables

    //AC Net
    public static BlockBase producerConnector;
    public static BlockBase consumerConnector;
    public static BlockBase testGenerator;
    public static BlockBase testStorage;

    public static AMWorldGen worldGen;

    public static final String TOOL_SCREWDRIVER = "AMETHYST_SCREWDRIVER";

    public static void preInit() {
        //Run before anything else. Read your config, create block, item, etc, and register them with the GameRegistry.
        //item block oredict fluid mining worldgen
        itemMaterial = new ItemBase("material", 64, "amethyst");
        itemFragment = new ItemBase("fragment", 64, "amethystFragment");
        itemTool = new ItemTool();
        //                                    0                1              2            3              4           5            6               7              8                9               10                  11            12
        itemIngot = new ItemBase("ingot", 64, "ingotAluminum", "ingotCopper", "ingotZinc", "ingotSilver", "ingotTin", "ingotLead", "ingotLithium", "ingotNickel", "ingotChromium", "ingotPigIron", "ingotWroughtIron", "ingotSteel", "ingotSteelStainless");

        //ores
        blockAmethystOre = new BlockOre("oreAmethyst", 3, itemMaterial, 0, 1, 2);
        blockAmethystPoorOre = new BlockOre("oreAmethystPoor", 2, itemFragment, 0, 2, 3);
        blockMetalOre = new BlockSimple(Material.rock, "blockMetalOre", ItemBlockBase.class, "bauxite", "chalcopyrite", "sphalerite", "argentite", "cassiterite", "gelena", "garnierite", "chromite", "hematite");


        producerConnector = new TestConnector.TestProducerConnector();
        consumerConnector = new TestConnector.TestConsumerConnector();
        testGenerator = new TestGenerator("testGenerator");
        testStorage = new TestStorage("testStorage");

        GameRegistry.registerTileEntity(TileTestConnector.TileProducerConnector.class, "tileProducerConnector");
        GameRegistry.registerTileEntity(TileTestConnector.TileConsumerConnector.class, "tileConsumerConnector");
        GameRegistry.registerTileEntity(TileTestGenerator.class, "tileTestGenerator");
        GameRegistry.registerTileEntity(TileTestStorage.class, "tileTestStorage");

        worldGen = new AMWorldGen();
        worldGen.worldGens.add(new WorldGenMultiOre(60, 2, 60, 20, 250, 3, new WorldGenMultiOre.WeightedBlock(blockAmethystOre.getDefaultState(), 1), new WorldGenMultiOre.WeightedBlock(blockAmethystPoorOre.getDefaultState(), 9)));
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