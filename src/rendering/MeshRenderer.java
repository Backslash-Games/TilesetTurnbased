package rendering;

import java.nio.FloatBuffer; //The buffers that the Vertex data is ultimately stored in
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List; //List and ArrayLists are containers for storing data, in this case the VBO/VAO IDs

import debug.Console;
import org.lwjgl.BufferUtils; //For creating the FloatBuffer
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import rendering.scenes.Scene;
import rendering.scenes.SceneObject;
import rendering.scenes.SceneSpace;

public class MeshRenderer {
    // Contains VBOs of a mesh
    private static List<Integer> vaos = new ArrayList<Integer>();
    // Contains mesh's Vertex positions, indices, texture coordinates, and normals
    private static List<Integer> vbos = new ArrayList<Integer>();

    public static SceneObject boundSceneObject = null;

    private static String consoleTag = STR."\{Console.CYAN}[M.RENDERER]\{Console.ANSI_RESET}";

    // Creates a float buffer using input data
    private static FloatBuffer createFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        buffer.put(data); // Write mode
        buffer.flip(); // Read mode
        return buffer;
    }
    // Creates an int buffer using input data
    private static IntBuffer createIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        buffer.put(data); // Write mode
        buffer.flip(); // Read mode
        return buffer;
    }

    // Stores Mesh data
    // -> Used for creating VBOs
    // --> Attribute - Flags the type of data (Vertex Pos/TexCoords/Normals)
    // --> Dimensions - Flag for 2D vs 3D
    // --> Data - Info being loaded into the VBO
    private static void storeData(int attribute, int dimensions, float[] data) {
        int vbo = GL15.glGenBuffers(); //Creates a VBO ID
        vbos.add(vbo); // Adds VBO to Local ArrayList
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vbo); //Loads the current VBO to store the data
        FloatBuffer buffer = createFloatBuffer(data); // Creates float buffer based on input data
        // Modifies bound array buffer
        // -> Draws static
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(attribute, dimensions, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0); //Unloads the current VBO when done.
    }

    // Binds the indices
    private static void bindIndices(int[] data) {
        int vbo = GL15.glGenBuffers();
        vbos.add(vbo);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vbo);
        IntBuffer buffer = createIntBuffer(data);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    }

    // Creates a mesh based on positions and indices
    public static Mesh createMesh(float[] positions, int[] indices) {
        // -> Projects float array into scene
        if(boundSceneObject == null){
            System.out.println(STR."\{Console.ERROR_TAG}\{consoleTag} NO SCENE OBJECT BOUND. PROJECTION FAILED.");
            return new Mesh(0, 0);
        }
        Scene.sceneSpace.SceneProjection(positions, boundSceneObject);
        // -> Creates the mesh
        int vao = genVAO(); // Make new VAO
        storeData(0,3,positions); // Store Mesh data
        bindIndices(indices); // Bind the indices
        GL30.glBindVertexArray(0); // Bind the vertex array
        return new Mesh(vao,indices.length); // Return mesh
    }

    // Generates a VAO
    private static int genVAO() {
        int vao = GL30.glGenVertexArrays(); // Creates a new VAO
        vaos.add(vao); // Adds to the current list
        GL30.glBindVertexArray(vao); // Binds the vao
        return vao;
    }
}
