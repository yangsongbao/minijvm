package pers.yangsongbao.minijvm.clz;

/**
 * @author songbao.yang
 * @date 2017/12/17
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
