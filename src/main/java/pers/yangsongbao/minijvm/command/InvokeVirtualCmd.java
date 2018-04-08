package pers.yangsongbao.minijvm.command;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.MethodRefInfo;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.MethodArea;
import pers.yangsongbao.minijvm.engine.StackFrame;
import pers.yangsongbao.minijvm.method.Method;

/**
 * @author songbao.yang
 * @date 2018/3/18
 */
public class InvokeVirtualCmd extends TwoOperandCmd {

    InvokeVirtualCmd(ClassFile clzFile, String opCode) {
        super(clzFile, opCode);
    }

    @Override
    public void execute(StackFrame frame, ExecuteResult result) {

        MethodRefInfo methodRefInfo = (MethodRefInfo) this.getConstantInfo(this.getIndex());
        String className = methodRefInfo.getClassName();
        String methodName = methodRefInfo.getMethodName();

        // 由于没有实现System.out.println方法，直接调用Java的方法。
        if (isSystemOutPrintlnMethod(className, methodName)) {
            JavaObject javaObject = frame.getOperandStack().pop();
            String value = javaObject.toString();
            System.out.println("-------------------" + value + "----------------");
            // 这里就是那个out对象， 因为是个假的，直接pop出来
            frame.getOperandStack().pop();
            return;
        }

        //注意：多态， 这才是真正的对象, 先从该对象的class 中去找对应的方法，找不到的话再去找父类的方法
        JavaObject javaObject = frame.getOperandStack().peek();
        MethodArea methodArea = MethodArea.getInstance();
        Method method = null;
        String currentClassName = javaObject.getClassName();
        while (currentClassName != null) {
            ClassFile currentClassFile = methodArea.findClassFile(currentClassName);
            method = currentClassFile.getMethod(methodRefInfo.getMethodName(), methodRefInfo.getParamAndReturnType());
            if (method != null) {
                break;
            } else {
                currentClassName = currentClassFile.getSuperClassName();
            }
        }

        if (method == null) {
            throw new RuntimeException("Can't find method for :" + methodRefInfo.toString());
        }
        result.setNextAction(ExecuteResult.PAUSE_AND_RUN_NEW_FRAME);
        result.setNextMethod(method);
    }

    private boolean isSystemOutPrintlnMethod(String className, String methodName) {
        return "java/io/PrintStream".equals(className) && "println".equals(methodName);
    }
}