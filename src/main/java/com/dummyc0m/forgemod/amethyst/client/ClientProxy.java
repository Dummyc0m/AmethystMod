package com.dummyc0m.forgemod.amethyst.client;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.common.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by Dummyc0m on 11/26/15.
 * client specific code
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        registerRenderers();
    }

    private void registerRenderers() {
        RenderItem renderItem = Minecraft.getMinecraft().getRenderItem();
        ModelResourceLocation itemModelResourceLocation = new ModelResourceLocation(AmethystMod.MODID + ":" + AMContent.itemMaterial.getItemName(), "inventory");
        renderItem.getItemModelMesher().register(AMContent.itemMaterial, 0, itemModelResourceLocation);
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
