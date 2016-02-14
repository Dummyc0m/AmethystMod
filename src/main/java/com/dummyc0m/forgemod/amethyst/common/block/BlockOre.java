package com.dummyc0m.forgemod.amethyst.common.block;

import com.dummyc0m.forgemod.amethyst.core.block.BlockSimple;
import com.dummyc0m.forgemod.amethyst.core.item.IItemBase;
import com.dummyc0m.forgemod.amethyst.core.item.ItemBlockBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

import java.util.Random;

/**
 * Created by Dummyc0m on 12/12/2015.
 */
public class BlockOre extends BlockSimple {
    private IItemBase item;
    private int itemMeta;
    private int quantity;
    private int bonus;

    public BlockOre(String name, int harvestLevel, IItemBase item, int itemMeta, int quantity, int bonus) {
        super(Material.rock, name, ItemBlockBase.class);
        setHarvestLevel("pickaxe", harvestLevel);
        setHardness(3f);
        setResistance(5f);
        this.item = item;
        this.quantity = quantity;
        this.bonus = bonus + 1;
        this.itemMeta = itemMeta;
    }

    @Override
    public int quantityDropped(Random random) {
        return bonus <= 1 ? quantity : random.nextInt(bonus) + quantity;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return item == null ? Item.getItemFromBlock(this) : (Item) item;
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return bonus <= 1 ? quantity : random.nextInt(fortune + bonus) + quantity;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return itemMeta;
    }
}
