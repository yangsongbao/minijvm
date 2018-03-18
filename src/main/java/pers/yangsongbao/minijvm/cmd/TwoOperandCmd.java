package pers.yangsongbao.minijvm.cmd;

import pers.yangsongbao.minijvm.clz.ClassFile;

/**
 *
 * @author songbao.yang
 * @date 2018/3/18
 */
public abstract class TwoOperandCmd extends BaseByteCodeCommand {

    int oprand1 = -1;
    int oprand2 = -1;

    protected TwoOperandCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public int getLength() {
        return 3;
    }

    public int getIndex() {
        return (this.oprand1 << 8) | this.oprand2;
    }

    public int getOprand1() {
        return oprand1;
    }

    public void setOprand1(int oprand1) {
        this.oprand1 = oprand1;
    }

    public int getOprand2() {
        return oprand2;
    }

    public void setOprand2(int oprand2) {
        this.oprand2 = oprand2;
    }
}
