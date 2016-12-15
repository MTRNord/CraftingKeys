package de.skate702.craftingkeys.api;


import de.skate702.craftingkeys.config.Config;
import de.skate702.craftingkeys.util.LanguageLocalizer;
import de.skate702.craftingkeys.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created by Marcel on 15.12.2016.
 */
public class Gui extends GuiScreen{
    public Helper helper = new Helper();

    public int selectedButtonID = -1;
    public static final int GuiID = 702;

    public List<GuiButton> superButtonList = buttonList;
    public int superWidth = width;
    public int superHeight = height;
    public int[] keyValues = new int[]{};
    public int guiBasePosition;
    public int guiShowBasePosX;
    public int guiShowBasePosY;
    public GuiType guiShowType;
    public int guiShowState;
    public long lastTime = 0;
    public long currentTime;
    @SuppressWarnings("FieldCanBeLocal")
    public ArrayList<GuiButton> configButtons;
    public final int buttonSaveID = 901;
    public final int buttonAbortID = 902;

    public static final Color pureWhite = new Color(255, 255, 255, 255);
    public static final Color lightGray = new Color(128, 128, 128, 255);
    public static final Color highlight = new Color(86, 144, 72, 255);

    public Minecraft mc;

    public void save() {
        Config.keyTopLeft.set(keyValues[0]);
        Config.keyTopCenter.set(keyValues[1]);
        Config.keyTopRight.set(keyValues[2]);
        Config.keyCenterLeft.set(keyValues[3]);
        Config.keyCenterCenter.set(keyValues[4]);
        Config.keyCenterRight.set(keyValues[5]);
        Config.keyLowerLeft.set(keyValues[6]);
        Config.keyLowerCenter.set(keyValues[7]);
        Config.keyLowerRight.set(keyValues[8]);
        Config.keyInteract.set(keyValues[9]);
        Config.keyStack.set(keyValues[10]);
        Config.keyDrop.set(keyValues[11]);
        Config.syncConfig();
    }

    private void initKeyValues() {
        if (keyValues.length == 0) {
            keyValues = new int[]{
                    Config.keyTopLeft.getInt(),      // 0
                    Config.keyTopCenter.getInt(),    // 1
                    Config.keyTopRight.getInt(),     // 2
                    Config.keyCenterLeft.getInt(),   // 3
                    Config.keyCenterCenter.getInt(), // 4
                    Config.keyCenterRight.getInt(),  // 5
                    Config.keyLowerLeft.getInt(),    // 6
                    Config.keyLowerCenter.getInt(),  // 7
                    Config.keyLowerRight.getInt(),   // 8
                    Config.keyInteract.getInt(),     // 9
                    Config.keyStack.getInt(),        // 10
                    Config.keyDrop.getInt()};        // 11
        }
    }

    private void addStandardButtons() {
        // Add control buttons
        superButtonList.add((new GuiButton(buttonAbortID, superWidth - 53, 3, 50, 20, LanguageLocalizer.localize("craftingkeys.config.button.abort"))));
        superButtonList.add((new GuiButton(buttonSaveID, superWidth - 53, 26, 50, 20, LanguageLocalizer.localize("craftingkeys.config.button.save"))));
    }

    public void drawKeyValues() {
        for (int i = 0; i < keyValues.length; i++) {
            configButtons.get(i).displayString = Keyboard.getKeyName(keyValues[i]);
        }
    }

    private void addCraftingButtons() {
        configButtons = new ArrayList<GuiButton>();
        configButtons.add((new GuiButton(0, guiBasePosition - 60, height / 2 - 87, 20, 20, "")));
        configButtons.add((new GuiButton(1, guiBasePosition - 41, height / 2 - 87, 20, 20, "")));
        configButtons.add((new GuiButton(2, guiBasePosition - 22, height / 2 - 87, 20, 20, "")));
        configButtons.add((new GuiButton(3, guiBasePosition - 60, height / 2 - 68, 20, 20, "")));
        configButtons.add((new GuiButton(4, guiBasePosition - 41, height / 2 - 68, 20, 20, "")));
        configButtons.add((new GuiButton(5, guiBasePosition - 22, height / 2 - 68, 20, 20, "")));
        configButtons.add((new GuiButton(6, guiBasePosition - 60, height / 2 - 49, 20, 20, "")));
        configButtons.add((new GuiButton(7, guiBasePosition - 41, height / 2 - 49, 20, 20, "")));
        configButtons.add((new GuiButton(8, guiBasePosition - 22, height / 2 - 49, 20, 20, "")));
        configButtons.add((new GuiButton(9, guiBasePosition + 34, height / 2 - 67, 22, 20, "")));
        configButtons.add((new GuiButton(10, guiBasePosition + 105, height / 2 - 84, 50, 20, "")));
        configButtons.add((new GuiButton(11, guiBasePosition + 105, height / 2 - 46, 50, 20, "")));
        buttonList.addAll(configButtons);
        drawKeyValues();
    }

    private void showNextGui() {
        switch (guiShowState) {
            case 0:
                guiShowType = GuiType.FURNACE;
                break;
            case 1:
                guiShowType = GuiType.BREWINGSTAND;
                break;
            case 2:
                guiShowType = GuiType.INVENTORY;
                break;
            case 3:
                guiShowType = GuiType.ENCHANTMENT;
                break;
            case 4:
                guiShowType = GuiType.DISPENSER;
                break;
            case 5:
                guiShowType = GuiType.VILLAGER;
                break;
            case 6:
                guiShowType = GuiType.ANVIL;
                break;
        }
        if (guiShowState >= 6) {
            guiShowState = 0;
        } else {
            guiShowState++;
        }

    }

