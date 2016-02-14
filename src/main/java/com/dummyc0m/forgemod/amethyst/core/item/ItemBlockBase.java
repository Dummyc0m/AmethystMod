package com.dummyc0m.forgemod.amethyst.core.item;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.client.util.LanguageUtil;
import com.dummyc0m.forgemod.amethyst.core.IToolTip;
import com.dummyc0m.forgemod.amethyst.core.block.IBlockBase;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.List;

/**
 * Created by Dummyc0m on 12/3/15.
 */
public class ItemBlockBase extends ItemBlock implements IToolTip {
    public ItemBlockBase(Block block) {
        super(block);
        if(((IBlockBase) block).getSubNames() != null && ((IBlockBase) block).getSubNames().length > 1) {
            setHasSubtypes(true);
        }
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        for (int i = 0; i < ((IBlockBase) block).getSubNames().length; i++) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
        //block.getSubBlocks(itemIn, tab, subItems);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (((IBlockBase)block).getSubNames() != null) {
            String subName = itemStack.getItemDamage() < ((IBlockBase) block).getSubNames().length ? ((IBlockBase) block).getSubNames()[itemStack.getItemDamage()] : ((IBlockBase) block).getSubNames()[((IBlockBase) block).getSubNames().length - 1];
            return this.getUnlocalizedName() + "." + subName;
        }
        return this.getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        if(((IBlockBase)block).isTooltip()) {
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
