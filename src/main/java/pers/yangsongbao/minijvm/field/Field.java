package pers.yangsongbao.minijvm.field;

import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 * @date 2017/12/17
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private ConstantPool constantPool;
    private List<AttributeInfo> attributes = new ArrayList<>();

    public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.constantPool = constantPool;
    }

    public static Field parse(ClassFile clzFile, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attrCount = iter.nextU2ToInt();

        ConstantPool constantPool = clzFile.getConstantPool();
        Field field = new Field(accessFlag, nameIndex, descIndex, constantPool);
        for (int i = 1; i <= attrCount; i++) {
            AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
            field.addAttribute(attributeInfo);
        }
        return field;
    }

    private void addAttribute(AttributeInfo attributeInfo) {
        attributes.add(attributeInfo);
    }

    @Override
    public String toString() {
        String name = ((Utf8Info) constantPool.getConstantInfo(this.nameIndex)).getValue();
        String desc = ((Utf8Info) constantPool.getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }
}
