package com.dummyc0m.forgemod.amethyst.api;

import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public interface IToolTip {
    void formatTooltip(ItemStack stack, List tooltip);
}
