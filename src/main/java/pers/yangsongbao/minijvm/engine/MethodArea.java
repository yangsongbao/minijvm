package pers.yangsongbao.minijvm.engine;

import lombok.extern.slf4j.Slf4j;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.loader.ClassFileLoader;
import pers.yangsongbao.minijvm.method.Method;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
@Slf4j
public class MethodArea {
    private ClassFileLoader clzLoader = null;
    private Map<String, ClassFile> loadedClasses;

    private MethodArea() {
        loadedClasses = new HashMap<>();
    }

    public Method getMainMethod(String className) throws ClassNotFoundException {
        ClassFile classFile = findClassFile(className);
        return classFile.getMainMethod();
    }

    public ClassFile findClassFile(String className) throws ClassNotFoundException {
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


    public static MethodArea getInstance(){
        return MethodAreaHolder.instance;
    }

    private static class MethodAreaHolder {
        private static final MethodArea instance = new MethodArea();
    }

    public ClassFileLoader getClzLoader() {
        return clzLoader;
    }

    public void setClzLoader(ClassFileLoader clzLoader) {
        this.clzLoader = clzLoader;
    }
}
