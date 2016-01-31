package com.dummyc0m.forgemod.amethyst.common.util;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

/**
 * Created by Dummyc0m on 12/3/15.
 */
public class ToolUtil {
    private ToolUtil() {
    }

    public static boolean isToolEffective(ItemStack stack, IBlockState state) {
        if (stack == null) {
            return state.getBlock().isToolEffective(null, state);
        }

        for (String type : stack.getItem().getToolClasses(stack))
        {
            if (state.getBlock().isToolEffective(type, state)) {
                return true;
            }
        }
        return false;
    }
}
