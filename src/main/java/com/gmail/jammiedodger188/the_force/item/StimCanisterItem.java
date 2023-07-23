package com.gmail.jammiedodger188.the_force.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class StimCanisterItem extends Item {
    public StimCanisterItem(){
        super(new Item.Properties()
                .food(new FoodProperties.Builder()
                        .nutrition(10)
                        .effect(() -> new MobEffectInstance(MobEffects.HEAL),1.0f)
                        .build())
                .rarity(Rarity.UNCOMMON)
                .stacksTo(3));}
}
