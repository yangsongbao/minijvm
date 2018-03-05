package pers.yangsongbao.minijvm.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.constant.constantInfo.*;

/**
 *
 * @author songbao.yang
 * @date 2018/3/5
 */
public class ConstantPoolVisitor implements Visitor {
    private static final Logger log = LoggerFactory.getLogger(ConstantPoolVisitor.class);

    @Override
    public void visitClassInfo(ClassInfo info) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Class    #")
                .append(info.getUtf8Index())
                .append("  ")
                .append(info.getClassName());
        System.out.println(buffer);
    }

    @Override
    public void visitDoubleInfo(DoubleInfo info) {

    }

    @Override
    public void visitFieldRef(FieldRefInfo info) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("FieldRef    #")
                .append(info.getClassInfoIndex())
                .append(".#")
                .append(info.getNameAndTypeIndex());
        System.out.println(buffer);
    }

    @Override
    public void visitFloatInfo(FloatInfo info) {

    }

    @Override
    public void visitIntegerInfo(IntegerInfo info) {

    }

    @Override
    public void visitInterfaceMethodRefInfo(InterfaceMethodRefInfo info) {

    }

    @Override
    public void visitInvokeDynamicInfo(InvokeDynamicInfo info) {

    }

    @Override
    public void visitLongInfo(LongInfo info) {

    }

    @Override
    public void visitMethodHandleInfo(MethodHandleInfo info) {

    }

    @Override
    public void visitMethodRef(MethodRefInfo info) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("MethodRef    #")
                .append(info.getClassInfoIndex())
                .append(".#")
                .append(info.getNameAndTypeIndex());
        System.out.println(buffer);
    }

    @Override
    public void visitNameAndType(NameAndTypeInfo info) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("NameAndType    #")
                .append(info.getNameIndex())
                .append(":#")
                .append(info.getDescriptorIndex());
        System.out.println(buffer);
    }

    @Override
    public void visitString(StringInfo info) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("String    #").append(info.getIndex());
        System.out.println(buffer);
    }

    @Override
    public void visitUtf8(Utf8Info info) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("UTF8    ").append(info.getValue());
        System.out.println(buffer);
    }
}
