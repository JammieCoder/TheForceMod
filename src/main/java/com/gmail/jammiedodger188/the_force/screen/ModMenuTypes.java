package com.gmail.jammiedodger188.the_force.screen;

import com.gmail.jammiedodger188.the_force.the_force;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.minecraftforge.registries.ForgeRegistries.Keys.MENU_TYPES;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(MENU_TYPES, the_force.MODID);

    public static final RegistryObject<MenuType<StimBlockMenu>> STIM_BLOCK_MENU = MENUS.register(
            "stim_block_menu", () -> IForgeMenuType.create(StimBlockMenu::new));


    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
