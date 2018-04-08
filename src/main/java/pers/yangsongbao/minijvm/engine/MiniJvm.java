package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.loader.ClassLoader;
import pers.yangsongbao.minijvm.method.Method;

/**
 * @author songbao.yang
 * @date 2018/3/8
 */
public class MiniJvm {

    public void run(String[] classPaths, String className) throws ClassNotFoundException {

        ClassLoader classLoader = new ClassLoader();
        for (String classPath : classPaths) {
            classLoader.addClassPath(classPath);
        }

        MethodArea methodArea = MethodArea.getInstance();
        methodArea.setClassLoader(classLoader);

        ExecuteEngine executeEngine = new ExecuteEngine();

        className = className.replace(".", "/");
        Method mainMethod = methodArea.getMainMethod(className);

        executeEngine.execute(mainMethod);
    }
}
