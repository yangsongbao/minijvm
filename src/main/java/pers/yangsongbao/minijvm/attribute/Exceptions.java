package pers.yangsongbao.minijvm.attribute;

import java.util.List;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class Exceptions extends AttributeInfo {
    private int numberOfExceptions;
    private List<Integer> exceptionsIndex;

    public Exceptions(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public int getNumberOfExceptions() {
        return numberOfExceptions;
    }

    public void setNumberOfExceptions(int numberOfExceptions) {
        this.numberOfExceptions = numberOfExceptions;
    }

    public List<Integer> getExceptionsIndex() {
        return exceptionsIndex;
    }

    public void setExceptionsIndex(List<Integer> exceptionsIndex) {
        this.exceptionsIndex = exceptionsIndex;
    }
}
