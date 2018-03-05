import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class NewClassLoader extends ClassLoader {
    private String path;
    private Map cashClass = new HashMap();

    public NewClassLoader (String path) {
        this.path = path;
    }


    protected synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {

        Class result = findClass(name);
        System.out.println("Get name class " + name);

        if (resolve)
            resolveClass(result);
        return result;
    }

    /*
    *   Ищем класс в HashMap. Потом в файле.
    *   если не находим делегируем поиск системному загрузчику,
    *   Если находим записываем в HashMap класс который прочитали из байт-массива.
    *
     */
    protected  Class findClass(String name) throws ClassNotFoundException {

        File file = findFile(name, ".class");
        Class result = (Class) cashClass.get(name);

        if (result != null) {
            System.out.println("Call class " + name  + " from cash");
            return result;
        }

        if (file==null) {
            System.out.println("Delegate load class" + name + " to Parent Classloader ");
            return findSystemClass(name);
        }

        try {
            byte[] classByte = loadFileAsBytes(file);
            result = defineClass(name, classByte,0, classByte.length);

        } catch (IOException io) {
            System.out.println("Cannot load class " + name + " " + io);
        }

        cashClass.put(name, result);
        return result;
    }

    //Ищет файл по пути
    private File findFile(String name, String extension) {
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

    //Загружает файл в байт-массив и возвращает его
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


