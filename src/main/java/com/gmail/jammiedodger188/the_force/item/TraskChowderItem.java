package com.gmail.jammiedodger188.the_force.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class TraskChowderItem extends Item{
    public TraskChowderItem(){super(new Item.Properties()
            .food(new FoodProperties.Builder()
                    .nutrition(10)
                    .build())
            .rarity(Rarity.EPIC));}
}
