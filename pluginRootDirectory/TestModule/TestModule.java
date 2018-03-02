public class TestModule implements Plugin{
    @Override
    public void run (String[] urls) {
        for (String str: urls) {
            System.out.println(str);
            System.out.println("Version 1.1");
        }
    }

}


