package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.MethodRefInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.MethodArea;
import pers.yangsongbao.minijvm.engine.StackFrame;
import pers.yangsongbao.minijvm.method.Method;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class InvokeSpecialCmd extends TwoOperandCmd {

    InvokeSpecialCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());
        // 不用实现jang.lang.Object 的init方法
        if (methodRefInfo.getClassName().equals("java/lang/Object") && methodRefInfo.getMethodName().equals("<init>")) {
            return;
        }
        Method nextMethod = MethodArea.getInstance().getMethod(methodRefInfo);
        result.setNextAction(ExecuteResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(nextMethod);
    }
}
