package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.loader.ClassFileLoader;
import pers.yangsongbao.minijvm.method.Method;

/**
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
public class MiniJvm {

    public void run(String[]classPaths , String className) throws ClassNotFoundException {

        ClassFileLoader classFileLoader = new ClassFileLoader();
        for (String classPath : classPaths) {
            classFileLoader.addClassPath(classPath);
        }

        MethodArea methodArea = MethodArea.getInstance();
        methodArea.setClzLoader(classFileLoader);

        ExecuteEngine executeEngine = new ExecuteEngine();

        className = className.replace(".", "/");
        Method mainMethod = methodArea.getMainMethod(className);
        executeEngine.execute(mainMethod);
    }
}
