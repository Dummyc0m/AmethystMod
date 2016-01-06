package com.dummyc0m.forgemod.amethyst.common.item;

import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.common.util.ToolUtil;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;

import java.util.Set;

/**
 * Created by Dummyc0m on 12/3/15.
 * For Screwdrivers and other wip tools
 */
public class ItemTool extends ItemBase {
    public ItemTool() {
        super("screwdriver", 1);
    }

    @Override
    public float getDigSpeed(ItemStack itemstack, IBlockState state) {
        return ToolUtil.isToolEffective(itemstack, state) ? 6.0F : 1.0F;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return stack.getItemDamage() == 0 ? ImmutableSet.of(AMContent.TOOL_SCREWDRIVER) : ImmutableSet.of();
    }
}
