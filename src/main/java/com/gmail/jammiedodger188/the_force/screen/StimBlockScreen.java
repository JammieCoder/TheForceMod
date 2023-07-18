package com.gmail.jammiedodger188.the_force.screen;

import com.gmail.jammiedodger188.the_force.the_force;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class StimBlockScreen extends AbstractContainerScreen<StimBlockMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(the_force.MODID,"textures/gui/stim_chest_gui1.png");

    public StimBlockScreen(StimBlockMenu menu, Inventory inv, Component component){
        super(menu, inv, component);
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        graphics.blit(TEXTURE, leftPos, topPos, 0 , 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float delta) {
        renderBackground(graphics);
        super.render(graphics, mouseX, mouseY, delta);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
