package pers.yangsongbao.minijvm.loader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pers.yangsongbao.minijvm.clz.ClassFile;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class ClassLoaderTest {

    private ClassLoader classLoader;

    @Before
    public void setUp() throws Exception {
        classLoader = new ClassLoader();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void loadClass() throws Exception {
        String classPath = "D:\\project\\Learn\\minijvm\\target\\classes";
        classLoader.addClassPath(classPath);
        String className = "pers.yangsongbao.minijvm.test.Plane";
        ClassFile classFile = classLoader.loadClass(className);

        //bytecode version jdk1.7
        Assert.assertTrue(classFile.getMinorVersion() == 0);
        Assert.assertTrue(classFile.getMajorVersion() == 51);

    }

    @Test
    public void addClassPath() throws Exception {

    }

    @Test
    public void getClassPath() throws Exception {

    }

}