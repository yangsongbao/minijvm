package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.print.Visitor;

/**
 * @author songbao.yang
 * @date 2018/3/3
 */
public class IntegerInfo extends ConstantInfo {
    private int type = ConstantInfo.INTEGER_INFO;
    private int value;

    public IntegerInfo(ConstantPool pool) {
        super(pool);
    }

    public IntegerInfo(ConstantPool pool, int value) {
        super(pool);
        this.value = value;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitIntegerInfo(this);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
