package fr.maefy.chat;

import java.awt.*;

public class Messages {

    private static final String PREFIX = Color.MAGENTA + "MAEFY >> ";

    public static String errorMessage(String message){
        return PREFIX + Color.RED + "ERREUR -> \n " + message;
    }

    public static String basicMessage(String message){
        return PREFIX + Color.BLUE + "MESSAGE -> \n " + message;
    }

}
