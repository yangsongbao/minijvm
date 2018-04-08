package pers.yangsongbao.minijvm.print;

import pers.yangsongbao.minijvm.constant.constantInfo.*;

/**
 * @author songbao.yang
 * @date 2018/3/5
 */
public interface Visitor {
    void visitClassInfo(ClassInfo info);

    void visitFieldRef(FieldRefInfo info);

    void visitMethodRef(MethodRefInfo info);

    void visitInterfaceMethodRefInfo(InterfaceMethodRefInfo info);

    void visitString(StringInfo info);

    void visitIntegerInfo(IntegerInfo info);

    void visitFloatInfo(FloatInfo info);

    void visitLongInfo(LongInfo info);

    void visitDoubleInfo(DoubleInfo info);

    void visitNameAndType(NameAndTypeInfo info);

    void visitUtf8Info(Utf8Info info);

    void visitMethodHandleInfo(MethodHandleInfo info);

    void visitMethodTypeInfo(MethodTypeInfo info);

    void visitInvokeDynamicInfo(InvokeDynamicInfo info);


}
