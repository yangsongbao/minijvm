package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.print.Visitor;

/**
 * @author songbao.yang
 * @date 2018/3/3
 */
public class MethodTypeInfo extends ConstantInfo {
    private int type = ConstantInfo.METHODTYPE_INFO;
    private int descriptorIndex;

    public MethodTypeInfo(ConstantPool pool) {
        super(pool);
    }

    public MethodTypeInfo(ConstantPool pool, int descriptorIndex) {
        super(pool);
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitMethodTypeInfo(this);
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }
}
