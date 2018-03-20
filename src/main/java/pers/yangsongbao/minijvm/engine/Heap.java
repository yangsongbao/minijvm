package pers.yangsongbao.minijvm.engine;

/**
 * 堆（Heap）是可供各条线程共享的运行时内存区域，也是供所有类实例和数组对象分配内存的区域
 * Java 堆在虚拟机启动的时候就被创建，它存储了被自动内存管理系统（Automatic Storage
 * Management System，也即是常说的“Garbage Collector（垃圾收集器）”）所管理的各种
 * 对象，这些受管理的对象无需，也无法显式地被销毁
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
