/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.shadowlink.shadowgtalib.model.wdr;

import nl.shadowlink.file_io.ByteReader;
import nl.shadowlink.shadowgtalib.model.model.Vector4D;
import nl.shadowlink.shadowgtalib.utils.Utils;
import java.util.ArrayList;

/**
 * @author Kilian
 */
public class ShaderParamVector {
	public int startOffset;
	public Vector4D Data;

	public void Read(ByteReader br) {
		startOffset = br.getCurrentOffset();
		Data = Vector4D.readFromByteReader(br);
		Data.print("Shader Vector: ");
	}

	public String[] getDataNames() {
		ArrayList<String> nameList = new ArrayList();

		nameList.add("Unkown Vector");

		String[] names = new String[nameList.size()];
		for (int i = 0; i < nameList.size(); i++) {
			names[i] = nameList.get(i);
		}

		return names;
	}

	public String[] getDataValues() {
		ArrayList<String> valueList = new ArrayList();

		valueList.add(Data.getX() + ", " + Data.getY() + ", " + Data.getZ() + ", " + Data.getW());

		String[] values = new String[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			values[i] = valueList.get(i);
		}

		return values;
	}

	public String getStartOffset() {
		return Utils.getStartOffset(startOffset);
	}

}
