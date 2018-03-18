package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.method.Method;

import java.util.Stack;

/**
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
public class ExecuteEngine {

    private Stack<StackFrame> VMStack = new Stack<>();


    public ExecuteResult execute(Method method) {

        StackFrame mainFrame = StackFrame.create(method);
        VMStack.push(mainFrame);

        while (!VMStack.isEmpty()){
            StackFrame stackFrame = VMStack.peek();
            ExecuteResult executeResult = stackFrame.execute();
            if (executeResult.isPauseAndRunNewFrame()) {
                Method nextMethod = executeResult.getNextMethod();
                StackFrame nextFrame = StackFrame.create(nextMethod);
                nextFrame.setCallerFrame(stackFrame);
                setupFunctionCallParams(stackFrame, nextFrame);

            }
        }
        return null;
    }

    private void setupFunctionCallParams(StackFrame currentFrame, StackFrame nextFrame) {

        Method method = nextFrame.getMethod();
        


    }

}
