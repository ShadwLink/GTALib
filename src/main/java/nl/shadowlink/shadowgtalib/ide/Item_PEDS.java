/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.shadowlink.shadowgtalib.ide;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Kilian
 */
public class Item_PEDS extends IDE_Item {
	private int gameType;

	public Item_PEDS(int gameType) {
		this.gameType = gameType;
	}

	@Override
	public void read(String line) {
		Logger.getLogger("IDE").log(Level.INFO, getClass().getSimpleName() + " not supported yet.");
	}

	public void save(BufferedWriter output) {
		try {
			String line = "";
			output.write(line + "\n");
			System.out.println("Line: " + line);
		} catch (IOException ex) {
			Logger.getLogger(Item_OBJS.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
