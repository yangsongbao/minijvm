package pers.yangsongbao.minijvm.cmd;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 *
 * @author songbao.yang
 * @date 2018/3/18
 */
public class GetStaticFieldCmd extends TwoOperandCmd {

    protected GetStaticFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {

    }
}
