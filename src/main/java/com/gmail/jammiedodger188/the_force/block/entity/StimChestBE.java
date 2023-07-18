package com.gmail.jammiedodger188.the_force.block.entity;

import com.gmail.jammiedodger188.the_force.ModBlockEntities;
import com.gmail.jammiedodger188.the_force.screen.StimBlockMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER;

public class StimChestBE extends BlockEntity implements MenuProvider{
    protected ItemStackHandler inventory;
    private LazyOptional<IItemHandler> inventoryOptional = LazyOptional.empty();
    public static final int SIZE = 27;

    public StimChestBE(BlockPos pos, BlockState state){
        super(ModBlockEntities.STIM_CHEST.get(), pos, state);
        inventory = createInventory();
    }

    //Name for menu
    @Override
    public @NotNull Component getDisplayName() {
        return Component.literal("Stim Chest");
    }

    //Reacts depending on which capability is requested for use
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ITEM_HANDLER)
            return inventoryOptional.cast();
        return super.getCapability(cap, side);
    }

    //Create inventory when it loads
    @Override
    public void onLoad() {
        super.onLoad();
        inventoryOptional = LazyOptional.of(() -> inventory);
    }

    private ItemStackHandler createInventory(){
        return new ItemStackHandler(SIZE){
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };

    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new StimBlockMenu(id, inventory, this);
    }

    //Load the inventory
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        inventory.deserializeNBT(nbt.getCompound("inventory"));
    }

    //Disables inventory capability among other defaults
    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        inventoryOptional.invalidate();
    }


    //Save inventory
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", inventory.serializeNBT());
        super.saveAdditional(nbt);
    }

    public void drops(){
        System.out.println(inventory.getSlots());
        SimpleContainer tempInventory = new SimpleContainer(inventory.getSlots());
        for(int i =0; i<SIZE; i++){
            System.out.println(inventory.getStackInSlot(i));
            tempInventory.setItem(i, inventory.getStackInSlot(i));
        }
        Containers.dropContents(level,worldPosition,tempInventory);
    }
}
