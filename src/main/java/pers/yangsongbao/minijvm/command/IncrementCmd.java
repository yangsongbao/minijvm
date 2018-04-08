package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.Heap;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class IncrementCmd extends TwoOperandCmd {

    IncrementCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        int index = this.getOprand1();
        int intValue = frame.getLocalVariableValue(index).getIntValue();
        int constValue = this.getOprand2();
        JavaObject javaObject = Heap.getInstance().newInt(intValue + constValue);
        frame.setLocalVariableValue(index, javaObject);
    }
}
