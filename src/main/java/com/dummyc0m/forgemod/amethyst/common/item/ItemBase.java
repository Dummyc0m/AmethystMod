package com.dummyc0m.forgemod.amethyst.common.item;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.api.IItemBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Dummyc0m on 11/28/15.
 * General amethyst item format, absorbed from other tutorials and mods.
 */
public class ItemBase extends Item implements IItemBase {
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
}
