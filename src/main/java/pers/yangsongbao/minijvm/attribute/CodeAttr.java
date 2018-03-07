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
    List<AttributeInfo> attributes = new ArrayList<>();
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    private BaseByteCodeCommand[] cmds;
    private List<AttrException> attrExceptions;
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
        this.attrExceptions = new ArrayList<>();
    }

    public void addAttrException(AttrException attrException) {
        attrExceptions.add(attrException);
    }

    public static CodeAttr parse(ClassFile clzFile, ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        int maxStack = iter.nextU2ToInt();
        int maxLocals = iter.nextU2ToInt();
        int codeLen = iter.nextU4ToInt();
        String code = iter.nextUxToHexString(codeLen);

        BaseByteCodeCommand[] commands = CommandParser.parse(clzFile, code);
        CodeAttr codeAttr = new CodeAttr(attrNameIndex, attrLen, maxStack, maxLocals, codeLen, code, commands);

        int exceptionTableLen = iter.nextU2ToInt();
        if (exceptionTableLen > 0) {
            AttrException attrException = new AttrException();
            attrException.setStartPcIndex(iter.nextU2ToInt());
            attrException.setEndPcIndex(iter.nextU2ToInt());
            attrException.setHandlerPcIndex(iter.nextU2ToInt());
            attrException.setCatchTypeIndex(iter.nextU2ToInt());
            codeAttr.addAttrException(attrException);
        }

        int subAttrCount = iter.nextU2ToInt();
        for (int i = 1; i <= subAttrCount; i++) {
            AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
            codeAttr.addAttributes(attributeInfo);
        }
        return codeAttr;
    }

    private static class AttrException {
        private int startPcIndex;
        private int endPcIndex;
        private int handlerPcIndex;
        private int catchTypeIndex;

        public int getStartPcIndex() {
            return startPcIndex;
        }

        public void setStartPcIndex(int startPcIndex) {
            this.startPcIndex = startPcIndex;
        }

        public int getEndPcIndex() {
            return endPcIndex;
        }

        public void setEndPcIndex(int endPcIndex) {
            this.endPcIndex = endPcIndex;
        }

        public int getHandlerPcIndex() {
            return handlerPcIndex;
        }

        public void setHandlerPcIndex(int handlerPcIndex) {
            this.handlerPcIndex = handlerPcIndex;
        }

        public int getCatchTypeIndex() {
            return catchTypeIndex;
        }

        public void setCatchTypeIndex(int catchTypeIndex) {
            this.catchTypeIndex = catchTypeIndex;
        }
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

}
