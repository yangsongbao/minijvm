package pers.yangsongbao.minijvm.cmd;

import pers.yangsongbao.minijvm.clz.ClassFile;

/**
 *
 * @author songbao.yang
 * @date 2018/3/18
 */
public abstract class OneOperandCmd extends BaseByteCodeCommand{

    int operand;

    protected OneOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public int getLength() {
        return 2;
    }

    public int getOperand() {
        return operand;
    }

    public void setOperand(int operand) {
        this.operand = operand;
    }
}
