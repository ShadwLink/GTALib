/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.shadowlink.shadowgtalib.ipl;

import com.nikhaldimann.inieditor.IniEditor;
import nl.shadowlink.file_io.ReadFunctions;
import nl.shadowlink.file_io.WriteFunctions;
import nl.shadowlink.shadowgtalib.model.model.Vector3D;

/**
 * @author Kilian
 */
public class Item_GRGE extends IPL_Item {
	private int gameType;

	public Vector3D lowLeftPos;
	public float lineX, lineY;
	public Vector3D topRightPos;
	public int doorType;
	public int garageType;
	public int hash;
	public String name;
	public int unknown;

	Item_GRGE(int gameType) {
		this.gameType = gameType;
	}

	@Override
	public void read(String line) {
		// Message.displayMsgHigh(line);
	}

	@Override
	public void read(ReadFunctions rf) {

	}

	@Override
	public void read(ReadFunctions rf, IniEditor ini) {
		lowLeftPos = Vector3D.readFromReadFunctions(rf);
		lineX = rf.readFloat();
		lineY = rf.readFloat();
		topRightPos = Vector3D.readFromReadFunctions(rf);
		doorType = rf.readInt();
		garageType = rf.readInt();
		long tempHash = rf.readUnsignedInt();
		name = "" + tempHash;
		hash = (int) tempHash;
		name = ini.get("Hashes", name); // temp
		unknown = rf.readInt();
		// display();
	}

	private void display() {
		System.out.println("LowLeftPos: " + lowLeftPos.x + ", " + lowLeftPos.y + ", " + lowLeftPos.z);
		System.out.println("Line x: " + lineX + " y: " + lineY);
		System.out.println("TopRightPos: " + topRightPos.x + ", " + topRightPos.y + ", " + topRightPos.z);
		System.out.println("Doortype: " + doorType);
		System.out.println("Garagetype: " + garageType);
		System.out.println("Hash: " + hash + " name: " + name);
		System.out.println("Unknown: " + unknown);
	}

	public void write(WriteFunctions wf) {
		Vector3D.writeToWriteFunctions(lowLeftPos, wf);
		wf.writeFloat(lineX);
		wf.writeFloat(lineY);
		Vector3D.writeToWriteFunctions(topRightPos, wf);
		wf.writeInt(doorType);
		wf.writeInt(garageType);
		wf.writeInt(hash);
		wf.writeInt(unknown);
		display();
	}

}
