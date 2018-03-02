import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class NewClassLoader extends ClassLoader {
    private String path;

    public NewClassLoader (String path) {
        this.path = path;
    }


    protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class result = findClass(name);
        if (resolve)
            resolveClass(result);
        return result;
    }

    //
    protected  Class findClass(String name) throws ClassNotFoundException {
        File file = findFile(name, ".class");
        Class result = null;
        if (file==null)
            return findSystemClass(name);

        try {
            byte[] classByte = loadFileAsBytes(file);
            result = defineClass(name, classByte,0, classByte.length);
        } catch (IOException io) {
            System.out.println("Cannot load class " + name + " " + io);
        }
        return result;
    }

    //Ищет файл
    private File findFile(String name, String extension) {
        System.out.println(path + File.separatorChar + "TestModule" + File.separatorChar+ name + extension);
        File file = new File(path + File.separatorChar + "TestModule" + File.separatorChar+ name + extension);
        if (file.exists())
            return file;
        return null;
    }

    protected URL findResource(String name) {
        File file = findFile(name, "");
        if (file==null)
            return null;
        try {
            return file.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Загружает файл в байт-массив
    public static byte[] loadFileAsBytes (File file) throws IOException {
        byte[] result = new byte[(int)file.length()];
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            fileInputStream.read(result,0,result.length);
        } finally {
            fileInputStream.close();
        }
        return result;
    }

}


