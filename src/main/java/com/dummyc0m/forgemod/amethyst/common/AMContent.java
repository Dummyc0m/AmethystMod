package com.dummyc0m.forgemod.amethyst.common;

import com.dummyc0m.forgemod.amethyst.common.item.ItemBase;

/**
 * Created by Dummyc0m on 11/26/15.
 */
public class AMContent {
    //Blocks

    //Items
    public static ItemBase itemMaterial;

    public static void preInit() {
        //Run before anything else. Read your config, create block, item, etc, and register them with the GameRegistry.
        //item block oredict fluid mining worldgen
        itemMaterial = new ItemBase("material", 64);
    }

    public static void init() {
        //Do your mod setup. Build whatever data structures you care about. Register recipes.
        //tile entities entities modded stuff
    }

    public static void postInit() {
        //Handle interaction with other mods, complete your setup based on this.
    }
}