package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author songbao.yang
 * @date 2018/3/8
 */
public class ExecuteEngine {

    /**
     * 每一条 Java 虚拟机线程都有自己私有的 Java 虚拟机栈
     * 这个栈与线程同时创建，用于存储栈帧
     */
    private Stack<StackFrame> vmStack = new Stack<>();

    public void execute(Method method) {
        StackFrame mainFrame = StackFrame.create(method);
        vmStack.push(mainFrame);

        while (!vmStack.isEmpty()) {
            StackFrame currentFrame = vmStack.peek();
            ExecuteResult executeResult = currentFrame.execute();
            if (executeResult.isPauseAndRunNewFrame()) {
                Method nextMethod = executeResult.getNextMethod();
                StackFrame nextFrame = StackFrame.create(nextMethod);
                nextFrame.setCallerFrame(currentFrame);
                setupFunctionCallParams(currentFrame, nextFrame);
                vmStack.push(nextFrame);
            } else {
                vmStack.pop();
            }
        }
    }

    private void setupFunctionCallParams(StackFrame currentFrame, StackFrame nextFrame) {

        Method nextMethod = nextFrame.getMethod();
        List<String> parameterList = nextMethod.getParameterList();

        // +1 需要把this传过去
        int paramNum = parameterList.size() + 1;

        List<JavaObject> values = new ArrayList<>();
        while (paramNum > 0) {
            values.add(currentFrame.getOperandStack().pop());
            paramNum--;
        }

        List<JavaObject> params = new ArrayList<>();
        for (int i = values.size() - 1; i >= 0; i--) {
            params.add(values.get(i));
        }

        nextFrame.setLocalVariableTable(params);
    }

}
