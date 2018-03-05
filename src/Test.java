import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        PluginManager pluginManager = new PluginManager("./pluginRootDirectory");

        for (;;) {
            String[] someStringMassive = new String[3];
            someStringMassive[0] = "12";

            pluginManager.loadPlugin("TestModule");

            new BufferedReader(new InputStreamReader(System.in)).readLine();

        }

    }
}
