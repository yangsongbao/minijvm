package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.FieldRefInfo;
import pers.yangsongbao.minijvm.constant.constantInfo.NameAndTypeInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class PutFieldCmd extends TwoOperandCmd {

    PutFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        FieldRefInfo fieldRef = (FieldRefInfo) this.getConstantInfo(this.getIndex());
        NameAndTypeInfo nameTypeInfo = (NameAndTypeInfo) this.getConstantInfo(fieldRef.getNameAndTypeIndex());
        String fieldName = nameTypeInfo.getName();
        //todo 字段类型检查
        JavaObject fieldValue = frame.getOperandStack().pop();
        JavaObject objectRef = frame.getOperandStack().pop();
        objectRef.setFieldValue(fieldName, fieldValue);
    }
}
