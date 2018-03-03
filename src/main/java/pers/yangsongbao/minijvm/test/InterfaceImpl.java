package pers.yangsongbao.minijvm.test;

/**
 *
 * @author songbao.yang
 * @date 2018/3/3
 */
public class InterfaceImpl implements TestInterface {

    @Override
    public void test() {
        System.out.println("---------InterfaceImpl.test()");
    }
}
