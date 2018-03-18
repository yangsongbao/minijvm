package pers.yangsongbao.minijvm.cmd;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.constantInfo.MethodRefInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 *
 * @author songbao.yang
 * @date 2018/3/18
 */
public class InvokeVirtualCmd extends TwoOperandCmd {

    protected InvokeVirtualCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        int index = this.getIndex();
        MethodRefInfo methodRefInfo = (MethodRefInfo)this.getConstantInfo(index);

    }
}
