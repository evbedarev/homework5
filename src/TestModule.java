public class TestModule implements Plugin{
    public TestModule() {
        System.out.println("Class Test. Version 1.1");
        System.out.println(this.getClass().getClassLoader().toString());
    }

    public void run (String[] urls) {
        System.out.println("Class Test. Version 1.1");
    }

/*
    @Override
    public String toString() {
        return "Class Test. Version 1.1";
    }*/
}

