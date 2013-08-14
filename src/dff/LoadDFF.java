/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dff;

import file_io.*;
import java.util.ArrayList;
import model.Polygon;
import model.Element;

/**
 *
 * @author Kilian
 */
public class LoadDFF extends Thread {
    private String dffFile;
    private Element mdl;
    private ByteReader br = null;

    private ArrayList<Polygon> tempPoly = new ArrayList();

    /**
     * Load a dff into one element
     * @param br the bytereader to load the dff from
     * @param kam if kam is used add the polygons differently
     * @param mdl the element to add the model data to
     */
    public LoadDFF(ByteReader br, Element mdl){
        this.br = br;
        this.mdl = mdl;
        loadModel();
    }

    /**
     * Unused multithreaded stuff
     */
    public void run() {
        loadModel();
    }

    /**
     * Load the model
     */
    public void loadModel(){
        //Message.displayMsgHigh("Started reading dff");
        int secID = 0;
        int secSize = 0;
        int secVersion = 0;
        int lastID = 0;

        int vertCount = 0; //holds the number of vertices
        int triCount = 0; //holds the number of polygons (triangles)
        int boneCount = 0; //holds the number of bones
        int specCount = 0; //holds the number of special indices
        int curTex = -1;
        int curPoly = 0;

        boolean loadedTexture = false;
        boolean triStrip = false;

        secID = br.ReadUInt32();
        secSize = br.ReadUInt32();
        secVersion = br.ReadUInt32();

        while(secID != 0){

            Message.displayMsgLow("Section ID: " + secID + "\nSection size: " + secSize + "\nSection version: 0x" + Integer.toString(secVersion,16));

            switch(secID){
                case Finals.rwDATA:
                    switch(lastID){
                        case Finals.rwCLUMP:
                            Message.displayMsgLow("Object Count: " + br.ReadUInt32());
                            if(secVersion != 0x800ffff){
                                Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                                Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                            }
                        break;
                        case Finals.rwFRAMELIST:
                            br.skipBytes(secSize);
                        break;
                        case Finals.rwGEOMETRYLIST:
                            Message.displayMsgLow("Geometry items: " + br.ReadUInt32());
                        break;
                        case Finals.rwGEOMETRY:
                            Message.displayMsgLow("Analyzing Geometry");
                            int flags = br.ReadUInt16();
                            Message.displayMsgLow("Flags: " + Integer.toString(flags, 2));
                            Message.displayMsgLow("Unknown: " + br.ReadUInt16());
                            triCount = br.ReadUInt32();
                            mdl.setPolygons(triCount);
                            Message.displayMsgHigh("Triangle Count: " + triCount);
                            vertCount = br.ReadUInt32();
                            mdl.setVertices(vertCount);
                            Message.displayMsgHigh("Vertex Count: " + vertCount);
                            Message.displayMsgLow("Morph Target Count: " + br.ReadUInt32());
                            if(secVersion == 0x800ffff){
                                Message.displayMsgLow("Ambient: " + br.readFloat());
                                Message.displayMsgLow("Diffuse: " + br.readFloat());
                                Message.displayMsgLow("Specular: " + br.readFloat());
                            }
                            if(br.hasFlag(flags, Finals.rwOBJECT_VERTEX_COLOR)){
                                Message.displayMsgLow("Model contains vertex colors");
                                //mdl.setHasVertexColors(true);
                                System.out.println("Vertex Colors");
                                for(int i = 0; i < vertCount; i++){
                                    //System.out.println("Vertex colors");
                                    byte red = br.ReadByte();
                                    byte blue = br.ReadByte();
                                    byte green = br.ReadByte();
                                    byte alpha = br.ReadByte();
                                    /*System.out.println("RED: " + red);
                                    System.out.println("BLUE: " + blue);
                                    System.out.println("GREEN: " + green);
                                    System.out.println("ALPHA: " + alpha);*/
                                    //.createVertexColors(red, blue, green, alpha);
                                }
                            }
                            if(br.hasFlag(flags, Finals.rwOBJECT_VERTEX_UV)){
                                Message.displayMsgLow("Model contains UV coords");
                                Message.displayMsgLow("Analyzing UV coords");
                                System.out.println("UV");
                                for(int i = 0; i < vertCount; i++){
                                    float u = br.readFloat();
                                    float v = br.readFloat();
                                    Message.displayMsgLow(i + " U: " + u + " V: " + v);
                                    if(!mdl.getHasVertexColors()){
                                        mdl.createModelVertex(i, 0, 0, 0, u, v);
                                    }else{
                                        mdl.createModelVertex(i, 0, 0, 0, u, v);
                                    }
                                }
                            }
                            if(br.hasFlag(flags, Finals.rwOBJECT_TRISTRIP)){
                                triStrip = false;
                            }else{
                                triStrip = true;
                            }
                            for(int i = 0; i < triCount; i++){
                                int b = br.ReadUInt16();
                                int a = br.ReadUInt16();
                                int flag = br.ReadUInt16();
                                int c = br.ReadUInt16();
                                //System.out.println(i + " a: " + a + " b: " + b + " c: " + c + " flags: " + flag);
                            }
                            Message.displayMsgLow("Bounding Sphere X: " + br.readFloat());
                            Message.displayMsgLow("Bounding Sphere Y: " + br.readFloat());
                            Message.displayMsgLow("Bounding Sphere Z: " + br.readFloat());
                            Message.displayMsgLow("Bounding Sphere Radius: " + br.readFloat());
                            Message.displayMsgLow("Has Position: " + br.ReadUInt32());
                            Message.displayMsgLow("Has Normals: " + br.ReadUInt32());
                            Message.displayMsgLow("Loading vertices");
                            System.out.println("Vertex Positions");
                            for(int i = 0; i < vertCount; i++){
                                float x = br.readFloat();
                                float y = br.readFloat();
                                float z = br.readFloat();
                                Message.displayMsgLow(i + " Vertex X: " + x + " Y: " + y + " Z: " + z);
                                mdl.createModelVertex(i, x, y, z, -1, -1);
                            }
                            if(br.hasFlag(flags, Finals.rwOBJECT_VERTEX_NORMAL)){
                                Message.displayMsgLow("Model contains normals");
                                mdl.hasNormals(true);
                                for(int i = 0; i < vertCount; i++){
                                    float normX = br.readFloat();
                                    float normY = br.readFloat();
                                    float normZ = br.readFloat();
                                    mdl.getVertex(i).setNormals(normX, normY, normZ);
                                    Message.displayMsgLow(i + " Normal X: " + normX + " Y: " + normY + " Z: " + normZ);
                                }
                            }
                        break;
                        case Finals.rwMATERIALLIST:
                            int matCount = br.ReadUInt32();
                            Message.displayMsgHigh("Material count: " + matCount);
                            for(int i = 0; i < matCount; i++){
                                Message.displayMsgLow("Material " + i + " Unknown int " + br.ReadUInt32());
                            }
                        break;
                        case Finals.rwMATERIAL:
                            mdl.createShader();
                            curTex++;
                            Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                            int red = br.ReadByte();
                            int green = br.ReadByte();
                            int blue = br.ReadByte();
                            int alpha = br.ReadByte();
                            //System.out.println(red + "," + green + "," + blue);
                            //mdl.addMaterial(red, green, blue, alpha, ms.getWhiteTexture());
                            //mdl.createModelPoly(flags, flags, secID);
                            Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                            Message.displayMsgLow("Texture Count: " + br.ReadUInt32());
                            Message.displayMsgLow("Unknown Float: " + br.readFloat());
                            Message.displayMsgLow("Unknown Float: " + br.readFloat());
                            Message.displayMsgLow("Unknown Float: " + br.readFloat());
                        break;
                        case Finals.rwTEXTURE:
                            Message.displayMsgLow("Texture filter mode flags: " + br.ReadUInt16());
                            Message.displayMsgLow("Unknown: " + br.ReadUInt16());
                        break;
                        case Finals.rwATOMIC:
                            Message.displayMsgLow("Frame Number: " + br.ReadUInt32());
                            Message.displayMsgLow("Geometry Number: " + br.ReadUInt32());
                            Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                            Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                        break;
                    }
                break;
                case Finals.rwFRAME:
                    Message.displayMsgHigh("Object name: " + br.ReadString(secSize));
                break;
                case Finals.rwSTRING:
                    String texName = br.ReadNullTerminatedString(secSize);
                    if(!loadedTexture){
                        mdl.getShader(curTex).setTextureName(texName);
                        Message.displayMsgLow("Texture: " + texName);
                        Message.displayMsgHigh("Added shader: " + texName + " " + texName.length());
                        loadedTexture = true;
                    }else{
                        Message.displayMsgLow("Texture name: " + texName);
                        loadedTexture = false;
                    }
                break;
                case Finals.rwMATERIALSPLIT:
                    Message.displayMsgLow("Triangle Strip: " + br.ReadUInt32());
                    int splitCount = br.ReadUInt32();
                    Message.displayMsgLow("Split Count: " + splitCount);
                    Message.displayMsgLow("Face Count: " + br.ReadUInt32());
                    Message.displayMsgLow("Analyzing strips");
                    for(int i = 0; i < splitCount; i++){
                        Message.displayMsgLow("Anazlyzing strip " + i);
                        int faceIndex = br.ReadUInt32();
                        int matIndex = br.ReadUInt32();
                        Message.displayMsgLow("FaceIndex: " + faceIndex);
                        Message.displayMsgLow("MatIndex: " + matIndex);
                        mdl.createStrip(faceIndex, matIndex);
                        int polyA = -1;
                        int polyB = -1;
                        int polyC = -1;
                        int newPoly = -1;
                        int i3 = 0;
                        int resetCount = 0;
                        if(triStrip){
                            for(int i2 = 0; i2 < (faceIndex/3); i2++){
                                polyA = br.ReadUInt32();
                                polyB = br.ReadUInt32();
                                polyC = br.ReadUInt32();
                                mdl.createModelPoly(polyA, polyB, polyC, i, true);
                                Message.displayMsgLow(i2 + " Poly: " + polyA + ", " + polyB + ", " + polyC);
                            }
                        }else{
                            for(int i2 = 0; i2 < (faceIndex); i2++){
                                newPoly = br.ReadUInt32();
                                polyA = polyB;
                                polyB = polyC;
                                polyC = newPoly;
                                if(polyA != -1){

                                    if(polyA != polyB && polyA != polyC && polyB != polyC){
                                        if((resetCount%2) == 0){
                                            if((i3%2) == 0){
                                                //System.out.println("1 " + i3 + ": " + polyA + " " + polyB + " " + polyC);
                                                mdl.createModelPoly(polyA, polyB, polyC, i, true);
                                            }else{
                                                //System.out.println("2 " + i3 + ": " + polyB + " " + polyA + " " + polyC);
                                                mdl.createModelPoly(polyB, polyA, polyC, i, true);
                                            }
                                        }else{
                                            if((i3%2) != 0){
                                                //System.out.println("1 " + i3 + ": " + polyA + " " + polyB + " " + polyC);
                                                mdl.createModelPoly(polyA, polyB, polyC, i, true);
                                            }else{
                                                //System.out.println("2 " + i3 + ": " + polyB + " " + polyA + " " + polyC);
                                                mdl.createModelPoly(polyB, polyA, polyC, i, true);
                                            }
                                        }
                                        Message.displayMsgLow(i2 + " Poly: " + polyA + ", " + polyB + ", " + polyC);
                                        i3++;
                                    }else{
                                        resetCount++;
                                    }
                                }
                            }
                        }
                    }
                break;
                case 39056125:
                    Message.displayMsgLow("Unknown: " + br.ReadUInt32());
                break;
                case 39056121:
                    br.skipBytes(secSize);
                break;
                case Finals.rwHANIM_PLG:
                    br.skipBytes(secSize);
                break;
                case Finals.rwSKIN_PLG:
                    br.skipBytes(secSize);
                break;
                case Finals.rwRTR:
                    br.skipBytes(secSize);
                break;
                case Finals.rwSkyMipmapVal:
                    br.skipBytes(secSize);
                break;

            }
            lastID = secID;
            if(br.moreToRead() > 16){
                secID = br.ReadUInt32();
                secSize = br.ReadUInt32();
                secVersion = br.ReadUInt32();
            }else{
                secID = -1;
            }
            if(secID == -1) break;
        }
        Message.displayMsgHigh("Finished analyzing DFF");
        mdl.createCenter();
        //mdl.displayBounds();
    }
}
