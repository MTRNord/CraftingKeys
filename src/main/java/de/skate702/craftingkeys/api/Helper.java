package de.skate702.craftingkeys.api;


import java.util.ArrayList;

/**
 * Created by Marcel on 15.12.2016.
 */
public class Helper {

    private ArrayList<String> GuiArray;
    private ArrayList<Helper.Guis> GuiFuncArray;
    private ArrayList<String> GuiType;

    private static Helper instance;

    private Helper(){
        GuiArray = new ArrayList<String>();
        GuiFuncArray = new ArrayList<Guis>();
        GuiType = new ArrayList<String>();
    }

    public static Helper getInstance(){
        if (instance == null){
            instance = new Helper();
        }
        return instance;
    }

    public interface Guis {
        void configureGui();
    }

    public boolean addGuiType( String newGuiType ) {
        GuiType.add( newGuiType );
        return true;
    }

    public ArrayList<String> getGuiTypeArray() {
        return GuiType;
    }

    public static String getGuiType(int i) {
        return getInstance().getGuiTypeArray().get(i);
    }

    public static int getGuiTypeSize() {
        return getInstance().getGuiTypeArray().size();
    }

    public static String getNextGuiType(int i) {
        return getInstance().getGuiArray().get(i + 1);
    }

    public boolean addName( String newGuiName ) {
        GuiArray.add( newGuiName );
        return true;
    }

    public ArrayList<String> getGuiArray() {
        return GuiArray;
    }

    public ArrayList<Guis> getGuiFuncArray() {
        return GuiFuncArray;
    }

    public static String getName(int i) {
        return getInstance().getGuiArray().get(i);
    }

    public static int getNameSize() {
        return getInstance().getGuiArray().size();
    }

    public static boolean addGuiFunc( Helper.Guis newGuiFunc ) {
        getInstance().getGuiFuncArray().add( newGuiFunc );
        return true;
    }

    public static Guis getGuiFunc(int i) {
        return getInstance().getGuiFuncArray().get(i);
    }
}
