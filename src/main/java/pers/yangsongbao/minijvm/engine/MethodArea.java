package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.loader.ClassFileLoader;
import pers.yangsongbao.minijvm.method.Method;

import java.util.HashMap;
import java.util.Map;

/**
 * 在 Java 虚拟机中，方法区（Method Area） 是可供各条线程共享的运行时内存区域。
 * 它存储了每一个类的结构信息，例如运行时常量池（Runtime Constant Pool）、字段和方法数据、
 * 构造函数和普通方法的字节码内容、还包括一些在类、实例、接口初始化时用到的特殊方法
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
public class MethodArea {
    private ClassFileLoader clzLoader = null;
    private Map<String, ClassFile> loadedClasses;

    private MethodArea() {
        loadedClasses = new HashMap<>();
    }

    Method getMainMethod(String className) throws ClassNotFoundException {
        ClassFile classFile = findClassFile(className);
        return classFile.getMainMethod();
    }

    private ClassFile findClassFile(String className) throws ClassNotFoundException {
        ClassFile loadedClass = loadedClasses.get(className);
        if (loadedClass != null){
            return loadedClass;
        }

        ClassFile classFile = clzLoader.loadClass(className);
        if (classFile == null){
            throw new ClassNotFoundException(className);
        }
        loadedClasses.put(className, classFile);
        return classFile;
    }


    static MethodArea getInstance(){
        return MethodAreaHolder.INSTANCE;
    }

    private static class MethodAreaHolder {
        private static final MethodArea INSTANCE = new MethodArea();
    }

    public ClassFileLoader getClzLoader() {
        return clzLoader;
    }

    public void setClzLoader(ClassFileLoader clzLoader) {
        this.clzLoader = clzLoader;
    }
}
