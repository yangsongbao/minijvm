package pers.yangsongbao.minijvm.attribute;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.cmd.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.cmd.CommandParser;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

public class CodeAttr extends AttributeInfo {
    private static final Logger logger = LoggerFactory.getLogger(CodeAttr.class);

    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    private BaseByteCodeCommand[] cmds;
    private LineNumberTable lineNumTable;
    private LocalVariableTable localVarTable;
    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code, BaseByteCodeCommand[] cmds) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        this.cmds = cmds;
    }

    public String getCode() {
        return code;
    }

    public BaseByteCodeCommand[] getCmds() {
        return cmds;
    }

    public void setLineNumberTable(LineNumberTable t) {
        this.lineNumTable = t;
    }

    public void setLocalVariableTable(LocalVariableTable t) {
        this.localVarTable = t;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter) {
        int attrNameIndex = iter.nextU2ToInt();
        int attrLen = iter.nextU4ToInt();
        int maxStack = iter.nextU2ToInt();
        int maxLocals = iter.nextU2ToInt();

        int codeLen = iter.nextU4ToInt();
        String code = iter.nextUxToHexString(codeLen);

        BaseByteCodeCommand[] cmds = CommandParser.parse(clzFile, code);
        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, cmds);

        int exceptionTableLen = iter.nextU2ToInt();
        //TODO 处理exception
        if (exceptionTableLen > 0) {
            String exTable = iter.nextUxToHexString(exceptionTableLen);
            logger.warn("Encountered exception table , just ignore it : {}", exTable);
        }

        int subAttrCount = iter.nextU2ToInt();
        for (int i = 1; i <= subAttrCount; i++) {
            int subAttrIndex = iter.nextU2ToInt();
            String subAttrName = clzFile.getConstantPool().getUTF8String(subAttrIndex);
            logger.info("CodeAttr parse subAttrName: {}", subAttrName);
            //已经向前移动了U2, 现在退回去。
            iter.back(2);
            //line item table
            if (AttributeInfo.LINE_NUM_TABLE.equalsIgnoreCase(subAttrName)) {
                LineNumberTable lineNumberTable = LineNumberTable.parse(iter);
                codeAttr.setLineNumberTable(lineNumberTable);
            } else if (AttributeInfo.LOCAL_VAR_TABLE.equalsIgnoreCase(subAttrName)) {
                LocalVariableTable localVariableTable = LocalVariableTable.parse(iter);
                codeAttr.setLocalVariableTable(localVariableTable);
            } else if (AttributeInfo.STACK_MAP_TABLE.equalsIgnoreCase(subAttrName)) {
                StackMapTable stackMapTable = StackMapTable.parse(iter);
                codeAttr.setStackMapTable(stackMapTable);
            } else {
                throw new RuntimeException("Need code to process " + subAttrName);
            }
        }
        return codeAttr;
    }

    private void setStackMapTable(StackMapTable t) {
        this.stackMapTable = t;
    }


}
