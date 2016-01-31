package com.dummyc0m.forgemod.amethyst.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Dummyc0m on 11/26/15.
 * Common code for both server and client
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) {
        //Run before anything else. Read your config, create block, item, etc, and register them with the GameRegistry.
        Config.init(event);
        AMContent.preInit();
    }

    public void init(FMLInitializationEvent event) {
        //Do your mod setup. Build whatever data structures you care about. Register recipes.
        AMContent.init();
        AMRecipes.registerCraftingRecipes();
        AMRecipes.registerFurnaceRecipes();
        AMRecipes.registerCustomRecipes();

        AMEvents events = new AMEvents();
        MinecraftForge.EVENT_BUS.register(events);
        //FMLCommonHandler.instance().bus().register(events);
    }

    public void postInit(FMLPostInitializationEvent event) {
        //Handle interaction with other mods, complete your setup based on this.
        AMContent.postInit();
    }

    /**
     * Returns a side-appropriate EntityPlayer for use during message handling
     */
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity;
    }
}
