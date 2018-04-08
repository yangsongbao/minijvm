package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.FieldRefInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.Heap;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class GetFieldCmd extends TwoOperandCmd {

    GetFieldCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {
        //todo 字段的解析
        //todo 字段所在类或接口的初始化
        FieldRefInfo info = (FieldRefInfo) this.getConstantInfo(this.getIndex());
        String className = info.getClassName();
        String fieldName = info.getFieldName();
        String fieldType = info.getFieldType();

        if ("java/lang/System".equals(className) && "out".equals(fieldName)
                && "Ljava/io/PrintStream;".equals(fieldType)) {
            JavaObject jo = Heap.getInstance().newObject(className);
            frame.getOperandStack().push(jo);
        }
//        throw new RuntimeException("pers.yangsongbao.minijvm.command.GetFieldCmd.execute");
        //TODO 处理非System.out的情况
    }
}
