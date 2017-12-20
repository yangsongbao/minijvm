package pers.yangsongbao.minijvm.method;

import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.attribute.CodeAttr;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.cmd.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.constant.UTF8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

public class Method {

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private CodeAttr codeAttr;
    private ClassFile clzFile;

    public Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attribCount = iter.nextU2ToInt();

        Method method = new Method(clzFile, accessFlag, nameIndex, descIndex);
        for (int j = 1; j <= attribCount; j++) {
            int attrNameIndex = iter.nextU2ToInt();
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            iter.back(2);
            if (AttributeInfo.CODE.equalsIgnoreCase(attrName)) {
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                method.setCodeAttr(codeAttr);
            } else {
                throw new RuntimeException("only CODE attribute is implemented , please implement the " + attrName);
            }
        }
        return method;
    }

    public BaseByteCodeCommand[] getCmds() {
        return this.getCodeAttr().getCmds();
    }

    private String getParamAndReturnType() {
        UTF8Info nameAndTypeInfo = (UTF8Info) this.getClzFile()
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

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }
}