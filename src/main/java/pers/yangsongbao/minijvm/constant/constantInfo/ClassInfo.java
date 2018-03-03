package pers.yangsongbao.minijvm.constant.constantInfo;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;

public class ClassInfo extends ConstantInfo {
    private int type = ConstantInfo.CLASS_INFO;
    private int utf8Index;

    public ClassInfo(ConstantPool pool) {
        super(pool);
    }

    public String getClassName() {
        int index = getUtf8Index();
        Utf8Info utf8Info = (Utf8Info) constantPool.getConstantInfo(index);
        return utf8Info.getValue();
    }

    @Override
    public int getType() {
        return type;
    }

    public int getUtf8Index() {
        return utf8Index;
    }

    public void setUtf8Index(int utf8Index) {
        this.utf8Index = utf8Index;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitClassInfo(this);
    }
}
