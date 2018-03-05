package pers.yangsongbao.minijvm.method;

import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.attribute.CodeAttr;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.cmd.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

/**
 * @author songbao.yang
 */
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
            switch (attrName){
                case AttributeInfo.CODE:
                    CodeAttr codeAttr = CodeAttr.parse(clzFile, iter);
                    method.setCodeAttr(codeAttr);
                    break;
                case AttributeInfo.DEPRECATED:

                    break;
                case AttributeInfo.EXCEPTIONS:
                    break;
                case AttributeInfo.SIGNATURE:
                    break;
                case AttributeInfo.SYNTHETIC:
                    break;
                case AttributeInfo.RUNTIME_VISIBLE_ANNOTATIONS:
                    break;
                case AttributeInfo.RUNTIME_INVISIBLE_ANNOTATIONS:
                    break;
                case AttributeInfo.RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
                    break;
                case AttributeInfo.RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
                    break;
                case AttributeInfo.ANNOTATION_DEFAULT:
                    break;
                default:
                    throw new RuntimeException("the method attribute " + attrName + " has not been implemented yet.");
            }
        }
        return method;
    }

    public BaseByteCodeCommand[] getCmds() {
        return this.getCodeAttr().getCmds();
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

    public CodeAttr getCodeAttr() {
        return codeAttr;
    }

    public void setCodeAttr(CodeAttr code) {
        this.codeAttr = code;
    }
}
