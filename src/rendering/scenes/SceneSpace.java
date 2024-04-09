package rendering.scenes;

import debug.Console;
import engine.space.Vector;
import org.lwjgl.opengl.GL30;

public class SceneSpace {
    // -> Defines the scale of tiles that makes up a scene space
    // --> Value equivalent to the total number of tiles rendered horizontally
    public int scale = 1;
    // -> Defines scene (0, 0)
    public Vector center = new Vector(-1, -1);

    private static final String consoleTag = STR."\{Console.YELLOW}[SPACE]\{Console.ANSI_RESET}";

    // -> Main Constructor
    public SceneSpace(){
        // -> Run constructor method
        System.out.println(STR."\{consoleTag} CREATING NEW SCENE SPACE \{Console.PURPLE}\{this}\{Console.ANSI_RESET}");
    }
    public SceneSpace(int scale){
        this.scale = scale;
        // -> Run constructor method
        System.out.println(STR."\{consoleTag} CREATING NEW SCENE SPACE \{Console.PURPLE}\{this}\{Console.ANSI_RESET}");
    }
    public SceneSpace(int scale, Vector center){
        this.scale = scale;
        this.center = center;
        // -> Run constructor method
        System.out.println(STR."\{consoleTag} CREATING NEW SCENE SPACE \{Console.PURPLE}\{this}\{Console.ANSI_RESET}");
    }

    public void SceneProjection(float[] positions, SceneObject sObj){
        // -> Get the adjustment amount
        float scaleMult = 1f / scale;
        Vector offset = Vector.Add(Vector.Mult(center, scale), sObj.transform.position);
        for(int i = 0; i < positions.length; i++){
            // Position object
            if((i % 3) == 0)
                positions[i] += offset.x;
            else if((i % 3) == 1)
                positions[i] += offset.y;
            // Scale object
            positions[i] *= scaleMult;
        }
    }


    // -> TO STRING
    @Override
    public String toString() {
        return STR."|\{scale}->\{center}|";
    }
}
