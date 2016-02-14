package com.dummyc0m.forgemod.amethyst.core.item;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.client.util.LanguageUtil;
import com.dummyc0m.forgemod.amethyst.core.IToolTip;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by Dummyc0m on 11/28/15.
 * General amethyst item format, absorbed from other tutorials and mods.
 */
public class ItemBase extends Item implements IItemBase, IToolTip {
    private String itemName;
    private String[] subNames;

    public ItemBase(String name, int stackSize, String... subNames) {
        this.setMaxStackSize(stackSize);
        itemName = name;
        this.setCreativeTab(AmethystMod.creativeTab);
        if(subNames != null && subNames.length > 0) {
            this.setUnlocalizedName(AmethystMod.MODID + "." + name);
            this.setHasSubtypes(true);
            this.subNames = subNames;
            GameRegistry.registerItem(this, subNames[0]);
        } else {
            this.setUnlocalizedName(AmethystMod.MODID + "." + name);
            this.setHasSubtypes(subNames != null && subNames.length > 0);
            GameRegistry.registerItem(this, name);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (subNames != null) {
            String subName = itemStack.getItemDamage() < subNames.length ? subNames[itemStack.getItemDamage()] : "";
            return this.getUnlocalizedName() + "." + subName;
        }
        return this.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        if (subNames != null) {
            for (int i = 0; i < subNames.length; i++) {
                subItems.add(new ItemStack(this, 1, i));
            }
        } else {
            subItems.add(new ItemStack(this));
        }
    }

    @Override
    public String[] getSubNames() {
        return subNames;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public boolean isTooltip() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if (isTooltip()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                formatTooltip(stack, tooltip, true);
            } else {
                formatTooltip(stack, tooltip, false);
                tooltip.add(LanguageUtil.getLocalizedString("lang." + AmethystMod.MODID + ".showTooltip"));
            }
        }
    }

    @Override
    public void formatTooltip(ItemStack stack, List tooltip, boolean shiftDown) {
        if (shiftDown) {
            LanguageUtil.formatTooltip(stack.getUnlocalizedName() + ".tooltip.hidden", tooltip);
        } else {
            LanguageUtil.formatTooltip(stack.getUnlocalizedName() + ".tooltip", tooltip);
        }
    }
}
