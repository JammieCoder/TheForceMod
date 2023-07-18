package com.gmail.jammiedodger188.the_force;

import com.gmail.jammiedodger188.the_force.item.StimCanisterItem;
import com.gmail.jammiedodger188.the_force.item.StimChestItem;
import com.gmail.jammiedodger188.the_force.item.TraskChowderItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "the_force" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, the_force.MODID);

    // Creates a new BlockItem with the id "the_force:stim_chest", combining the namespace and path
    public static final RegistryObject<StimChestItem> STIM_CHEST_ITEM = ITEMS.register("stim_chest", StimChestItem::new);

    //Trask Chowder food item creation with properties: can always be eaten, gives nutrition of 10, epic rarity
    public static final RegistryObject<TraskChowderItem> TRASK_CHOWDER = ITEMS.register("trask_chowder", TraskChowderItem::new);
    public static final RegistryObject<StimCanisterItem> STIM_CANISTER = ITEMS.register("stim_canister", StimCanisterItem::new);


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
