package pers.yangsongbao.minijvm.attribute;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class SourceFile extends AttributeInfo {
    private int sourcefileIndex;

    public SourceFile(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public int getSourcefileIndex() {
        return sourcefileIndex;
    }

    public void setSourcefileIndex(int sourcefileIndex) {
        this.sourcefileIndex = sourcefileIndex;
    }
}
