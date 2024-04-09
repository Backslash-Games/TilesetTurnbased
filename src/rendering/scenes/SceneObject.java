package rendering.scenes;

import debug.Console;
import engine.space.Transform;
import rendering.Mesh;
import rendering.MeshRenderer;
import rendering.constants.MeshConst;

public class SceneObject {
    public String name = "";
    public Transform transform = null;
    private Mesh mesh = null;

    private final String consoleTag = STR."\{Console.GREEN}[SCENE OBJECT]\{Console.ANSI_RESET}";

    // -> Constructors
    public SceneObject(){
        this("SceneObj");
    }
    public SceneObject(String name){
        this(name, new Transform());
    }
    public SceneObject(String name, Transform transform){
        // First always set transform
        this.transform = transform;
        this.name = name;
        // Debug information
        System.out.println(STR."\{consoleTag} CREATING OBJECT \{getNameColoring()} -- DEFAULTING TO BOX");
    }

    public void setMesh(Mesh mesh){
        this.mesh = mesh;
    }

    // -> Pull Methods
    public Mesh getMesh(){
        return mesh;
    }
    public String getName(){
        return name;
    }
    public String getNameColoring(){
        return STR."\{Console.YELLOW}\{getName()}\{Console.ANSI_RESET}";
    }
}
