/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Texturedic;

import ch.ubique.inieditor.IniEditor;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Kilian
 */
public class Util {

    public static String getHexString(int value){
        String hex = Integer.toString(value,16).toUpperCase();
        int size = 4;
        if(hex.length() > 4) size = 8;
        while(hex.length() != size){
            hex = "0" + hex;
        }
        hex = "0x" + hex;
        return hex;
    }

    public static String getStartOffset(int offset){
        return " - (" + getHexString(offset) + ")";
    }

    public static String getShaderName(int type){
        String ret = "Unknown";
        switch(type){
            case 0x2b5170fd:
                ret = "Texture";
                break;
            case 0x608799c6:
                ret = "SpecularTexture";
                break;
            case 0x46b7c64f:
                ret = "NormalTexture";
                break;
            case -718597665:
                ret = "DiffuseMap1";
                break;
            case 606121937:
                ret = "DiffuseMap2";
                break;
            case -64241494:
                ret = "Vector";
                break;
            case 376311761:
                ret = "Vector";
                break;
            case 1212833469:
                ret = "Vector";
                break;
            case -15634147:
                ret = "Vector";
                break;
            case -160355455:
                ret = "Vector";
                break;
            case -2078982697:
                ret = "Vector";
                break;
            case -677643234:
                ret = "Vector";
                break;
            case -1168850544:
                ret = "Vector";
                break;
            case 424198508:
                ret = "Vector";
                break;
            case 514782960:
                ret = "Vector";
                break;
            case -260861532:
                ret = "Matrix";
                break;
        }
        ret += " (" + type + ")";
        return ret;
    }

    public static String getShaderNameFromIni(int type){
        String ret = "Unknown";
        try {
            IniEditor shaders = new IniEditor();
            shaders.load("shaders.ini");
            if (shaders.hasOption("names", "" + type)) {
                ret = shaders.get("names", "" + type);
            }
        } catch (IOException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }

    public static String getShaderType(int type){
        String ret = "Unknown " + type;
        switch(type){
            case 0:
                ret = "Texture";
                break;
            case 4:
                ret = "Matrix";
                break;
            case 1:
                ret = "Vector";
                break;
        }
        return ret;
    }
    
    public static int getTotalMemSize(int Flags){
        return (getSystemMemSize(Flags) + getGraphicsMemSize(Flags));
    }

    public static int getSystemMemSize(int Flags) {
        return (int)(Flags & 0x7FF) << (int)(((Flags >> 11) & 0xF) + 8);
    }

    public static int getGraphicsMemSize(int Flags) {
        return (int)((Flags >> 15) & 0x7FF) << (int)(((Flags >> 26) & 0xF) + 8);
    }

    public static long genHash(String key) {
        int hash = 0;

        for (int i = 0; i < key.length(); i++) {
            hash += (key.charAt(i) & 0xFF);
            hash += (hash << 10);
            hash ^= (hash >>> 6);
        }
        hash += (hash << 3);
        hash ^= (hash >>> 11);
        hash += (hash << 15);
        long ret = hash & 0xffffffffL;
        return ret;
    }

}
