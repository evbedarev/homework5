import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        for (;;) {
            String[] someStringMassive = new String[3];
            someStringMassive[0] = "123";
            PluginManager pluginManager = new PluginManager("./pluginRootDirectory");
            pluginManager.loadPlugin("TestModule");
//            plug.run(someStringMassive);
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }

    }
}
