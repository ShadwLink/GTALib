package nl.shadowlink.shadowgtalib.model.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import nl.shadowlink.file_io.ByteReader;
import nl.shadowlink.file_io.ReadFunctions;
import nl.shadowlink.file_io.WriteFunctions;

/**
 * @author Kilian
 */
public class Vector3D {

	public float x = 0.0f;
	public float y = 0.0f;
	public float z = 0.0f;

	public Vector3D() {
	}

	public Vector3D(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}

	public void plus(Vector3D v3d) {
		this.x += v3d.getX();
		this.y += v3d.getY();
		this.z += v3d.getZ();
	}

	public void minus(Vector3D v3d) {
		this.x -= v3d.getX();
		this.y -= v3d.getY();
		this.z -= v3d.getZ();
	}

	public void maal(Vector3D v3d) {
		this.x *= v3d.getX();
		this.y *= v3d.getY();
		this.z *= v3d.getZ();
	}

	public Vector3D maal(float maal) {
		this.x *= maal;
		this.y *= maal;
		this.y *= maal;
		return this;
	}

	public Vector4D normalize() {
		double norm;

		norm = 1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
		this.x *= norm;
		this.y *= norm;
		this.z *= norm;
		Vector4D v4d = new Vector4D(this.x, this.y, this.z, 0.0f);
		return v4d;
	}

	@Override
	public String toString() {
		return x + ", " + y + ", " + z;
	}

	/**
	 * Reads a Vector4D from a ByteReader
	 *
	 * @param pReadFunctions
	 *        The ReadFunctions to read the Vector4D from
	 * @return Vector4D read from the ByteReader
	 */
	public static Vector3D readFromReadFunctions(final ReadFunctions pReadFunctions) {
		float x = pReadFunctions.readFloat();
		float y = pReadFunctions.readFloat();
		float z = pReadFunctions.readFloat();
		return new Vector3D(x, y, z);
	}

	/**
	 * Writes a Vector3D to the WriteFunctions
	 *
	 * @param pVector3D
	 *        The Vector3D that should be written
	 * @param pWriteFunctions
	 *        The WriteFunctions where the Vector3D should be written to
	 */
	public static int writeToWriteFunctions(final Vector3D pVector3D, final WriteFunctions pWriteFunctions) {
		pWriteFunctions.writeFloat(pVector3D.x);
		pWriteFunctions.writeFloat(pVector3D.y);
		pWriteFunctions.writeFloat(pVector3D.z);
		return 12;
	}

}
