package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.cmd.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
public class StackFrame {

    private List<JavaObject> localVariableTable;
    private Stack<JavaObject> operandStack;
    private int programCounter;
    private Method method;
    private StackFrame callerFrame;

    private StackFrame(Method method) {
        this.localVariableTable = new ArrayList<>();
        this.operandStack = new Stack<>();
        this.programCounter = 0;
        this.method = method;
    }

    public static StackFrame create(Method method){
        return new StackFrame(method);
    }

    public ExecuteResult execute(){

        return null;
    }


        public int getNextCommandIndex(int offset) {
        BaseByteCodeCommand[] commands = method.getCodeAttr().getCommands();
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].getOffset() == offset) {
                return i;
            }
        }
        throw new RuntimeException("Can't find next command");
    }


    public StackFrame getCallerFrame() {
        return callerFrame;
    }

    public void setCallerFrame(StackFrame callerFrame) {
        this.callerFrame = callerFrame;
    }

    public List<JavaObject> getLocalVariableTable() {
        return localVariableTable;
    }

    public void setLocalVariableTable(List<JavaObject> localVariableTable) {
        this.localVariableTable = localVariableTable;
    }

    public Stack<JavaObject> getOperandStack() {
        return operandStack;
    }

    public void setOperandStack(Stack<JavaObject> operandStack) {
        this.operandStack = operandStack;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
