package de.skate702.craftingkeys.api;


import de.skate702.craftingkeys.manager.ContainerManager;
import net.minecraft.inventory.Container;

/**
 * Created by Marcel on 18.12.2016.
 */
public abstract class Api extends ContainerManager{


    /**
     * Creates a new ContainerManager with the given container and registers GUI
     *
     * @param container Creates an new container :)
     * @param name Name of the new Gui
     */
    Api(Container container, String name) {
        super(container);
        Gui gui = Gui.getInstance();
        gui.registerGui(name, new Helper.Guis() {public void configureGui() {initGui();}});
    }
    public abstract void initGui();


}
