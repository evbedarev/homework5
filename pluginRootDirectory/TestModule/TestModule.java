public class TestModule implements Plugin{
    @Override
    void run (String[] urls) {
        for (String str: urls) {
            System.out.println(str);
        }
    }

}
