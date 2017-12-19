package pers.yangsongbao.minijvm.clz;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class ClassIndex {
    private int thisClassIndex;
    private int superClassIndex;

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public void setThisClassIndex(int thisClassIndex) {
        this.thisClassIndex = thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public void setSuperClassIndex(int superClassIndex) {
        this.superClassIndex = superClassIndex;
    }
}
