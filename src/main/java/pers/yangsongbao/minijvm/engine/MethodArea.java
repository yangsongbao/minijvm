package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.MethodRefInfo;
import pers.yangsongbao.minijvm.loader.ClassLoader;
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
    private ClassLoader classLoader = null;
    private Map<String, ClassFile> loadedClasses;

    /**
     * 运行时常量池，未实现
     */

    private MethodArea() {
        loadedClasses = new HashMap<>();
    }

    public static MethodArea getInstance() {
        return MethodAreaHolder.INSTANCE;
    }

    Method getMainMethod(String className) {
        ClassFile classFile = findClassFile(className);
        return classFile.getMainMethod();
    }

    public ClassFile findClassFile(String className) {
        ClassFile loadedClass = loadedClasses.get(className);
        if (loadedClass != null) {
            return loadedClass;
        }

        ClassFile classFile = classLoader.loadClass(className);
        if (classFile == null) {
            throw new RuntimeException("do not find class: " + className);
        }
        loadedClasses.put(className, classFile);
        return classFile;
    }

    public Method getMethod(MethodRefInfo methodRef) {
        ClassFile classFile = this.findClassFile(methodRef.getClassName());
        Method m = classFile.getMethod(methodRef.getMethodName(), methodRef.getParamAndReturnType());
        if (m == null) {
            throw new RuntimeException("method can't be found : " + methodRef.toString());
        }
        return m;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    private static class MethodAreaHolder {
        private static final MethodArea INSTANCE = new MethodArea();
    }
}
