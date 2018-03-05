package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class Exceptions extends AttributeInfo {
    private List<Integer> exceptionsIndex = new ArrayList<>();

    public Exceptions(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static Exceptions parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        Exceptions exceptions = new Exceptions(attrNameIndex, attrLen);
        int numOfExceptions = iter.nextU2ToInt();
        for (int i = 1; i <= numOfExceptions ; i++) {
            int exceptionIndex = iter.nextU2ToInt();
            exceptions.addExceptionIndex(exceptionIndex);
        }
        return exceptions;
    }

    public void addExceptionIndex(int exceptionIndex) {
        exceptionsIndex.add(exceptionIndex);
    }

    public List<Integer> getExceptionsIndex() {
        return exceptionsIndex;
    }

    public void setExceptionsIndex(List<Integer> exceptionsIndex) {
        this.exceptionsIndex = exceptionsIndex;
    }
}
