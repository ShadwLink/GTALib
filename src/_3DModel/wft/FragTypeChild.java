/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _3DModel.wft;

import _3DModel.wdr.Util;
import file_io.ByteReader;
import file_io.Vector4D;

/**
 *
 * @author Kilian
 */
public class FragTypeChild {
    public int drwblOffset;

    public FragTypeChild(ByteReader br) {
        int VTable = br.ReadUInt32();
        //System.out.println("VTable: " + VTable);
        float unkFloat1 = br.readFloat();
        float unkFloat2 = br.readFloat();
        int unkZero = br.ReadUInt32();
        //System.out.println("UnkFloat1: " + unkFloat1);
        //System.out.println("UnkFloat2: " + unkFloat2);
        //System.out.println("UnkZero: " + unkZero);
        for(int i = 0; i < 8; i++){
            Vector4D unkVec = br.readVector();
            unkVec.print("UnkVec" + i);
        }
        drwblOffset = br.ReadOffset();
        //System.out.println("DrawableOffset: " + Util.getHexString(drwblOffset));
        int unkZero2 = br.ReadUInt32();
        //System.out.println("UnkZero2: " + unkZero2);
        int unkOffset1 = br.ReadOffset();
        //System.out.println("UnkOffset1: " + Util.getHexString(unkOffset1));
        int unkOffset2 = br.ReadOffset();
        //System.out.println("UnkOffset2: " + Util.getHexString(unkOffset2));
        int unkOffset3 = br.ReadOffset();
        //System.out.println("UnkOffset3: " + Util.getHexString(unkOffset3));
        int unkOffset4 = br.ReadOffset();
        //System.out.println("UnkOffset4: " + Util.getHexString(unkOffset4));

        int unkZero3 = br.ReadUInt32();
        //System.out.println("UnkZero3: " + unkZero3);
        int unkZero4 = br.ReadUInt32();
        //System.out.println("UnkZero4: " + unkZero4);
        int unkZero5 = br.ReadUInt32();
        //System.out.println("UnkZero5: " + unkZero5);
        int unkZero6 = br.ReadUInt32();
        //System.out.println("UnkZero6: " + unkZero6);

        int unkOffset5 = br.ReadOffset();
        //System.out.println("UnkOffset5: " + Util.getHexString(unkOffset5));

    }

}
