package pers.yangsongbao.minijvm.cmd;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.constantInfo.ClassInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.Heap;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 *
 * @author songbao.yang
 * @date 2018/3/18
 */
public class NewObjectCmd extends TwoOperandCmd {

    protected NewObjectCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        int index = getIndex();
        ClassInfo classInfo = (ClassInfo)this.getConstantInfo(index);
        String className = classInfo.getClassName();

        JavaObject objectRef = Heap.getInstance().newObject(className);
        frame.getOperandStack().push(objectRef);
    }

}
