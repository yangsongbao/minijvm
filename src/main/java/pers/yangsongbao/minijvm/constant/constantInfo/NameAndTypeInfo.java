package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;

public class NameAndTypeInfo extends ConstantInfo {
    public int type = ConstantInfo.NAME_AND_TYPE_INFO;

    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeInfo(ConstantPool pool) {
        super(pool);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public void setDescriptorIndex(int descriptorIndex) {
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public int getType() {
        return type;
    }


    public String getName() {
        ConstantPool pool = this.getConstantPool();
        Utf8Info utf8Info1 = (Utf8Info) pool.getConstantInfo(nameIndex);
        return utf8Info1.getValue();
    }

    public String getTypeInfo() {
        ConstantPool pool = this.getConstantPool();
        Utf8Info utf8Info2 = (Utf8Info) pool.getConstantInfo(descriptorIndex);
        return utf8Info2.getValue();
    }

    @Override
    public String toString() {
        return "(" + getName() + "," + getTypeInfo() + ")";
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitNameAndType(this);

    }
}
