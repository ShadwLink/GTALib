/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wft;

import java.util.LinkedList;
import file_io.ByteReader;
import wdr.DrawableModel;
import wdr.Util;

/**
 *
 * @author Kilian
 */
public class FragTypeModel {
    public int VTable;
    public int blockMapAdress;
    public int offset1;
    public int offset2;

    public LinkedList<DrawableModel> drawables = new LinkedList();

    public void read(ByteReader br){
        System.out.println("--------------------\nHeader\n--------------------");
        VTable = br.ReadUInt32();
        System.out.println("VTable: " + VTable);
        blockMapAdress = br.ReadOffset();
        System.out.println("Block: " + Util.getHexString(blockMapAdress));

        float unkFloat1 = br.readFloat();
        float unkFloat2 = br.readFloat();
        System.out.println("UNKFloat1: " + unkFloat1);
        System.out.println("UNKFloat2: " + unkFloat2);

        for(int i = 0; i < 10; i++){
            br.readVector().print("UNK Vec" + i);
        }

        offset1 = br.ReadOffset();
        offset2 = br.ReadOffset();

        System.out.println("PackString Offset: " + Util.getHexString(offset1));
        int save = br.getCurrentOffset();
        br.setCurrentOffset(offset1);
        System.out.println("PackString: " + br.ReadNullTerminatedString());
        br.setCurrentOffset(save);


        System.out.println("Drawable: " + Util.getHexString(offset2));
        save = br.getCurrentOffset();
        br.setCurrentOffset(offset2);

        DrawableModel drwbl = new DrawableModel();
        drwbl.readSystemMemory(br);
        drawables.add(drwbl);

        br.setCurrentOffset(save);


        int zero1 = br.ReadUInt32();
        int zero2 = br.ReadUInt32();
        int zero3 = br.ReadUInt32();
        int max1 = br.ReadUInt32();
        int zero4 = br.ReadUInt32();
        System.out.println("Zero1: " + zero1);
        System.out.println("Zero2: " + zero2);
        System.out.println("Zero3: " + zero3);
        System.out.println("Max1: " + max1);
        System.out.println("Zero4: " + zero4);

        int offset3 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset3));
        save = br.getCurrentOffset();
        br.setCurrentOffset(offset3);
        int off = br.ReadOffset();
        System.out.println("Off = " + Util.getHexString(off));
        while(off != -1){
            int save2 = br.getCurrentOffset();
            br.setCurrentOffset(off);
            String name = br.ReadNullTerminatedString();
            System.out.println("Name: " + name);
            br.setCurrentOffset(save2);
            System.out.println(Util.getHexString(br.getCurrentOffset()));
            off =  br.ReadOffset();
        }
        br.setCurrentOffset(save);

        int offset4 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset4));
        int childListOffset = br.ReadOffset();
        System.out.println("ChildListOffset: " + Util.getHexString(childListOffset));

        int zero5 = br.ReadUInt32();
        int zero6 = br.ReadUInt32();
        int zero7 = br.ReadUInt32();

        int offset6 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset6));

        int zero8 = br.ReadUInt32();

        int offset7 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset7));
        int offset8 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset8));
        int offset9 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset9));
        int offset10 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset10));

        int zero9 = br.ReadUInt32();
        int zero10 = br.ReadUInt32();
        int zero11 = br.ReadUInt32();

        int offset11 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset11));
        int zero12 = br.ReadUInt32();
        int zero13 = br.ReadUInt32();
        int zero14 = br.ReadUInt32();
        int zero15 = br.ReadUInt32();

        int offset12 = br.ReadOffset();
        System.out.println("Unk offset: " + Util.getHexString(offset12));

        System.out.println("--------------------\nChildList\n--------------------");
        save = br.getCurrentOffset();
        br.setCurrentOffset(childListOffset);
        int childOffset = br.ReadOffset();
        while(childOffset != -1){
            int save2 = br.getCurrentOffset();
            System.out.println("ChildOffset: " + Util.getHexString(childOffset));
            if(childOffset < 0x0F0000){
                br.setCurrentOffset(childOffset);
                FragTypeChild ftc = new FragTypeChild(br);
                if(ftc.drwblOffset != -1){
                    br.setCurrentOffset(ftc.drwblOffset);
                    drwbl = new DrawableModel();
                    drwbl.readSystemMemory(br);
                    drawables.add(drwbl);
                }
                br.setCurrentOffset(save2);
            }
            childOffset = br.ReadOffset();
        }
        br.setCurrentOffset(save);
    }

}
