package com.gmail.jammiedodger188.the_force;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = the_force.MODID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class CreativeTab {

    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "the_force" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            Registries.CREATIVE_MODE_TAB, the_force.MODID
    );

    public static  final RegistryObject<CreativeModeTab> CREATIVE_MODE_TAB = CREATIVE_MODE_TABS.register(
            "creative_mode_tab",
            () -> CreativeModeTab.builder()
                    .withTabsBefore(CreativeModeTabs.COMBAT)
                    .title(Component.translatable("The Force Things"))
                    .icon(()->new ItemStack(ModItems.STIM_CHEST_ITEM.get()))
                    .displayItems((params,output)->{
                        output.accept(ModItems.STIM_CHEST_ITEM.get());
                        output.accept(ModItems.TRASK_CHOWDER.get());
                        output.accept(ModItems.STIM_CANISTER.get());
                    })
                    .build()
    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
