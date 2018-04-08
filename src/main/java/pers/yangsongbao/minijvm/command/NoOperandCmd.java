package pers.yangsongbao.minijvm.command;


import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.Heap;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 */
public class NoOperandCmd extends BaseByteCodeCommand {

    public NoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        String opCode = this.getOpCode();

        if (BaseByteCodeCommand.aload_0.equals(opCode)) {

            JavaObject javaObject = frame.getLocalVariableValue(0);

            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.aload_1.equals(opCode)) {

            JavaObject javaObject = frame.getLocalVariableValue(1);

            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.aload_2.equals(opCode)) {
            JavaObject javaObject = frame.getLocalVariableValue(2);
            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.iload_1.equals(opCode)) {
            JavaObject javaObject = frame.getLocalVariableValue(1);
            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.iload_2.equals(opCode)) {
            JavaObject javaObject = frame.getLocalVariableValue(2);
            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.iload_3.equals(opCode)) {
            JavaObject javaObject = frame.getLocalVariableValue(3);
            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.fload_3.equals(opCode)) {
            JavaObject javaObject = frame.getLocalVariableValue(3);
            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.areturn.equals(opCode)) {
            StackFrame callerFrame = frame.getCallerFrame();
            JavaObject javaObject = frame.getOperandStack().pop();
            callerFrame.getOperandStack().push(javaObject);
            result.setNextAction(ExecuteResult.EXIT_CURRENT_FRAME);

        } else if (BaseByteCodeCommand.voidreturn.equals(opCode)) {
            result.setNextAction(ExecuteResult.EXIT_CURRENT_FRAME);

        } else if (BaseByteCodeCommand.ireturn.equals(opCode)) {
            StackFrame callerFrame = frame.getCallerFrame();
            JavaObject javaObject = frame.getOperandStack().pop();
            callerFrame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.freturn.equals(opCode)) {

            StackFrame callerFrame = frame.getCallerFrame();
            JavaObject javaObject = frame.getOperandStack().pop();
            callerFrame.getOperandStack().push(javaObject);
        } else if (BaseByteCodeCommand.astore_1.equals(opCode)) {

            JavaObject javaObject = frame.getOperandStack().pop();

            frame.setLocalVariableValue(1, javaObject);

        } else if (BaseByteCodeCommand.dup.equals(opCode)) {

            JavaObject javaObject = frame.getOperandStack().peek();
            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.iconst_0.equals(opCode)) {

            JavaObject javaObject = Heap.getInstance().newInt(0);

            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.iconst_1.equals(opCode)) {

            JavaObject javaObject = Heap.getInstance().newInt(1);

            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.iconst_4.equals(opCode)) {

            JavaObject javaObject = Heap.getInstance().newInt(4);

            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.fconst_2.equals(opCode)) {

            JavaObject javaObject = Heap.getInstance().newFloat(2);

            frame.getOperandStack().push(javaObject);

        } else if (BaseByteCodeCommand.istore_1.equals(opCode)) {

            JavaObject javaObject = frame.getOperandStack().pop();

            frame.setLocalVariableValue(1, javaObject);

        } else if (BaseByteCodeCommand.istore_2.equals(opCode)) {

            JavaObject javaObject = frame.getOperandStack().pop();

            frame.setLocalVariableValue(2, javaObject);

        } else if (BaseByteCodeCommand.iadd.equals(opCode)) {

            JavaObject javaObject1 = frame.getOperandStack().pop();
            JavaObject javaObject2 = frame.getOperandStack().pop();

            JavaObject sum = Heap.getInstance().newInt(javaObject1.getIntValue() + javaObject2.getIntValue());

            frame.getOperandStack().push(sum);

        } else if (BaseByteCodeCommand.aconst_null.equals(opCode)) {

            frame.getOperandStack().push(null);

        } else {
            throw new RuntimeException("you must forget to implement the operation :" + opCode);
        }
    }

    @Override
    public int getLength() {
        return 1;
    }

    @Override
    public String toString() {
        return this.getOffset() + ":" + this.getOpCode() + " " + this.getReadableCodeText();
    }

}
