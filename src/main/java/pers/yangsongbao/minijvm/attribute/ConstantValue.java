package pers.yangsongbao.minijvm.attribute;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class ConstantValue extends AttributeInfo {

    private int constValueIndex;

    public ConstantValue(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(int constValueIndex) {
        this.constValueIndex = constValueIndex;
    }
}
