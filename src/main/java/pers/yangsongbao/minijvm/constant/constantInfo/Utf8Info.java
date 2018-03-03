package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;

public class Utf8Info extends ConstantInfo {
    private int type = ConstantInfo.UTF8_INFO;
    private int length;
    private String value;

    public Utf8Info(ConstantPool pool) {
        super(pool);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visistUTF8(this);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public int getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Utf8Info [type=" + type + ", length=" + length + ", value=" + value + ")]";
    }

}
