package com.dummyc0m.forgemod.amethyst.common.item;

import com.dummyc0m.forgemod.amethyst.AmethystMod;
import com.dummyc0m.forgemod.amethyst.api.IBlockBase;
import com.dummyc0m.forgemod.amethyst.api.IToolTip;
import com.dummyc0m.forgemod.amethyst.client.util.LanguageUtil;
import com.dummyc0m.forgemod.amethyst.common.block.BlockBase;
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
        block.getSubBlocks(itemIn, tab, subItems);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        this.formatTooltip(stack, tooltip);
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        if (((IBlockBase)block).getSubNames() != null) {
            String subName = itemStack.getItemDamage() < ((BlockBase)block).getSubNames().length ? ((BlockBase)block).getSubNames()[itemStack.getItemDamage()] : ((BlockBase)block).getSubNames()[((BlockBase)block).getSubNames().length - 1];
            return this.getUnlocalizedName() + "." + subName;
        }
        return this.getUnlocalizedName();
    }

    @SideOnly(Side.CLIENT)
    @Override
    @SuppressWarnings("unchecked")
    public void formatTooltip(ItemStack stack, List tooltip) {
        if(((IBlockBase)block).isTooltip()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                LanguageUtil.formatTooltip(stack.getUnlocalizedName() + ".tooltip.hidden", tooltip);
            } else {
                LanguageUtil.formatTooltip(stack.getUnlocalizedName() + ".tooltip", tooltip);
                tooltip.add(LanguageUtil.getLocalization("lang." + AmethystMod.MODID + ".showTooltip"));
            }
        }
    }
}
