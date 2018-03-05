package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.print.Visitor;

/**
 *
 * @author songbao.yang
 * @date 2018/3/3
 */
public class DoubleInfo extends ConstantInfo {
    private int type = ConstantInfo.DOUBLE_INFO;
    private double value;

    public DoubleInfo(ConstantPool pool) {
        super(pool);
    }

    public DoubleInfo(ConstantPool pool, double value) {
        super(pool);
        this.value = value;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {

    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
