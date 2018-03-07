package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.print.Visitor;

/**
 * @author songbao.yang
 * @date 2018/3/3
 */
public class InterfaceMethodRefInfo extends ConstantInfo {
    private int type = ConstantInfo.INTERFACEMETHODREF_INFO;
    private int classInfoIndex;
    private int nameAndTypeIndex;

    public InterfaceMethodRefInfo(ConstantPool pool) {
        super(pool);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitInterfaceMethodRefInfo(this);
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }

    public void setNameAndTypeIndex(int nameAndTypeIndex) {
        this.nameAndTypeIndex = nameAndTypeIndex;
    }
}
