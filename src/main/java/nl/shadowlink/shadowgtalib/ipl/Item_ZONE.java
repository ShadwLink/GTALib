/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.shadowlink.shadowgtalib.ipl;

import com.nikhaldimann.inieditor.IniEditor;
import nl.shadowlink.file_io.ReadFunctions;
import nl.shadowlink.shadowgtalib.model.model.Vector3D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kilian
 */
public class Item_ZONE extends IPL_Item {
	private int gameType;

	public Vector3D posLowerLeft;
	public Vector3D posUpperRight;

	public Item_ZONE(int gameType) {
		this.gameType = gameType;
	}

	@Override
	public void read(String line) {
		// Message.displayMsgHigh(line);
	}

	@Override
	public void read(ReadFunctions rf) {
		posLowerLeft = Vector3D.readFromReadFunctions(rf);
		posUpperRight = Vector3D.readFromReadFunctions(rf);
		// display();
	}

	private void display() {
		System.out.println("LowerLeft: " + posLowerLeft.x + ", " + posLowerLeft.y + ", " + posLowerLeft.z);
		System.out.println("UpperRight: " + posUpperRight.x + ", " + posUpperRight.y + ", " + posUpperRight.z);
	}

	@Override
	public void read(ReadFunctions rf, IniEditor ini) {
		Logger.getLogger("IPL").log(Level.INFO, getClass().getSimpleName() + " not supported yet.");
	}

}
