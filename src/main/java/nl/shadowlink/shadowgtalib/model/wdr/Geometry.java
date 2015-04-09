/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.shadowlink.shadowgtalib.model.wdr;

import nl.shadowlink.file_io.ByteReader;
import nl.shadowlink.shadowgtalib.utils.Utils;

/**
 * @author Kilian
 */
public class Geometry {
	private int startOffset;

	private int VTable;

	private int Unknown1;
	private int Unknown2;

	public int vertexBuffersOffset;

	private int Unknown3;
	private int Unknown4;
	private int Unknown5;

	public int indexBuffersOffset;

	private int Unknown6;
	private int Unknown7;
	private int Unknown8;
	public int IndexCount;
	public int FaceCount;
	public int VertexCount;
	public int PrimitiveType;	// RAGE_PRIMITIVE_TYPE
	private int Unknown9;
	public int VertexStride;
	private int Unknown10;
	private int Unknown11;
	private int Unknown12;
	private int Unknown13;

	public VertexBuffer vertexBuffer;
	public IndexBuffer indexBuffer;

	public void Read(ByteReader br) {
		// Message.displayMsgHigh("Geometry");

		startOffset = br.getCurrentOffset();

		VTable = br.ReadUInt32();

		// Message.displayMsgHigh("VTable: " + VTable);

		Unknown1 = br.ReadUInt32();
		Unknown2 = br.ReadUInt32();

		vertexBuffersOffset = br.ReadOffset();
		// Message.displayMsgHigh("VertexBufferOffset: " + vertexBuffersOffset);
		Unknown3 = br.ReadUInt32();
		Unknown4 = br.ReadUInt32();
		Unknown5 = br.ReadUInt32();

		indexBuffersOffset = br.ReadOffset();
		// Message.displayMsgHigh("IndexBufferOffset: " + indexBuffersOffset);
		Unknown6 = br.ReadUInt32();
		Unknown7 = br.ReadUInt32();
		Unknown8 = br.ReadUInt32();

		IndexCount = br.ReadUInt32();
		FaceCount = br.ReadUInt32();
		VertexCount = br.ReadUInt16();
		PrimitiveType = br.ReadUInt16();

		// Message.displayMsgHigh("IC: " + IndexCount);
		// Message.displayMsgHigh("FC: " + FaceCount);
		// Message.displayMsgHigh("VC: " + VertexCount);
		// Message.displayMsgHigh("PT: " + PrimitiveType);

		Unknown9 = br.ReadUInt32();

		VertexStride = br.ReadUInt16();
		Unknown10 = br.ReadUInt16();

		Unknown11 = br.ReadUInt32();
		Unknown12 = br.ReadUInt32();
		Unknown13 = br.ReadUInt32();

		// Data

		br.setCurrentOffset(vertexBuffersOffset);
		vertexBuffer = new VertexBuffer(br);

		br.setCurrentOffset(indexBuffersOffset);
		indexBuffer = new IndexBuffer(br);
	}

	public String[] getDataNames() {
		String[] names = new String[20];

		names[0] = "Unknown1";
		names[1] = "Unknown2";

		names[2] = "vertexBuffersOffset";

		names[3] = "Unknown3";
		names[4] = "Unknown4";
		names[5] = "Unknown5";

		names[6] = "indexBuffersOffset";

		names[7] = "Unknown6";
		names[8] = "Unknown7";
		names[9] = "Unknown8";
		names[10] = "IndexCount";
		names[11] = "FaceCount";
		names[12] = "VertexCount";
		names[13] = "PrimitiveType";	// RAGE_PRIMITIVE_TYPE
		names[14] = "Unknown9";
		names[15] = "VertexStride";
		names[16] = "Unknown10";
		names[17] = "Unknown11";
		names[18] = "Unknown12";
		names[19] = "Unknown13";

		return names;
	}

	public String[] getDataValues() {
		String[] values = new String[20];

		values[0] = "" + Unknown1;
		values[1] = "" + Unknown2;

		values[2] = Utils.getHexString(vertexBuffersOffset);

		values[3] = "" + Unknown3;
		values[4] = "" + Unknown4;
		values[5] = "" + Unknown5;

		values[6] = Utils.getHexString(indexBuffersOffset);

		values[7] = "" + Unknown6;
		values[8] = "" + Unknown7;
		values[9] = "" + Unknown8;
		values[10] = "" + IndexCount;
		values[11] = "" + FaceCount;
		values[12] = "" + VertexCount;
		values[13] = "" + PrimitiveType;	// RAGE_PRIMITIVE_TYPE
		values[14] = "" + Unknown9;
		values[15] = "" + VertexStride;
		values[16] = "" + Unknown10;
		values[17] = "" + Unknown11;
		values[18] = "" + Unknown12;
		values[19] = "" + Unknown13;

		return values;
	}

	public String toString() {
		return "Geometry";
	}

	public String getStartOffset() {
		return Utils.getStartOffset(startOffset);
	}
}
