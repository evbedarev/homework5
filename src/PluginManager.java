
public class PluginManager {
    private final String rootDirectory;
    private ClassLoader loader;

    public PluginManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
        this.loader = new NewClassLoader(this.rootDirectory);
    }

    public Plugin loadPlugin(String pluginName) throws Exception {

//        loader.loadClass(pluginName);

        try {
            Class plugin = Class.forName(pluginName, true, loader);
            Object object = plugin.newInstance();
            return  (Plugin) object;

        } catch (ClassNotFoundException cl) {

            throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
        }
    }
}

class PluginNotFoundException extends Exception {
    PluginNotFoundException (String s ) { super(s);}
}

