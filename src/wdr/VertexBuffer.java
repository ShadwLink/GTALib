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
public class VertexBuffer {
    public int startOffset;

    public int VTable;
    public int VertexCount;
    public int Unknown1;              // byte bLocked, byte align
    public int DataOffset;       // pLockedData
    public int StrideSize;
    public int vertexDeclOffset;
    public int Unknown2;
    public int DataOffset2;             // piVertexBuffer
    public int p2Offset;

    public byte[] RawData;

    public VertexDeclaration VertexDeclaration;

    public VertexBuffer()
    {
    }

    public VertexBuffer(ByteReader br) {
        Read(br);
    }

    public void ReadData(ByteReader br) {
        br.setSystemMemory(false);
        br.setCurrentOffset(DataOffset);
        RawData = br.toArray(VertexCount*StrideSize);
        br.setSystemMemory(true);
    }

    public void Read(ByteReader br) {
        startOffset = br.getCurrentOffset();
        VTable = br.ReadUInt32();

        VertexCount = br.ReadUInt16();
        Unknown1 = br.ReadUInt16();

        DataOffset = br.ReadDataOffset();
        Message.displayMsgHigh("DataOffset: " + DataOffset);

        StrideSize = br.ReadUInt32();

        vertexDeclOffset = br.ReadOffset();

        Unknown2 = br.ReadUInt32();

        DataOffset2 = br.ReadDataOffset();
        Message.displayMsgHigh("DataOffset2: " + DataOffset2);

        p2Offset = br.ReadOffset(); // null

        ReadData(br);

        //

        br.setCurrentOffset(vertexDeclOffset);
        VertexDeclaration = new VertexDeclaration(br);
    }

    public String[] getDataNames(){
        String[] names = new String[10];
        names[0] = "VTable";
        names[1] = "VertexCount";
        names[2] = "Unknown1";              // byte bLocked, byte align
        names[3] = "DataOffset";       // pLockedData
        names[4] = "StrideSize";
        names[5] = "vertexDeclOffset";
        names[6] = "Unknown2";
        names[7] = "DataOffset2";             // piVertexBuffer
        names[8] = "p2Offset";

        names[9] = "RawData(Length)";

        return names;
    }

    public String[] getDataValues(){
        String[] values = new String[10];
        values[0] = "" + VTable;
        values[1] = "" + VertexCount;
        values[2] = "" + Unknown1;              // byte bLocked, byte align
        values[3] = Util.getHexString(DataOffset);       // pLockedData
        values[4] = "" + StrideSize;
        values[5] = Util.getHexString(vertexDeclOffset);
        values[6] = "" + Unknown2;
        values[7] = Util.getHexString(DataOffset2);             // piVertexBuffer
        values[8] = Util.getHexString(p2Offset);

        values[9] = "" + RawData.length;

        return values;
    }

    public String getStartOffset(){
        return Util.getStartOffset(startOffset);
    }

}
