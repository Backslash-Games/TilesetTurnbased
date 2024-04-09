package winManagement;

import java.nio.IntBuffer;
import java.util.ArrayList;

import debug.Console;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import rendering.Mesh;
import rendering.scenes.Scene;

import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;
public class ProgramWindow {

    // Class properties
    private String winName = "Default";
    private long window;
    private ArrayList<Mesh> meshQueue = new ArrayList<>();
    private Scene currentScene = null;

    private final String consoleTag = STR."\{Console.BLUE}[WINDOW]\{Console.ANSI_RESET}";

    // Class constructor
    public ProgramWindow(String windowName, Scene currentScene){
        winName = windowName;
        this.currentScene = currentScene;
    }

    // -> Main function used to display the window
    public void run()
    {
        // Check if scene is set
        if(currentScene == null) {
            System.out.println(STR."\{consoleTag}\{Console.ERROR_TAG} No Scene Found");
            return;
        }

        // Init window
        init();
        // Run the loop
        loop();

        // Check for the window to close
        Callbacks.glfwFreeCallbacks(window);
        GLFW.glfwDestroyWindow(window);

        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }


    public void init()
    {
        System.out.println(STR."\{consoleTag} INITIALIZING WINDOW \"\{winName}\"");

        // Start error checker?
        GLFWErrorCallback.createPrint(System.err).set();

        // Check if GLFW was able to init
        if(!GLFW.glfwInit()) {
            throw new IllegalStateException(STR."\{consoleTag}\{Console.ERROR_TAG} Unable to initialize GLFW");
        }

        // Set window properties
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        // Create new window
        // -> Error check
        window = GLFW.glfwCreateWindow(640, 480, winName, NULL, NULL);
        if(window == NULL) {
            throw new IllegalStateException(STR."\{consoleTag}\{Console.ERROR_TAG} Unable to create GLFW Window");
        }

        // Enables keyboard input
        GLFW.glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {});

        // Allocate data on the stack?
        try(MemoryStack stack = stackPush()){
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            // Set the window size
            GLFW.glfwGetWindowSize(window, pWidth, pHeight);

            // Store video mode
            GLFWVidMode vidmode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());

            // Set window position
            GLFW.glfwSetWindowPos(window,(vidmode.width() - pWidth.get(0)) / 2,(vidmode.height() - pHeight.get(0)) / 2);

            // Set Focus on current window
            GLFW.glfwMakeContextCurrent(window);
            GLFW.glfwSwapInterval(1);
            // Show the window
            GLFW.glfwShowWindow(window);
        }
    }
    public void loop()
    {
        System.out.println(STR."\{consoleTag} STARTING LOOP ON \"\{winName}\"");

        // Establishes processes for the window
        GL.createCapabilities();

        // Render the current scene
        currentScene.RenderScene();
        meshQueue = currentScene.GetObjectMeshes();

        // Debug all logged meshes for rendering
        for(int i = 0; i < meshQueue.size(); i++) {
            if(meshQueue.get(i) == null)
                System.out.println(STR."\{Console.ERROR_TAG}\{consoleTag} MESH.Q AT VALUE \{i} NOT FOUND");
            System.out.println(STR."\{consoleTag} ADDED \{meshQueue.get(i).getNameColoring()} TO MESH QUEUE");
        }


        // Keep looping until the window is closed
        while(!GLFW.glfwWindowShouldClose(window)) {
            // Loop the color buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);

            // -> Renders all logged meshes
            // --> This will later become the scene definition
            for(int i = 0; i < meshQueue.size(); i++){
                Mesh cMesh = meshQueue.get(i);

                GL30.glBindVertexArray(cMesh.getVaoID());
                GL20.glEnableVertexAttribArray(0);
                GL11.glDrawElements(GL11.GL_TRIANGLES, cMesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
                GL20.glDisableVertexAttribArray(0);
                GL30.glBindVertexArray(0);
            }

            // Display update
            GLFW.glfwSwapBuffers(window);
            // Display sync
            GLFW.glfwPollEvents();
        }
    }

    // Adds a new mesh to the render queue
    public void BindMesh(Mesh mesh){
        meshQueue.add(mesh);
        System.out.println(STR."\{consoleTag} Adding new mesh with \{mesh.getVertexCount()} vertices");
    }
}
