package com.dummyc0m.forgemod.amethyst.client;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.common.CommonProxy;
import com.dummyc0m.forgemod.amethyst.common.block.BlockSimple;
import com.dummyc0m.forgemod.amethyst.common.item.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Level;

/**
 * Created by Dummyc0m on 11/26/15.
 * client specific code
 */
public class ClientProxy extends CommonProxy {
    private ItemModelMesher itemModelMesher;

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerRenderers();
    }

    private void registerRenderers() {
        itemModelMesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
        registerItem(AMContent.itemMaterial);
        registerItem(AMContent.itemFragment);
        registerItem(AMContent.itemTool);
        registerSimpleBlock(AMContent.blockAmethystOre);
        registerSimpleBlock(AMContent.blockAmethystPoorOre);
    }

    private void registerItem(ItemBase item) {
        if(item.getHasSubtypes()) {
            String[] subNames = item.getSubNames();
            for(int i = 0; i < subNames.length; i++) {
                ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + subNames[i], "inventory");
                itemModelMesher.register(item, i, res);
                FMLLog.log(Level.INFO, "Registered Item Model for " + AmethystMod.MODID + ":" + subNames[i]);
            }
        } else {
            ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + item.getItemName(), "inventory");
            itemModelMesher.register(item, 0, res);
            FMLLog.log(Level.INFO, "Registered Item Model for " + AmethystMod.MODID + ":" + item.getItemName());
        }
    }

    private void registerSimpleBlock(BlockSimple block) {
        ModelResourceLocation res = new ModelResourceLocation(AmethystMod.MODID + ":" + block.getBlockName(), "inventory");
        itemModelMesher.register(GameRegistry.findItem(AmethystMod.MODID, block.getBlockName()), 0, res);
        FMLLog.log(Level.INFO, "Registered ItemBlock Model for " + AmethystMod.MODID + ":" + block.getBlockName());
    }

    private void registerBlock(Block block) {


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
