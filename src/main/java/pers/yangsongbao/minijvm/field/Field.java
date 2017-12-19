package pers.yangsongbao.minijvm.field;

import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.attribute.ConstantValue;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.constant.UTF8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

/**
 * @author songbao.yang
 * @date 2017/12/17
 */
public class Field {
    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private ConstantPool constantPool;
    private ConstantValue constValue;

    public Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.constantPool = constantPool;
    }

    public static Field parse(ConstantPool constantPool, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attrCount = iter.nextU2ToInt();

        Field field = new Field(accessFlag, nameIndex, descIndex, constantPool);
        for (int i = 1; i <= attrCount; i++) {
            int attrNameIndex = iter.nextU2ToInt();
            String attrName = constantPool.getUTF8String(attrNameIndex);
            if (AttributeInfo.CONST_VALUE.equals(attrName)) {
                int attrLen = iter.nextU4ToInt();
                ConstantValue constValue = new ConstantValue(attrNameIndex, attrLen);
                constValue.setConstValueIndex(iter.nextU2ToInt());
                field.setConstantValue(constValue);
            } else {
                throw new RuntimeException("the attribute " + attrName + " has not been implemented yet.");
            }
        }
        return field;
    }

    public void setConstantValue(ConstantValue constValue) {
        this.constValue = constValue;
    }

    @Override
    public String toString() {
        String name = ((UTF8Info) constantPool.getConstantInfo(this.nameIndex)).getValue();
        String desc = ((UTF8Info) constantPool.getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }
}
