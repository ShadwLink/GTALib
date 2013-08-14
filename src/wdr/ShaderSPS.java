/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wdr;

import file_io.Message;
import file_io.ByteReader;

/**
 *
 * @author Kilian
 */
public class ShaderSPS {
    private int startOffset;

    private int Unknown14;
    private int Unknown15;
    private int Unknown16;
    private int Unknown17;

    public String ShaderName;
    public String ShaderSPS;

    public void Read(ByteReader br){
        startOffset = br.getCurrentOffset();

        Shader shader = new Shader(br);

        int shaderNamePtr = br.ReadOffset();
        int shaderSpsPtr = br.ReadOffset();

        Message.displayMsgHigh("ShaderNamePtr: " + shaderNamePtr);
        Message.displayMsgHigh("ShaderSpsPtr: " + shaderSpsPtr);

        Unknown14 = br.ReadUInt32();
        Unknown15 = br.ReadUInt32();
        Unknown16 = br.ReadUInt32();
        Unknown17 = br.ReadUInt32();

        // Data:

        br.setCurrentOffset(shaderNamePtr);
        ShaderName = br.ReadNullTerminatedString();

        Message.displayMsgHigh("Shadername: " + ShaderName);

        br.setCurrentOffset(shaderSpsPtr);
        ShaderSPS = br.ReadNullTerminatedString();

        Message.displayMsgHigh("ShaderSPS: " + ShaderSPS);
    }

    public String getStartOffset(){
        return Util.getStartOffset(startOffset);
    }
}
