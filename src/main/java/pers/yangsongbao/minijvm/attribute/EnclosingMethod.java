package pers.yangsongbao.minijvm.attribute;

/**
 * @author songbao.yang
 * @date 2018/3/4
 */
public class EnclosingMethod extends AttributeInfo {
    private int classIndex;
    private int methodIndex;

    public EnclosingMethod(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }


    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getMethodIndex() {
        return methodIndex;
    }

    public void setMethodIndex(int methodIndex) {
        this.methodIndex = methodIndex;
    }
}
