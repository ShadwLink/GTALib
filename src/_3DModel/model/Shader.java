/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package _3DModel.model;

/**
 *
 * @author Kilian
 */
public class Shader {
    private String textureName = "";
    private int glTex;

    /**
     * Create an empty shader
     */
    public Shader(){
    }

    /**
     * Returns the name of this texture
     * @return name of this texture
     */
    public String getTextureName() {
        return textureName;
    }

    /**
     * Set the name of the texture inside this shader
     * @param textureName name of the texture
     */
    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    /**
     * gl int of the tex
     * @param glTex gl int of the tex
     */
    public void setGLTex(int glTex){
        this.glTex = glTex;
    }

    /**
     * Returns the GL tex of this shader
     * @return the GL tex of this shader
     */
    public int getGLTex(){
        return glTex;
    }

}
