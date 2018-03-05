package pers.yangsongbao.minijvm.attribute;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class SourceDebugExtension extends AttributeInfo {
    private char[] debugExtension = new char[attrLen];

    public SourceDebugExtension(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }


}
