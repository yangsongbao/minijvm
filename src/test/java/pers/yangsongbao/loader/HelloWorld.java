package pers.yangsongbao.loader;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class HelloWorld {

    private String defaultName = "yangsongbao";

    public void sayHello(String name) {
        if (name == null) {
            System.out.println("hello " + defaultName);
        } else {
            System.out.println("hello " + name);
        }
    }
}
