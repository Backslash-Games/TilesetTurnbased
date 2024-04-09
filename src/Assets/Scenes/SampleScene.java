package Assets.Scenes;

import engine.space.Vector;
import rendering.Mesh;
import rendering.scenes.Scene;
import rendering.constants.MeshConst;
import rendering.scenes.SceneObject;
import rendering.scenes.SceneSpace;

import java.util.ArrayList;

public class SampleScene extends Scene {
    public SampleScene(String name) {
        super(name);
        // -> Define scene space
        sceneSpace = new SceneSpace(10);
    }

    @Override
    public void RenderScene() {
        super.RenderScene();
        // -> Log mesh in current window
        SceneObject box = new SceneObject("Box_1");
        box.transform.position = new Vector(2f, 2f);
        box.transform.size = new Vector(1, 1);
        box.setMesh(MeshConst.CreateBox(box));
        AddSceneObject(box);
        // -> Log mesh in current window
        SceneObject box2 = new SceneObject("Box_2");
        box2.transform.position = new Vector(18f, 2f);
        box2.transform.size = new Vector(1, 1);
        box2.setMesh(MeshConst.CreateBox(box2));
        AddSceneObject(box2);
    }
}
