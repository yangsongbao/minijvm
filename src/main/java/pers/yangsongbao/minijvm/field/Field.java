package pers.yangsongbao.minijvm.field;

import pers.yangsongbao.minijvm.attribute.*;
import pers.yangsongbao.minijvm.attribute.Deprecated;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;
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
    private Deprecated deprecated;
    private Signature signature;
    private Synthetic synthetic;

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
            int attrLen = iter.nextU4ToInt();
            String attrName = constantPool.getUTF8String(attrNameIndex);
            switch (attrName){
                case AttributeInfo.CONSTANT_VALUE:
                    ConstantValue constValue = new ConstantValue(attrNameIndex, attrLen);
                    constValue.setConstValueIndex(iter.nextU2ToInt());
                    field.setConstantValue(constValue);
                    break;
                case AttributeInfo.DEPRECATED:
                    Deprecated deprecated = new Deprecated(attrNameIndex, attrLen);
                    field.setDeprecated(deprecated);
                    break;
                case AttributeInfo.SIGNATURE:
                    Signature signature = new Signature(attrNameIndex, attrLen);
                    signature.setIgnatureIndex(iter.nextU2ToInt());
                    field.setSignature(signature);
                    break;
                case AttributeInfo.SYNTHETIC:
                    Synthetic synthetic = new Synthetic(attrNameIndex, attrLen);
                    field.setSynthetic(synthetic);
                    break;
                case AttributeInfo.RUNTIME_VISIBLE_ANNOTATIONS:
                    throw new RuntimeException("the filed attribute " + AttributeInfo.RUNTIME_VISIBLE_ANNOTATIONS + " has not been implemented yet.");
                case AttributeInfo.RUNTIME_INVISIBLE_ANNOTATIONS:
                    throw new RuntimeException("the filed attribute " + AttributeInfo.RUNTIME_INVISIBLE_ANNOTATIONS + " has not been implemented yet.");
                default:
                    throw new RuntimeException("the filed attribute " + attrName + " has not been implemented yet.");
            }
        }
        return field;
    }


    public void setConstantValue(ConstantValue constValue) {
        this.constValue = constValue;
    }

    public Deprecated getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(Deprecated deprecated) {
        this.deprecated = deprecated;
    }

    public Signature getSignature() {
        return signature;
    }

    public void setSignature(Signature signature) {
        this.signature = signature;
    }

    public Synthetic getSynthetic() {
        return synthetic;
    }

    public void setSynthetic(Synthetic synthetic) {
        this.synthetic = synthetic;
    }

    @Override
    public String toString() {
        String name = ((Utf8Info) constantPool.getConstantInfo(this.nameIndex)).getValue();
        String desc = ((Utf8Info) constantPool.getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }
}
