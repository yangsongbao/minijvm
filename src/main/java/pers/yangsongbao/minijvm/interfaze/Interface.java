package pers.yangsongbao.minijvm.interfaze;

import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

/**
 * @author songbao.yang
 * @date 2018/3/3
 */
public class Interface {
    private int nameIndex;

    public Interface(int nameIndex) {
        this.nameIndex = nameIndex;
    }

    public static Interface parse(ConstantPool constantPool, ByteCodeIterator iter) {
        int nameIndex = iter.nextU2ToInt();
        return new Interface(nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public void setNameIndex(int nameIndex) {
        this.nameIndex = nameIndex;
    }
}
