package com.gmail.jammiedodger188.the_force;

import com.gmail.jammiedodger188.the_force.block.StimChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "the_force" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, the_force.MODID);
    public static final RegistryObject<Block> STIM_CHEST = BLOCKS.register("stim_chest", StimChestBlock::new);
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
