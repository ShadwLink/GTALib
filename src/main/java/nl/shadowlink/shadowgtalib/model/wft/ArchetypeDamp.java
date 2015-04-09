/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nl.shadowlink.shadowgtalib.model.wft;

import nl.shadowlink.file_io.ByteReader;

/**
 * @author Kilian
 */
public class ArchetypeDamp {

	public void read(ByteReader br) {
		int VTable = br.ReadUInt32();
		System.out.println("VTable: " + VTable);

	}

}
