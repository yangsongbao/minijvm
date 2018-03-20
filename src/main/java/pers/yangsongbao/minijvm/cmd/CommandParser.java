package pers.yangsongbao.minijvm.cmd;


import pers.yangsongbao.minijvm.clz.ClassFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 */
public class CommandParser {

    public static BaseByteCodeCommand[] parse(ClassFile clzFile, String codes) {
        if ((codes == null) || (codes.length() == 0) || (codes.length() % 2) != 0) {
            throw new RuntimeException("the orignal code is not correct");
        }

        List<BaseByteCodeCommand> commands = new ArrayList<>();
        CommandIterator iter = new CommandIterator(codes);
        while (iter.hasNext()){
            String opCode = iter.next2CharAsString();
            switch (opCode) {
                case BaseByteCodeCommand.new_object :
                    NewObjectCmd newObjectCmd = new NewObjectCmd(clzFile, opCode);
                    newObjectCmd.setOprand1(iter.next2CharAsInt());
                    newObjectCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(newObjectCmd);
                    break;
                case BaseByteCodeCommand.invokespecial:
                    InvokeSpecialCmd invokeSpecialCmd = new InvokeSpecialCmd(clzFile, opCode);
                    invokeSpecialCmd.setOprand1(iter.next2CharAsInt());
                    invokeSpecialCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(invokeSpecialCmd);
                    break;
                case BaseByteCodeCommand.invokevirtual:
                    InvokeVirtualCmd invokeVirtualCmd = new InvokeVirtualCmd(clzFile, opCode);
                    invokeVirtualCmd.setOprand1(iter.next2CharAsInt());
                    invokeVirtualCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(invokeVirtualCmd);
                    break;
                case BaseByteCodeCommand.getfield:
                    GetFieldCmd getFieldCmd = new GetFieldCmd(clzFile, opCode);
                    getFieldCmd.setOprand1(iter.next2CharAsInt());
                    getFieldCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(getFieldCmd);
                    break;
                case BaseByteCodeCommand.getstatic:
                    GetStaticFieldCmd getStaticFieldCmd = new GetStaticFieldCmd(clzFile, opCode);
                    getStaticFieldCmd.setOprand1(iter.next2CharAsInt());
                    getStaticFieldCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(getStaticFieldCmd);
                    break;
                case BaseByteCodeCommand.putfield:
                    PutFieldCmd putFieldCmd = new PutFieldCmd(clzFile, opCode);
                    putFieldCmd.setOprand1(iter.next2CharAsInt());
                    putFieldCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(putFieldCmd);
                    break;
                case BaseByteCodeCommand.if_icmp_ge:
                case BaseByteCodeCommand.if_icmple:
                case BaseByteCodeCommand.goto_no_condition:
                    ComparisonCmd cmd = new ComparisonCmd(clzFile,opCode);
                    cmd.setOprand1(iter.next2CharAsInt());
                    cmd.setOprand2(iter.next2CharAsInt());
                    commands.add(cmd);
                    break;
                case BaseByteCodeCommand.bipush:
                    BiPushCmd biPushCmd = new BiPushCmd(clzFile, opCode);
                    biPushCmd.setOperand(iter.next2CharAsInt());
                    commands.add(biPushCmd);
                    break;
                case BaseByteCodeCommand.iinc:
                    IncrementCmd incrementCmd = new IncrementCmd(clzFile,opCode);
                    incrementCmd.setOprand1(iter.next2CharAsInt());
                    incrementCmd.setOprand2(iter.next2CharAsInt());
                    commands.add(incrementCmd);
                    break;
                case BaseByteCodeCommand.dup:
                case BaseByteCodeCommand.aload_0:
                case BaseByteCodeCommand.aload_1:
                case BaseByteCodeCommand.aload_2:
                case BaseByteCodeCommand.iload_1:
                case BaseByteCodeCommand.iload_2:
                case BaseByteCodeCommand.iload_3:
                case BaseByteCodeCommand.fload_3:
                case BaseByteCodeCommand.iconst_0:
                case BaseByteCodeCommand.iconst_1:
                case BaseByteCodeCommand.istore_1:
                case BaseByteCodeCommand.istore_2:
                case BaseByteCodeCommand.voidreturn:
                case BaseByteCodeCommand.iadd:
                case BaseByteCodeCommand.astore_1:
                case BaseByteCodeCommand.ireturn:
                    NoOperandCmd noOperandCmd = new NoOperandCmd(clzFile, opCode);
                    commands.add(noOperandCmd);
                    break;
                default:
                    throw new RuntimeException("Sorry, the java instruction " + opCode + " has not been implemented");
            }
        }
        return null;
    }

    private static class CommandIterator {
        String codes = null;
        int pos = 0;
        CommandIterator(String codes) {
            this.codes = codes;
        }

        boolean hasNext() {
            return pos < this.codes.length();
        }

        String next2CharAsString() {
            String result = codes.substring(pos, pos + 2);
            pos += 2;
            return result;
        }

        int next2CharAsInt() {
            String s = this.next2CharAsString();
            return Integer.valueOf(s, 16);
        }
    }
}
