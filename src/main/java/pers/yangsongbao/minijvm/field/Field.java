package pers.yangsongbao.minijvm.field;

import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author songbao.yang
 * @date 2017/12/17
 */
public class Field {
    private static final Set<String> acceptableAttribute;

    static {
        acceptableAttribute = new HashSet<>();
        acceptableAttribute.add(AttributeInfo.CONSTANT_VALUE);
        acceptableAttribute.add(AttributeInfo.DEPRECATED);
        acceptableAttribute.add(AttributeInfo.SIGNATURE);
        acceptableAttribute.add(AttributeInfo.SYNTHETIC);
        acceptableAttribute.add(AttributeInfo.RUNTIME_VISIBLE_ANNOTATIONS);
        acceptableAttribute.add(AttributeInfo.RUNTIME_INVISIBLE_ANNOTATIONS);
    }

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private ConstantPool constantPool;
    private Map<String, AttributeInfo> attributes;

    private Field(int accessFlag, int nameIndex, int descriptorIndex, ConstantPool constantPool) {
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
            int attrNameIndex = iter.nextU2ToInt();
            iter.back(2);
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            if (acceptableAttribute.contains(attrName)) {
                AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
                field.addAttribute(attrName, attributeInfo);
            } else {
                throw new RuntimeException("unsupported field attribute " + attrName);
            }
        }
        return field;
    }

    private void addAttribute(String attrName, AttributeInfo attributeInfo) {
        attributes.put(attrName, attributeInfo);
    }

    @Override
    public String toString() {
        String name = ((Utf8Info) constantPool.getConstantInfo(this.nameIndex)).getValue();
        String desc = ((Utf8Info) constantPool.getConstantInfo(this.descriptorIndex)).getValue();
        return name + ":" + desc;
    }
}
