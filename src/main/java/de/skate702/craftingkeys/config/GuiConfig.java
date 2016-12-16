package de.skate702.craftingkeys.config;

import de.skate702.craftingkeys.CraftingKeys;
import de.skate702.craftingkeys.api.Gui;
import de.skate702.craftingkeys.api.Helper;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;


@SuppressWarnings("NewExpressionSideOnly")
public class GuiConfig{
    Gui gui = Gui.getInstance();



    /**
     * 0,1,2 = top,
     * 3,4,5 = mid,
     * 6,7,8 = low,
     * 9     = interact,
     * 10    = stack,
     * 11    = drop
     */


    public void initDefaults(){
        gui.registerGui("FURNANCE", new Helper.Guis() { public void configureGui() {genFurnaceInfo();}});
        gui.registerGui("BREWINGSTAND", new Helper.Guis() {public void configureGui() {genBrewingStandInfo();}});
        gui.registerGui("DISPENSER",new Helper.Guis() {public void configureGui() {genDispenserInfo();}});
        gui.registerGui("ENCHANTMENT", new Helper.Guis() {public void configureGui() {genEnchantmentInfo();}});
        gui.registerGui("INVENTORY", new Helper.Guis() {public void configureGui() {genInventoryInfo();}});
        gui.registerGui("VILLAGER", new Helper.Guis() {public void configureGui() {genVillagerInfo();}});
        gui.registerGui("ANVIL", new Helper.Guis() {public void configureGui() {genAnvilInfo();}});
    }

    private void genFurnaceInfo() {
        Gui gui = Gui.getInstance();
        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/furnace.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        GL11.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 0, 100, 50, 50);

        gui.drawInfoString(1, 63, 21);
        gui.drawInfoString(4, 63, 57);
        gui.drawInfoString(9, 123, 39);
    }

    private void genBrewingStandInfo() {
        Gui gui = Gui.getInstance();
        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/brewing_stand.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        gui.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 200, 0, 50, 50);

        //NEW_1.11

        gui.drawInfoString(0, 24, 21);
        gui.drawInfoString(4, 86, 63);
        gui.drawInfoString(3, 64, 56);
        gui.drawInfoString(5, 109, 56);

        /*
        gui.drawInfoString(1, 86, 21);
        gui.drawInfoString(4, 86, 58);
        gui.drawInfoString(3, 63, 50);
        gui.drawInfoString(5, 109, 50);
        */
    }

    private void genEnchantmentInfo() {
        Gui gui = Gui.getInstance();
        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/enchanting_table.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        gui.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 150, 50, 50, 50);

        //NEW_1.11

        gui.drawInfoString(1, 22, 51);
        gui.drawInfoString(2, 42, 51);


        //gui.drawInfoString(1, 32, 51);
        // NEW_1_8 gui.drawInfoString(2, 42, 51);
    }


    private void genAnvilInfo() {
        Gui gui = Gui.getInstance();

        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/anvil.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        gui.drawRect(gui.guiShowBasePosX + -30, gui.guiShowBasePosY + 17, gui.guiShowBasePosX + 83, gui.guiShowBasePosY + 36, Color.black.getRGB());

        gui.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 100, 0, 50, 50);

        gui.drawInfoString(4, 34, 51);
        gui.drawInfoString(5, 83, 51);
        gui.drawInfoString(9, 141, 51);
    }

    private void genVillagerInfo() {
        Gui gui = Gui.getInstance();
        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/villager.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        gui.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 100, 100, 50, 50);

        gui.drawInfoString(4, 43, 57);
        gui.drawInfoString(5, 69, 57);
        gui.drawInfoString(9, 126, 58);
    }

    private void genInventoryInfo() {
        Gui gui = Gui.getInstance();
        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        gui.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 0, 0, 50, 50);

        gui.drawInfoString(1, 105, 22); //W
        gui.drawInfoString(2, 123, 22); //E
        gui.drawInfoString(4, 105, 40); //S
        gui.drawInfoString(5, 123, 40); //D
        gui.drawInfoString(9, 164, 32); //LCTRL
    }

    private void genDispenserInfo() {
        Gui gui = Gui.getInstance();
        gui.glColor4f(0.5F, 0.5F, 0.5F, 1F);
        gui.bindTexture(new ResourceLocation("textures/gui/container/dispenser.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX - 86, gui.guiShowBasePosY, 1, 0, 174, 80);

        gui.glColor4f(1F, 1F, 1F, 1F);
        gui.bindTexture(new ResourceLocation(CraftingKeys.MODID, "textures/gui/symbols.png"));
        gui.drawTexturedModalRect(gui.guiShowBasePosX + 105, gui.guiShowBasePosY + 17, 50, 50, 50, 50);

        gui.drawInfoString(0, 69, 21);
        gui.drawInfoString(1, 87, 21);
        gui.drawInfoString(2, 105, 21);

        gui.drawInfoString(3, 69, 39);
        gui.drawInfoString(4, 87, 39);
        gui.drawInfoString(5, 105, 39);

        gui.drawInfoString(6, 69, 57);
        gui.drawInfoString(7, 87, 57);
        gui.drawInfoString(8, 105, 57);
    }
}
