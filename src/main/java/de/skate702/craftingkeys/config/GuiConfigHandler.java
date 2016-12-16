package de.skate702.craftingkeys.config;

// NEW_1_8 cpw.mods.fml => net.minecraftforge.fml

import de.skate702.craftingkeys.api.Gui;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiConfigHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == Gui.GuiID) {
            return Gui.getInstance();
        }

        return null;
    }


}

