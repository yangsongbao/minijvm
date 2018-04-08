package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.FieldRefInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class GetStaticFieldCmd extends TwoOperandCmd {

    protected GetStaticFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        FieldRefInfo fieldRefInfo = (FieldRefInfo) this.getConstantInfo(getIndex());
        //todo 字段解析
        //todo 字段所在类或接口初始化
        String className = fieldRefInfo.getClassName();

    }
}
