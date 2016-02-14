package com.dummyc0m.forgemod.amethyst.core;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public interface IToolTip {
    @SideOnly(Side.CLIENT)
    void formatTooltip(ItemStack stack, List tooltip, boolean shiftDown);
}
