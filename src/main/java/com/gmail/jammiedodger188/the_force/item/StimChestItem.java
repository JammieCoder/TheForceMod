package com.gmail.jammiedodger188.the_force.item;

import com.gmail.jammiedodger188.the_force.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class StimChestItem extends BlockItem {
    public StimChestItem(){super(ModBlocks.STIM_CHEST.get(),new Item.Properties().rarity(Rarity.COMMON));}
}
