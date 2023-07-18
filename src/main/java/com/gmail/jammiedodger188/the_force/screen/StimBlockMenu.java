package com.gmail.jammiedodger188.the_force.screen;

import com.gmail.jammiedodger188.the_force.ModBlocks;
import com.gmail.jammiedodger188.the_force.block.entity.StimChestBE;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ITEM_HANDLER;

public class StimBlockMenu extends AbstractContainerMenu {
    public final StimChestBE blockEntity;
    private final Level level;

    public StimBlockMenu(int id, Inventory inv, FriendlyByteBuf extraData) {
        this(id, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public StimBlockMenu(int id, Inventory inv, BlockEntity blockEntity) {
        super(ModMenuTypes.STIM_BLOCK_MENU.get(), id);
        checkContainerSize(inv, StimChestBE.SIZE);
        this.blockEntity = (StimChestBE) blockEntity;
        this.level = inv.player.level();

        addBEInventorySlots();
        addPlayerInventory(inv);
        addPlayerHotbar(inv);
    }

    private void addBEInventorySlots() {
        //creating slots for the menu
        this.blockEntity.getCapability(ITEM_HANDLER).ifPresent(handler -> {
            for (int y = 0; y < 3; ++y) {
                for (int x = 0; x < 9; ++x) {
                    int slotNo = x + y * 9;
                    int xPos = 18 * x + 8;
                    int yPos = 18 * y + 18;
                    this.addSlot(new SlotItemHandler(handler, slotNo, xPos, yPos));
                }
            }
        });
    }

    //   - Data Inventory (0-26)
//   - Player Inventory (27-53)
//   - Player Hotbar (54-62)
    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        System.out.println(quickMovedSlotIndex);
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();

            /*
            The following quick move logic can be simplified to if in data inventory,
            try to move to player inventory/hotbar and vice versa for containers
            that cannot transform data (e.g. chests).
            */

            // If the quick move was performed on the player inventory or hotbar slot
            if (quickMovedSlotIndex >= 27 && quickMovedSlotIndex < 63) {
                // Try to move the inventory/hotbar slot into the data inventory input slots
                if (!this.moveItemStackTo(rawStack, 0, 27, false)) {
                    // If cannot move and in player inventory slot, try to move to hotbar
                    if (quickMovedSlotIndex < 54) {
                        if (!this.moveItemStackTo(rawStack, 54, 63, false)) {
                            // If cannot move, no longer quick move
                            return ItemStack.EMPTY;
                        }
                    }
                    // Else try to move hotbar into player inventory slot
                    else if (!this.moveItemStackTo(rawStack, 27, 54, false)) {
                        // If cannot move, no longer quick move
                        return ItemStack.EMPTY;
                    }
                }
            }
            // Else if the quick move was performed on the data inventory input slots, try to move to player inventory/hotbar
            else if (!this.moveItemStackTo(rawStack, 54, 63, false)) {
                if(!this.moveItemStackTo(rawStack, 27, 54, false))
                // If cannot move, no longer quick move
                    return ItemStack.EMPTY;
            }

            if (rawStack.isEmpty()) {
                // If the raw stack has completely moved out of the slot, set the slot to the empty stack
                quickMovedSlot.set(ItemStack.EMPTY);
            } else {
                // Otherwise, notify the slot that the stack count has changed
                quickMovedSlot.setChanged();
            }

            /*
            The following if statement and Slot#onTake call can be removed if the
            menu does not represent a container that can transform stacks (e.g.
            chests).
            */
            if (rawStack.getCount() == quickMovedStack.getCount()) {
                // If the raw stack was unable to be moved to another slot, no longer quick move
                return ItemStack.EMPTY;
            }
            // Execute logic on what to do post move with the remaining stack
            quickMovedSlot.onTake(player, rawStack);
        }

        return quickMovedStack; // Return the slot stack
    }


    //Can we still open the container at this position?
    @Override
    public boolean stillValid(Player player) {
        return stillValid(ContainerLevelAccess.create(level, blockEntity.getBlockPos()),
                player, ModBlocks.STIM_CHEST.get());
    }

    private void addPlayerInventory(Inventory playerInventory) {
        int slotIndex;
        int xPos;
        int yPos;
        for (int y = 0; y < 3; ++y) { //each row in inventory
            for (int x = 0; x < 9; ++x) { //each slot in row
                slotIndex = 9 + x + y * 9; //add 9 for the hotbar slots
                xPos = x * 18 + 8;
                yPos = y * 18 + 84;
                this.addSlot(new Slot(playerInventory, slotIndex, xPos, yPos));
            }
        }
    }

    private void addPlayerHotbar(Inventory playerInventory) {
        int slotIndex;
        int xPos;
        int yPos;
        for (int x = 0; x < 9; ++x) { //each slot in row
            slotIndex = x;
            xPos = x * 18 + 8;
            yPos = 142;
            this.addSlot(new Slot(playerInventory, slotIndex, xPos, yPos));
        }
    }
}
