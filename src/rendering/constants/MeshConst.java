package rendering.constants;

import rendering.Mesh;
import rendering.MeshRenderer;
import rendering.scenes.Scene;
import rendering.scenes.SceneObject;

public class MeshConst {
    private static int boxesMade = 0;
    public static Mesh CreateBox(SceneObject sObj){
        MeshRenderer.boundSceneObject = sObj;

        float[] vertices = {-1f,-1f,0f,
                1f, -1f, 0f,
                1f, 1f, 0f,
                -1f, 1f, 0f};
        int[] indices = {0,1,2,
                0,2,3};
        Mesh box = MeshRenderer.createMesh(vertices,indices);
        box.setName(STR."Box_\{boxesMade}");
        boxesMade++;
        return box;
    }
}
