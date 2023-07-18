package com.gmail.jammiedodger188.the_force;

import com.gmail.jammiedodger188.the_force.block.entity.StimChestBE;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, the_force.MODID);

    public static final RegistryObject<BlockEntityType<StimChestBE>> STIM_CHEST = BLOCK_ENTITIES.register(
            "stim_chest",
            () -> BlockEntityType.Builder.of(StimChestBE::new, ModBlocks.STIM_CHEST.get()).build(null)
    );

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}
