package pers.yangsongbao.minijvm.engine;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by songbao.yang on 2018/3/21.
 */
public class MiniJvmTest {
    String path;

    @Before
    public void setUp() throws Exception {
        path = "D:\\project\\Learn\\minijvm\\target\\classes";
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void run() throws Exception {
        String[] classPath = {path};
        MiniJvm miniJvm = new MiniJvm();
        miniJvm.run(classPath, "pers.yangsongbao.minijvm.test.Plane");
    }

}