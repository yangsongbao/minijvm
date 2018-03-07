package pers.yangsongbao.minijvm.attribute;

/**
 * @author songbao.yang
 * @date 2018/3/4
 */
public class Signature extends AttributeInfo {
    private int ignatureIndex;

    public Signature(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public int getIgnatureIndex() {
        return ignatureIndex;
    }

    public void setIgnatureIndex(int ignatureIndex) {
        this.ignatureIndex = ignatureIndex;
    }
}
