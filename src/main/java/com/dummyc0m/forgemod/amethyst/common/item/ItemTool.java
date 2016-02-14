package com.dummyc0m.forgemod.amethyst.common.item;

import com.dummyc0m.forgemod.amethyst.client.util.LanguageUtil;
import com.dummyc0m.forgemod.amethyst.common.AMContent;
import com.dummyc0m.forgemod.amethyst.core.energy.INetworkConnector;
import com.dummyc0m.forgemod.amethyst.core.energy.NetManager;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBase;
import com.dummyc0m.forgemod.amethyst.core.util.ItemNBTUtil;
import com.dummyc0m.forgemod.amethyst.core.util.ToolUtil;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
        return ToolUtil.isToolEffective(itemstack, state) ? 10.0F : 1.0F;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        if (playerIn.isSneaking() && ItemNBTUtil.getTag(itemStackIn).hasKey("networkId")) {
            itemStackIn.getTagCompound().removeTag("networkId");
        }
        return itemStackIn;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null && te instanceof INetworkConnector) {
                if (playerIn.isSneaking()) {
                    String networkId = ((INetworkConnector) te).getNetworkId();
                    if (networkId == null) {
                        networkId = UUID.randomUUID().toString();
                        ((INetworkConnector) te).setNetworkId(networkId);
                        if (!NetManager.INSTANCE.addToNetwork((INetworkConnector) te)) {
                            FMLLog.warning("Failed to add connector at %s to Network %s", pos, networkId);
                        }
                    }
                    ItemNBTUtil.getTag(stack).setString("networkId", networkId);
                } else {
                    if (ItemNBTUtil.getTag(stack).hasKey("networkId")) {
                        ((INetworkConnector) te).setNetworkId(stack.getTagCompound().getString("networkId"));
                        if (!NetManager.INSTANCE.addToNetwork((INetworkConnector) te)) {
                            FMLLog.warning("Failed to add connector at %s to Network %s", pos, stack.getTagCompound().getString("networkId"));
                        }
                    }
                }
            } else if (playerIn.isSneaking()) {
                ItemNBTUtil.getTag(stack).removeTag("networkId");
            }
        }
        return false;
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return stack.getItemDamage() == 0 ? ImmutableSet.of(AMContent.TOOL_SCREWDRIVER) : ImmutableSet.of();
    }

    @Override
    public boolean isTooltip() {
        return true;
    }

    @Override
    public void formatTooltip(ItemStack stack, List tooltip, boolean shiftDown) {
        if (shiftDown && ItemNBTUtil.getTag(stack).hasKey("networkId")) {
            tooltip.add(LanguageUtil.getLocalizedStringFormatted(LanguageUtil.DESC_PREFIX + "networkIdDisplay", stack.getTagCompound().getString("networkId")));
        }
    }
}
