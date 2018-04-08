package pers.yangsongbao.minijvm.engine;

import pers.yangsongbao.minijvm.command.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.method.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 栈帧（Frame）是用来存储数据和部分过程结果的数据结构，同时也被用来处理动态链接
 * （Dynamic Linking）、方法返回值和异常分派（Dispatch Exception）
 * <p>
 * 栈帧随着方法调用而创建，随着方法结束而销毁——无论方法是正常完成还是异常完成（抛出
 * 了在方法内未被捕获的异常）都算作方法结束
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
public class StackFrame {

    /**
     * 一个局部变量可以保存一个类型为 boolean、 byte、 char、 short、 float、 reference
     * 和 returnAddress 的数据，两个局部变量可以保存一个类型为 long 和 double 的数据
     */
    private List<JavaObject> localVariableTable;

    /**
     * 每一个栈帧内部都包含一个称为操作数栈（Operand Stack）的后进先出栈
     */
    private Stack<JavaObject> operandStack;

    private int index;
    private Method method;
    private StackFrame callerFrame;

    private StackFrame(Method method) {
        this.localVariableTable = new ArrayList<>();
        this.operandStack = new Stack<>();
        this.index = 0;
        this.method = method;
    }

    static StackFrame create(Method method) {
        return new StackFrame(method);
    }

    public ExecuteResult execute() {
        System.out.println("-----start execute method : " + method.getMethodName());
        List<BaseByteCodeCommand> commands = method.getCodeAttr().getCommands();
        while (index < commands.size()) {
            ExecuteResult executeResult = new ExecuteResult();
            executeResult.setNextAction(ExecuteResult.RUN_NEXT_CMD);
            BaseByteCodeCommand command = commands.get(index);
            System.out.println("execute command : " + command.toString());
            command.execute(this, executeResult);
            switch (executeResult.getNextAction()) {
                case ExecuteResult.RUN_NEXT_CMD:
                    index++;
                    break;
                case ExecuteResult.JUMP:
                    int nextCmdOffset = executeResult.getNextCmdOffset();
                    this.index = getNextCommandIndex(nextCmdOffset);
                    break;
                case ExecuteResult.EXIT_CURRENT_FRAME:
                    return executeResult;
                case ExecuteResult.PAUSE_AND_RUN_NEW_FRAME:
                    index++;
                    return executeResult;
                default:
                    index++;
            }
        }
        //当前StackFrmae的指令全部执行完毕，可以退出了
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setNextAction(ExecuteResult.EXIT_CURRENT_FRAME);
        return executeResult;
    }

    public JavaObject getLocalVariableValue(int index) {
        return this.localVariableTable.get(index);
    }

    public void setLocalVariableValue(int index, JavaObject value) {
        if (this.localVariableTable.size() - 1 < index) {
            for (int i = this.localVariableTable.size(); i <= index; i++) {
                this.localVariableTable.add(null);
            }
        }
        this.localVariableTable.set(index, value);
    }

    private int getNextCommandIndex(int offset) {
        List<BaseByteCodeCommand> commands = method.getCodeAttr().getCommands();
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i).getOffset() == offset) {
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
