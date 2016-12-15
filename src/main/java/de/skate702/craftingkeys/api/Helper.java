package de.skate702.craftingkeys.api;


import java.util.ArrayList;

/**
 * Created by Marcel on 15.12.2016.
 */
public class Helper {

    public ArrayList<String> GuiArray = new ArrayList<String>();
    public ArrayList<Helper.Guis>  GuiFuncArray = new ArrayList<Helper.Guis>();

    public interface Guis {
        void initGui();
    }

    public boolean addName( String newGuiName ) {
        GuiArray.add( newGuiName );
        return true;
    }

    public String getName(int i) {
        return GuiArray.get(i);
    }

    public int getNameSize() {
        return GuiArray.size();
    }

    public boolean addGuiFunc( Helper.Guis newGuiFunc ) {
        GuiFuncArray.add( newGuiFunc );
        return true;
    }

    public Helper.Guis getGuiFunc(int i) {
        return GuiFuncArray.get(i);
    }
}
