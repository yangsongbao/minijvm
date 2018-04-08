package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.constantInfo.FloatInfo;
import pers.yangsongbao.minijvm.constant.constantInfo.IntegerInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.Heap;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 * @date 2018/3/21
 */
public class LdcCommand extends OneOperandCmd {

    protected LdcCommand(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        ConstantInfo constantInfo = this.getConstantInfo(getOperand());
        switch (constantInfo.getType()) {
            case ConstantInfo.INTEGER_INFO:
                JavaObject integerInfo = Heap.getInstance().newInt(((IntegerInfo) constantInfo).getValue());
                frame.getOperandStack().push(integerInfo);
                break;
            case ConstantInfo.FLOAT_INFO:
                JavaObject floatInfo = Heap.getInstance().newFloat(((FloatInfo) constantInfo).getValue());
                frame.getOperandStack().push(floatInfo);
                break;
            case ConstantInfo.STRING_INFO:
                JavaObject stringInfo = Heap.getInstance().newString((constantInfo).toString());
                frame.getOperandStack().push(stringInfo);
                break;
            case ConstantInfo.CLASS_INFO:
                break;
            default:
                throw new RuntimeException("wrong constantInfo type : " + constantInfo.getType());
        }
    }
}
