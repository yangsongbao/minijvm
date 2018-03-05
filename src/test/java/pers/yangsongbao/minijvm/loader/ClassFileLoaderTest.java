package pers.yangsongbao.minijvm.loader;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.field.Field;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class ClassFileLoaderTest {

    private ClassFileLoader classFileLoader;

    @Before
    public void setUp() throws Exception {
        classFileLoader = new ClassFileLoader();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void loadClass() throws Exception {
        String classPath = "D:\\project\\Learn\\minijvm\\target\\classes";
        classFileLoader.addClassPath(classPath);
        String className = "pers.yangsongbao.minijvm.test.Plane";
        ClassFile classFile = classFileLoader.loadClass(className);

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