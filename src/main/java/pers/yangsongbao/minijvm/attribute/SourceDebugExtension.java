package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class SourceDebugExtension extends AttributeInfo {
    private String debugExtension;

    public SourceDebugExtension(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static SourceDebugExtension parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        SourceDebugExtension sourceDebugExtension = new SourceDebugExtension(attrNameIndex, attrLen);
        String debugExtension = iter.nextUxToHexString(attrLen);
        sourceDebugExtension.setDebugExtension(debugExtension);
        return sourceDebugExtension;
    }

    public String getDebugExtension() {
        return debugExtension;
    }

    public void setDebugExtension(String debugExtension) {
        this.debugExtension = debugExtension;
    }
}
