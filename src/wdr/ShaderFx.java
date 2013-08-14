/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdr;

import file_io.ByteReader;
import file_io.Message;

/**
 *
 * @author Kilian
 */
public class ShaderFx {
    private int startOffset;

    public Shader shader;

    private int shaderNamePtr;
    private int shaderSpsPtr;

    private int Unknown14;
    private int Unknown15;
    private int Unknown16;
    private int Unknown17;

    public String ShaderName;
    public String ShaderSPS;

    public void Read(ByteReader br) {
        startOffset = br.getCurrentOffset();

        shader = new Shader(br);

        shaderNamePtr = br.ReadOffset();
        shaderSpsPtr = br.ReadOffset();

        //System.out.println("ShaderNamePtr: " + shaderNamePtr);
        //System.out.println("ShaderSpsPtr: " + shaderSpsPtr);

        Unknown14 = br.ReadUInt32();
        Unknown15 = br.ReadUInt32();
        Unknown16 = br.ReadUInt32();
        Unknown17 = br.ReadUInt32();

        // Data:

        if(shaderNamePtr != -1){
            br.setCurrentOffset(shaderNamePtr);
            ShaderName = br.ReadNullTerminatedString();
            System.out.println("ShaderName: " + ShaderName);
        }

        if(shaderSpsPtr != -1){
            br.setCurrentOffset(shaderSpsPtr);
            ShaderSPS = br.ReadNullTerminatedString();
            System.out.println("ShaderSPS: " + ShaderSPS);
        }

    }

    public String[] getDataNames(){
        String[] names = new String[8];
        names[0] = "shaderNamePtr";
        names[1] = "shaderSpsPtr";
        names[2] = "Unknown14";              // byte bLocked, byte align
        names[3] = "Unknown15";       // pLockedData
        names[4] = "Unknown16";
        names[5] = "Unknown17";
        names[6] = "ShaderName";
        names[7] = "ShaderSPS";             // piVertexBuffer

        return names;
    }

    public String[] getDataValues(){
        String[] values = new String[10];
        values[0] = Util.getHexString(shaderNamePtr);
        values[1] = Util.getHexString(shaderSpsPtr);
        values[2] = "" + Unknown14;              // byte bLocked, byte align
        values[3] = "" + Unknown15;       // pLockedData
        values[4] = "" + Unknown16;
        values[5] = "" + Unknown17;
        values[6] = "" + ShaderName;
        values[7] = "" + ShaderSPS;             // piVertexBuffer

        return values;
    }

    public String getStartOffset(){
        return Util.getStartOffset(startOffset);
    }

}
