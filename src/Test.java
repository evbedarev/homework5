import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws Exception {
        for (;;) {
            PluginManager pluginManager = new PluginManager(".");
            ClassLoader loader =
            Class clazz = Class.forName("TestModule", true, loader);
            Object object = clazz.newInstance();
            System.out.println(object);
            System.out.println(clazz.getClassLoader().getClass());
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }

    }
}
