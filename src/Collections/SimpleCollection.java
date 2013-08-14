/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Collections;

import file_io.ByteReader;
import java.util.ArrayList;

/**
 *
 * @author Kilian
 */
public class SimpleCollection<T> {
    public ArrayList<T> Values;

    public int Count;
    public int Size;
    public int type;

    public SimpleCollection(ByteReader br, int type) {
        this.type = type;
        Read(br);
    }

    public void Read(ByteReader br) {
        int offset = br.ReadOffset();
        file_io.Message.displayMsgLow("Offset: " + offset);

        Count = br.ReadUInt16();
        Size = br.ReadUInt16();

        Values = new ArrayList<T>(Count);

        int save = br.getCurrentOffset();

        br.setCurrentOffset(offset);

        for (int i = 0; i < Count; i++) {
            Values.add(ReadData(br));
        }

        br.setCurrentOffset(save);
    }

    public T ReadData(ByteReader br){

        switch(type){
            case 0:
                Object data = br.ReadUInt32();
                file_io.Message.displayMsgLow("Data: " + data);
                return (T)data;
            default:
                Object data2 = br.ReadUInt32();
                return (T)data2;
        }
    }
}
