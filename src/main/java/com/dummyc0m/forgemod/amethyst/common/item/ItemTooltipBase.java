package com.dummyc0m.forgemod.amethyst.common.item;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.client.util.LanguageUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by Dummyc0m on 11/28/15.
 * Item base with tooltips
 */
public class ItemTooltipBase extends ItemBase {
    public ItemTooltipBase(String name, int stackSize, String... subNames) {
        super(name, stackSize, subNames);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        this.formatTooltip(stack, tooltip);
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void formatTooltip(ItemStack stack, List tooltip) {
        if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
            LanguageUtil.formatTooltip(stack.getUnlocalizedName() + ".tooltip.hidden", tooltip);
        } else {
            LanguageUtil.formatTooltip(stack.getUnlocalizedName() + ".tooltip", tooltip);
            tooltip.add(LanguageUtil.getLocalization("lang." + AmethystMod.MODID + ".showTooltip"));
        }
    }
}
