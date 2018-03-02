public class PluginManager {
    private final String rootDirectory;

    public PluginManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    public void loadPlugin(String pluginName) throws Exception {
        ClassLoader loader = new NewClassLoader(this.rootDirectory);
        try {
            Class plugin = Class.forName(pluginName, true, loader);
            Object object = plugin.newInstance();

        } catch (ClassNotFoundException cl) {

            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }
}

class PluginNotFoundException extends Exception {
    PluginNotFoundException (String s ) { super(s);}
}