    public enum GuiType {
        ANVIL,
        FURNACE,
        BREWINGSTAND,
        ENCHANTMENT,
        INVENTORY,
        VILLAGER,
        DISPENSER
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        //Setting up
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        superDrawDefaultBackground(); //drawWorldBackground(0);

        //Title
        drawCenteredString(LanguageLocalizer.localize("craftingkeys.config.title"), width / 2, width / 2 - 115, pureWhite.getRGB());

        // Info-text and fake line
        drawCenteredString(LanguageLocalizer.localize("craftingkeys.config.description"), width/ 2, width / 2 - 10, pureWhite.getRGB());
        drawCenteredString("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -", width / 2, height / 2 + 8, lightGray.getRGB());

        // Key Info
        drawCenteredString(LanguageLocalizer.localize("craftingkeys.config.stack"), guiBasePosition + 130, height / 2 - 96, pureWhite.getRGB());
        drawCenteredString(LanguageLocalizer.localize("craftingkeys.config.drop"), guiBasePosition + 130, height / 2 - 58, pureWhite.getRGB());

        //Draw line to let it look better
        superDrawHorizontalLine(guiBasePosition - 86, guiBasePosition + 85, superHeight / 2 - 20, pureWhite.getRGB());

        // Draw Crafting Table
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(new ResourceLocation("textures/gui/container/crafting_table.png"));
        drawTexturedModalRect(guiBasePosition - 86, superHeight / 2 - 100, 1, 0, 174, 80);

        // Draw info
        // Insert Gui by selected Type
        currentTime = Minecraft.getSystemTime();
        int waitingTimeMS = 3000;
        if (currentTime - lastTime > waitingTimeMS) {
            showNextGui();
            GL11.glColor4f(0.5F, 0.5F, 0.5F, 1F);
            lastTime = Minecraft.getSystemTime();
        }
        System.out.println("ArraySize: " + helper.GuiArray.size());
        for (int i=0;i<helper.GuiArray.size();i++) {
            System.out.println("GuiArray: " + helper.GuiArray.get(i));
            System.out.println("guiShowType: " + guiShowType);
            System.out.println("Equal?  " + Objects.equals(helper.GuiArray.get(i).toString(), guiShowType.toString()));
            if (Objects.equals(helper.GuiArray.get(i).toString(), guiShowType.toString())) {
                System.out.println("FIRST IF");
                helper.GuiFuncArray.get(i).initGui();
                break;
            }
        }

        //Draw line to let it look better
        superDrawHorizontalLine(guiShowBasePosX - 86, guiShowBasePosX + 85, guiShowBasePosY + 80, lightGray.getRGB());

        // Super
        drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        // Fill vars
        int guiBaseOffset = 35;
        guiBasePosition = width / 2 - guiBaseOffset;

        guiShowBasePosX = width / 2 - 35;
        guiShowBasePosY = height / 2 + 25;

        guiShowType = GuiType.ANVIL;
        guiShowState = 0;
        lastTime = Minecraft.getSystemTime();
        currentTime = Minecraft.getSystemTime();

        // Init Config
        initKeyValues();

        // Init buttons
        addStandardButtons();
        addCraftingButtons();
    }


    @Override
    public void actionPerformed(GuiButton button) {
        if (button.id == buttonAbortID) {
            Logger.info("actionPerformed(b)", "Closing Crafting Keys GUI now!");
            mc.thePlayer.closeScreen();
        } else if (button.id == buttonSaveID) {
            save();
            Logger.info("actionPerformed(b)", "Saving & closing Crafting Keys GUI now!");
            mc.thePlayer.closeScreen();
        } else if (button.id >= 0 && button.id <= 11) {
            if (selectedButtonID == -1) {
                selectedButtonID = button.id;
                configButtons.get(selectedButtonID).displayString = "...";
            }
        }
    }

    @Override
    public void keyTyped(char character, int keyCode) {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            if (selectedButtonID != -1) {
                selectedButtonID = -1;
                drawKeyValues();
            } else {
                Logger.info("keyTyped(c,i)", "Trying to close inventory with esc.");
                superKeyTyped(character, keyCode);
            }

        } else if (selectedButtonID != -1) {
            if (!ArrayUtils.contains(keyValues, keyCode) || keyValues[selectedButtonID] == keyCode) { // No double keys
                keyValues[selectedButtonID] = keyCode;
                selectedButtonID = -1;
                drawKeyValues();
            }
        }
    }

    public void superDrawHorizontalLine(int startX, int endX, int y, int color) {
        drawHorizontalLine(startX, endX, y, color);
    }


    // Super
    public void superKeyTyped(char typedChar, int keyCode){
        try {
            super.keyTyped(typedChar, keyCode); // Enable standard gui closing
        } catch (Exception ignored) {
            // support for newer versions, just do nothing
        }

    }


    public void superDrawDefaultBackground() {
        drawDefaultBackground();
    }

    public void drawCenteredString(String text, int width, int height, int color) {
        drawCenteredString(fontRendererObj, text, width, height, color);
    }

    public void drawInfoString(int index, int posX, int posY) {
        drawCenteredString(Keyboard.getKeyName(keyValues[index]),
            guiShowBasePosX + posX - 86, guiShowBasePosY + posY, highlight.getRGB());
    }




    /**
     * Registers a new Gui to the GuiHelper View
     *
     * @param  GuiName  the Name of the Gui Item
     * @param  GuiFunc  Function to register as gui
     */
    public void registerGui(String GuiName, Helper.Guis GuiFunc){
        helper.addName(GuiName);
        helper.addFunc(GuiFunc);
    }
}