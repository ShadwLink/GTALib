/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdr;

import file_io.ByteReader;
import file_io.Vector4D;
import java.util.ArrayList;

/**
 *
 * @author Kilian
 */
public class ShaderParamVector {
    public int startOffset;
    public Vector4D Data;

    public void Read(ByteReader br) {
        startOffset = br.getCurrentOffset();
        Data = br.readVector();
        Data.print("Shader Vector: ");
    }

    public String[] getDataNames(){
        ArrayList<String> nameList = new ArrayList();

        nameList.add("Unkown Vector");

        String[] names = new String[nameList.size()];
        for(int i = 0; i < nameList.size(); i++){
            names[i] = nameList.get(i);
        }

        return names;
    }

    public String[] getDataValues(){
        ArrayList<String> valueList = new ArrayList();

        valueList.add(Data.getX() + ", " + Data.getY() + ", " + Data.getZ() + ", " + Data.getW());

        String[] values = new String[valueList.size()];
        for(int i = 0; i < valueList.size(); i++){
            values[i] = valueList.get(i);
        }

        return values;
    }

    public String getStartOffset(){
        return Util.getStartOffset(startOffset);
    }

}
