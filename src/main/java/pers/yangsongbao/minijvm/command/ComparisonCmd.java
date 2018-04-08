package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class ComparisonCmd extends TwoOperandCmd {

    ComparisonCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {

        if (BaseByteCodeCommand.if_icmp_ge.equals(this.getOpCode())) {
            JavaObject jo2 = frame.getOperandStack().pop();
            JavaObject jo1 = frame.getOperandStack().pop();
            if (jo1.getIntValue() >= jo2.getIntValue()) {
                this.setJumpResult(result);
            }

        } else if (BaseByteCodeCommand.if_icmple.equals(this.getOpCode())) {
            JavaObject jo2 = frame.getOperandStack().pop();
            JavaObject jo1 = frame.getOperandStack().pop();
            if (jo1.getIntValue() <= jo2.getIntValue()) {
                this.setJumpResult(result);
            }

        } else if (BaseByteCodeCommand.goto_no_condition.equals(this.getOpCode())) {
            this.setJumpResult(result);

        } else {
            throw new RuntimeException(this.getOpCode() + "has not been implemented");
        }
    }

    private int getOffsetFromStartCmd() {
        int index1 = this.getOprand1();
        int index2 = this.getOprand2();
        short offsetFromCurrent = (short) (index1 << 8 | index2);
        return this.getOffset() + offsetFromCurrent;
    }

    private void setJumpResult(ExecuteResult result) {
        int offsetFromStartCmd = this.getOffsetFromStartCmd();
        result.setNextAction(ExecuteResult.JUMP);
        result.setNextCmdOffset(offsetFromStartCmd);
    }

    @Override
    public String toString() {
        String text = this.getReadableCodeText();
        return this.getOffset() + ":" + this.getOpCode() + " " + text + " " + this.getOffsetFromStartCmd();
    }
}
