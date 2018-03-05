package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.print.Visitor;

/**
 *
 * @author songbao.yang
 * @date 2018/3/3
 */
public class LongInfo extends ConstantInfo {
    private int type = ConstantInfo.LONG_INFO;
    private float value;

    public LongInfo(ConstantPool pool) {
        super(pool);
    }

    public LongInfo(ConstantPool pool, float value) {
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
