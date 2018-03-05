package pers.yangsongbao.minijvm.print;

import pers.yangsongbao.minijvm.constant.constantInfo.*;

/**
 *
 * @author songbao.yang
 * @date 2018/3/5
 */
public interface Visitor {
    void visitClassInfo(ClassInfo info);
    void visitDoubleInfo(DoubleInfo info);
    void visitFieldRef(FieldRefInfo info);
    void visitFloatInfo(FloatInfo info);
    void visitIntegerInfo(IntegerInfo info);
    void visitInterfaceMethodRefInfo(InterfaceMethodRefInfo info);
    void visitInvokeDynamicInfo(InvokeDynamicInfo info);
    void visitLongInfo(LongInfo info);
    void visitMethodHandleInfo(MethodHandleInfo info);
    void visitMethodRef(MethodRefInfo info);
    void visitNameAndType(NameAndTypeInfo info);
    void visitString(StringInfo info);
    void visitUtf8(Utf8Info info);
}
