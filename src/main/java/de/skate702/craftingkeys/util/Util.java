package de.skate702.craftingkeys.util;

// NEW_1_8 cpw.mods.fml => net.minecraftforge.fml
// NEW_1_9 net.minecraft.util.text.TextComponentTranslation;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * Provides basic utility methods. For input methods, look at InputUtil.
 */
public class Util {

    /**
     * Current Instance Client, used for a lot of operations.
     */
    public static final Minecraft client = FMLClientHandler.instance().getClient();
    /**
     * Only used by isFirstInWorldTick()
     */
    private static boolean firstInWorldTick = true;

    /**
     * Private Constructor. This is a utility class!
     */
    private Util() {
    }

    /**
     * Returns the current held item stack.
     *
     * @return A item stack
     */
    public static ItemStack getHeldStack() {
        return client.thePlayer.inventory.getItemStack();
    }

    /**
     * Returns if the current player is helding a item stack.
     *
     * @return True, if held stack != null
     */
    public static boolean isHoldingStack() {
        // NEW_1_11 return (getHeldStack().func_190916_E() != 0);
        return (getHeldStack().func_190916_E() != 0);
    }

    /**
     * Returns, if this is the first method call in a fresh opened world.
     *
     * @return True, if this is the first tick
     */
    public static boolean isFirstInWorldTick() {
        if (firstInWorldTick && client.theWorld != null) {
            firstInWorldTick = false;
            return true;
        } else if (client.theWorld == null) {
            firstInWorldTick = true;
        }
        return false;
    }

    /**
     * Prints a Message in chat with the given language key from lang-files.
     *
     * @param lang_key key from lang-file
     */
    public static void printMessage(String lang_key) {
        client.thePlayer.addChatMessage(new TextComponentTranslation(lang_key));
        // NEW_1_9 client.thePlayer.addChatMessage(new TextComponentTranslation(lang_key));
    }

}
