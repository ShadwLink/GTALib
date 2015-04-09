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
public class IndexBuffer {
	public int startOffset;

	public int VTable;

	public int IndexCount;
	public int DataOffset;

	public int p1Offset;

	public byte[] RawData;

	public IndexBuffer(ByteReader br) {
		Read(br);
	}

	public void Read(ByteReader br) {
		startOffset = br.getCurrentOffset();

		VTable = br.ReadUInt32();
		// Message.displayMsgHigh("VTable: " + VTable);

		IndexCount = br.ReadUInt32();
		// Message.displayMsgHigh("IndexCount: " + IndexCount);

		DataOffset = br.ReadDataOffset();

		p1Offset = br.ReadOffset();

		ReadData(br);
	}

	public void ReadData(ByteReader br) {
		br.setSystemMemory(false);
		br.setCurrentOffset(DataOffset);
		RawData = br.toArray((int) (IndexCount * 2));
		br.setSystemMemory(true);
	}

	public String[] getDataNames() {
		String[] names = new String[4];
		names[0] = "IndexCount";
		names[1] = "DataOffset";
		names[2] = "p1Offset";
		names[3] = "RawData(Length)";              // byte bLocked, byte align

		return names;
	}

	public String[] getDataValues() {
		String[] values = new String[4];
		values[0] = "" + IndexCount;
		values[1] = Utils.getHexString(DataOffset);
		values[2] = Utils.getHexString(p1Offset);
		values[3] = "" + RawData.length;              // byte bLocked, byte align

		return values;
	}

	public String getStartOffset() {
		return Utils.getStartOffset(startOffset);
	}

}
