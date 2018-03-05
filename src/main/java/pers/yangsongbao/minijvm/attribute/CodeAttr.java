package pers.yangsongbao.minijvm.attribute;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.cmd.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.cmd.CommandParser;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 */
public class CodeAttr extends AttributeInfo {
    private static final Logger logger = LoggerFactory.getLogger(CodeAttr.class);

    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    private BaseByteCodeCommand[] cmds;
    List<AttributeInfo> attributes = new ArrayList<>();
//    private LineNumberTable lineNumTable;
//    private LocalVariableTable localVarTable;
//    private StackMapTable stackMapTable;

    public CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code, BaseByteCodeCommand[] cmds) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        this.cmds = cmds;
    }

    public void addAttributes(AttributeInfo attributeInfo) {
        attributes.add(attributeInfo);
    }

    public String getCode() {
        return code;
    }

    public BaseByteCodeCommand[] getCmds() {
        return cmds;
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter, int attrNameIndex, int attrLen) {
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
            AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
            codeAttr.addAttributes(attributeInfo);
        }
        return codeAttr;
    }

}
