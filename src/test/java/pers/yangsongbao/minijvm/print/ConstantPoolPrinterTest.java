package pers.yangsongbao.minijvm.print;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.loader.ClassFileLoader;

import static org.junit.Assert.*;

/**
 * Created by songbao.yang on 2018/3/5.
 */
public class ConstantPoolPrinterTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void print() throws Exception {
        ClassFileLoader classFileLoader = new ClassFileLoader();
        String classPath = "D:\\project\\Learn\\minijvm\\target\\classes";
        classFileLoader.addClassPath(classPath);
        String className = "pers.yangsongbao.minijvm.test.Plane";
        ClassFile classFile = classFileLoader.loadClass(className);
        ConstantPool constantPool = classFile.getConstantPool();

        ConstantPoolPrinter constantPoolPrinter = new ConstantPoolPrinter(constantPool);
        constantPoolPrinter.print();

        System.out.println("----------------end-----------------------");

    }

}