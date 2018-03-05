package pers.yangsongbao.minijvm.method;

import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 */
public class Method {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    List<AttributeInfo> attributes = new ArrayList<>();

    private ClassFile clzFile;

    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public void addAttribute(AttributeInfo attributeInfo){
        attributes.add(attributeInfo);
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attribCount = iter.nextU2ToInt();

        Method method = new Method(clzFile, accessFlag, nameIndex, descIndex);
        for (int j = 1; j <= attribCount; j++) {
            AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
            method.addAttribute(attributeInfo);
        }
        return method;
    }

    private String getParamAndReturnType() {
        Utf8Info nameAndTypeInfo = (Utf8Info) this.getClzFile()
                .getConstantPool().getConstantInfo(this.getDescriptorIndex());
        return nameAndTypeInfo.getValue();
    }

    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
