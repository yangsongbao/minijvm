package pers.yangsongbao.minijvm.constant;

import pers.yangsongbao.minijvm.print.Visitor;

/**
 * @author songbao.yang
 * @date 2017/12/17
 */
public abstract class ConstantInfo {

    public static final int UTF8_INFO = 1;
    public static final int INTEGER_INFO = 3;
    public static final int FLOAT_INFO = 4;
    public static final int LONG_INFO = 5;
    public static final int DOUBLE_INFO = 6;
    public static final int CLASS_INFO = 7;
    public static final int STRING_INFO = 8;
    public static final int FIELD_INFO = 9;
    public static final int METHOD_INFO = 10;
    public static final int INTERFACEMETHODREF_INFO = 11;
    public static final int NAME_AND_TYPE_INFO = 12;
    public static final int METHODHANDLE_INFO = 15;
    public static final int METHODTYPE_INFO = 16;
    public static final int INVOKEDYNAMIC_INFO = 18;

    protected ConstantPool constantPool;

    public ConstantInfo() {

    }

    public ConstantInfo(ConstantPool pool) {
        this.constantPool = pool;
    }

    public abstract int getType();

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ConstantInfo getConstantInfo(int index) {
        return this.constantPool.getConstantInfo(index);
    }

    public abstract void accept(Visitor visitor);

}
