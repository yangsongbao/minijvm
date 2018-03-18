package pers.yangsongbao.minijvm.attribute;


import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.cmd.BaseByteCodeCommand;
import pers.yangsongbao.minijvm.cmd.CommandParser;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.*;

/**
 * @author songbao.yang
 */
public class CodeAttr extends AttributeInfo {
    private int maxStack;
    private int maxLocals;
    private int codeLen;
    private String code;
    private BaseByteCodeCommand[] commands;
    private List<AttrException> attrExceptions;
    private Map<String, AttributeInfo> attributes;

    private static final Set<String> acceptableAttribute;

    static {
        acceptableAttribute = new HashSet<>();
        acceptableAttribute.add(AttributeInfo.LINE_NUM_TABLE);
        acceptableAttribute.add(AttributeInfo.LOCAL_VAR_TABLE);
        acceptableAttribute.add(AttributeInfo.STACK_MAP_TABLE);
    }


    private CodeAttr(int attrNameIndex, int attrLen, int maxStack, int maxLocals, int codeLen, String code, BaseByteCodeCommand[] commands) {
        super(attrNameIndex, attrLen);
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.codeLen = codeLen;
        this.code = code;
        this.commands = commands;
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
            int index = iter.nextU2ToInt();
            iter.back(2);
            String attrName = clzFile.getConstantPool().getUTF8String(index);
            if (acceptableAttribute.contains(attrName)){
                AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
                codeAttr.addAttributes(attrName, attributeInfo);
            } else {
                throw new RuntimeException("unsupported code attribute " + attrName);
            }

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

    public void addAttributes(String attrName, AttributeInfo attributeInfo) {
        attributes.put(attrName, attributeInfo);
    }

    public String getCode() {
        return code;
    }

    public BaseByteCodeCommand[] getCommands() {
        return commands;
    }

}
