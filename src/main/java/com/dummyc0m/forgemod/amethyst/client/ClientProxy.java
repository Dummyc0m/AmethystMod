package com.dummyc0m.forgemod.amethyst.client;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.common.CommonProxy;
import com.dummyc0m.forgemod.amethyst.core.block.IBlockBase;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.b3d.B3DLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;

/**
 * Created by Dummyc0m on 11/26/15.
 * client specific code
 */
public class ClientProxy extends CommonProxy {
    //private ItemModelMesher itemModelMesher;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        registerRenderers();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    private void registerRenderers() {
        B3DLoader.instance.addDomain(AmethystMod.MODID);
        OBJLoader.instance.addDomain(AmethystMod.MODID);

        //itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerItem(AMContent.itemMaterial);
        registerItem(AMContent.itemFragment);
        registerItem(AMContent.itemTool);
        registerItem(AMContent.itemIngot);
        registerBlockBase(AMContent.blockAmethystOre);
        registerBlockBase(AMContent.blockAmethystPoorOre);
        registerBlockBase(AMContent.blockMetalOre);
        registerBlockBase(AMContent.producerConnector);
        registerBlockBase(AMContent.consumerConnector);
        registerBlockBase(AMContent.testGenerator);
        registerBlockBase(AMContent.testStorage);
    }

    private void registerItem(ItemBase item) {
        if(item.getHasSubtypes()) {
            String[] subNames = item.getSubNames();
            for(int i = 0; i < subNames.length; i++) {
                ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + subNames[i], "inventory");
                //itemModelMesher.register(item, i, res);
                ModelLoader.setCustomModelResourceLocation(item, i, res);
                ModelBakery.registerItemVariants(item, res);
                //ModelBakery.addVariantName(item, AmethystMod.MODID + ":" + subNames[i]);
                FMLLog.log(Level.INFO, "Registered Item Model for " + AmethystMod.MODID + ":" + subNames[i]);
            }
        } else {
            ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + item.getItemName(), "inventory");
            //itemModelMesher.register(item, 0, res);
            ModelLoader.setCustomModelResourceLocation(item, 0, res);
            ModelBakery.registerItemVariants(item, res);
            FMLLog.log(Level.INFO, "Registered Item Model for " + AmethystMod.MODID + ":" + item.getItemName());
        }
    }

    private void registerBlockBase(IBlockBase block) {
        if (block.getSubNames() != null) {
            String[] subNames = block.getSubNames();
            for (int i = 0; i < subNames.length; i++) {
                ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + subNames[i], "inventory");
                //itemModelMesher.register(GameRegistry.findItem(AmethystMod.MODID, subNames[i]), i, res);
                ModelLoader.setCustomModelResourceLocation(GameRegistry.findItem(AmethystMod.MODID, block.getBlockName()), i, res);
                ModelBakery.registerItemVariants(GameRegistry.findItem(AmethystMod.MODID, block.getBlockName()), res);
                FMLLog.log(Level.INFO, "Registered ItemBlock Model for " + AmethystMod.MODID + ":" + subNames[i]);
            }
        } else {
            ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + block.getBlockName(), "inventory");
            //itemModelMesher.register(GameRegistry.findItem(AmethystMod.MODID, block.getBlockName()), 0, res);
            ModelLoader.setCustomModelResourceLocation(GameRegistry.findItem(AmethystMod.MODID, block.getBlockName()), 0, res);
            ModelBakery.registerItemVariants(GameRegistry.findItem(AmethystMod.MODID, block.getBlockName()), res);
            FMLLog.log(Level.INFO, "Registered ItemBlock Model for " + AmethystMod.MODID + ":" + block.getBlockName());
        }
    }

    @Override
    public EntityPlayer getPlayerEntity(MessageContext ctx) {
        // Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
        // your packets will not work because you will be getting a client
        // player even when you are on the server! Sounds absurd, but it's true.

        // Solution is to double-check side before returning the player:
        return (ctx.side.isClient() ? Minecraft.getMinecraft().thePlayer : super.getPlayerEntity(ctx));
    }
}
