package com.gmail.jammiedodger188.the_force.block;

import com.gmail.jammiedodger188.the_force.block.entity.StimChestBE;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StimChestBlock extends Block implements EntityBlock {
    public StimChestBlock(){super(BlockBehaviour.Properties.of()
            .pushReaction(PushReaction.NORMAL)
            .mapColor(MapColor.COLOR_YELLOW)
            .strength(3));
    }

    /*BLOCK ENTITY*/


    @Override
    public @NotNull RenderShape getRenderShape(@NotNull BlockState state) {
        return RenderShape.MODEL;
    }




    @Override
    public void onRemove(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if(oldState.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if(blockEntity instanceof StimChestBE tempBE){
                tempBE.drops();
            }
        }
        super.onRemove(oldState, level, pos, newState, isMoving);
    }





    @Override
    public InteractionResult use(BlockState oldState, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        if(!level.isClientSide()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof StimChestBE)
                NetworkHooks.openScreen((ServerPlayer) player, (StimChestBE) blockEntity, pos);
            else
                throw new IllegalStateException("Our Container provider is missing!");

        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new StimChestBE(pos, state);
    }
}
