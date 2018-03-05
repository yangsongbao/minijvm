package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.print.Visitor;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class NullConstantInfo extends ConstantInfo {

    public NullConstantInfo() {

    }

    @Override
    public int getType() {
        return -1;
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
