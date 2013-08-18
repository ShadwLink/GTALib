/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _3DModel.wdr;

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

}
