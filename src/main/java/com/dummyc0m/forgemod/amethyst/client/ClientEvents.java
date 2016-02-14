package com.dummyc0m.forgemod.amethyst.client;

import com.dummyc0m.forgemod.amethyst.client.util.LanguageUtil;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.core.util.ItemNBTUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Dummyc0m on 2/13/2016.
 */
public class ClientEvents {
    private final FontRenderer font = Minecraft.getMinecraft().fontRendererObj;

    @SubscribeEvent
    public void onRenderOverlayPost(RenderGameOverlayEvent.Post event) {
        EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
        if (player.getCurrentEquippedItem() != null) {
            ItemStack itemStack = player.getCurrentEquippedItem();
            if (itemStack.getItem() == AMContent.itemTool && itemStack.getItemDamage() == 0) {
                if (ItemNBTUtil.getTag(itemStack).hasKey("networkId")) {
                    String str = LanguageUtil.getLocalizedStringFormatted(LanguageUtil.DESC_PREFIX + "networkIdDisplay", itemStack.getTagCompound().getString("networkId"));
                    font.drawString(str, event.resolution.getScaledWidth() / 2 - font.getStringWidth(str) / 2, event.resolution.getScaledHeight() - GuiIngameForge.left_height - 10, 0xD4AF37);
                }
            }
        }
    }
}
