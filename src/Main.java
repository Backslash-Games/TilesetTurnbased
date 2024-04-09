import Assets.Scenes.SampleScene;
import winManagement.ProgramWindow;

public class Main {
    public static void main(String[] args)
    {
        // -> Initialize window
        ProgramWindow window = new ProgramWindow("Game Window", new SampleScene("Test_1"));
        window.run();
    }
}