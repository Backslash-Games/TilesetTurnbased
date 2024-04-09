package rendering.scenes;

import debug.Console;
import rendering.Mesh;

import java.util.ArrayList;

public class Scene {
    // Scene definitions
    public String name = "";
    // Defines scene space
    public static SceneSpace sceneSpace = null;

    // Create a log of all meshes
    public ArrayList<SceneObject> sceneObjects = new ArrayList<>();

    public final String consoleTag = STR."\{Console.PURPLE}[SCENE]\{Console.ANSI_RESET}";

    public Scene(String name){
        this.name = name;
    }

    // Controls the setup of the scene
    public void RenderScene()
    {
        System.out.println(STR."\{consoleTag} RENDERING SCENE \{getName(true)}");
    }

    // Scene setup
    public void AddSceneObject(SceneObject sObj){
        System.out.println(STR."\{consoleTag} ADDING SCENE OBJECT \{sObj.getNameColoring()}");
        // Add to scene objects
        sceneObjects.add(sObj);
    }

    public ArrayList<Mesh> GetObjectMeshes(){
        ArrayList<Mesh> meshes = new ArrayList<>();

        for(int i = 0; i < sceneObjects.size(); i++)
            meshes.add(sceneObjects.get(i).getMesh());

        return meshes;
    }

    public String getName(boolean coloring){
        String retName = name;
        if(coloring){
            retName = STR."\{Console.RED}\{retName}\{Console.ANSI_RESET}";
        }
        return retName;
    }
}
