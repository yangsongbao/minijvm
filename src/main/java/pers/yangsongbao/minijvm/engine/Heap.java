package pers.yangsongbao.minijvm.engine;

/**
 *
 * @author songbao.yang
 * @date 2018/3/18
 */
public class Heap {

    private static Heap instance = new Heap();
    private Heap() {
    }
    public static Heap getInstance(){
        return instance;
    }

    public JavaObject newObject(String clzName){
        JavaObject jo = new JavaObject(JavaObject.OBJECT);
        jo.setClassName(clzName);
        return jo;
    }
}
