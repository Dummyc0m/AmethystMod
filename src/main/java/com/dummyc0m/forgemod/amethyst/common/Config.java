package com.dummyc0m.forgemod.amethyst.common;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Dummyc0m on 11/28/15.
 */
public class Config {
    public static Configuration config;

    public static void init(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        //Do stuff
        Property versionProp = config.get(Configuration.CATEGORY_GENERAL, "version", AmethystMod.VERSION);
        versionProp.comment = "Do not touch this line! #DANGEROUS#";
        double version = versionProp.getDouble();
        //save
        config.save();
    }
}
