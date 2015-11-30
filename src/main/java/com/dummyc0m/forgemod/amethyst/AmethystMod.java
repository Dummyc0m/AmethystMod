package com.dummyc0m.forgemod.amethyst;

import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.common.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Dummyc0m on 11/26/15.
 * Amethyst, a magic programmable mod
 */

@Mod(modid = AmethystMod.MODID, name = AmethystMod.MODNAME, version = AmethystMod.VERSION)
public class AmethystMod {
    public static final String MODID = "amethyst";
    public static final String MODNAME = "Amethyst";
    public static final String VERSION = "0.0";
    public static final double VERSION_D = Double.parseDouble(VERSION);

    @Mod.Instance(MODID)
    public static AmethystMod instance = new AmethystMod();

    @SidedProxy(clientSide = "com.dummyc0m.forgemod.amethyst.client.ClientProxy", serverSide = "com.dummyc0m.forgemod.amethyst.common.CommonProxy")
    public static CommonProxy proxy;

    public static CreativeTabs creativeTab = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return AMContent.itemMaterial;
        }

        @Override
        public int getIconItemDamage() {
            return 0;
        }

        @Override
        public ItemStack getIconItemStack() {
            return new ItemStack(AMContent.itemMaterial, 1, 0);
        }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        //Run before anything else. Read your config, create block, item, etc, and register them with the GameRegistry.
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        //Do your mod setup. Build whatever data structures you care about. Register recipes.
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        //Handle interaction with other mods, complete your setup based on this.
        proxy.postInit(event);
    }
}
