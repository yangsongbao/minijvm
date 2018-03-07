package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 * @date 2018/3/4
 */
public class BootstrapMethod {
    private int bootstrapMethodRef;
    private int numBootstrapArguments;
    private List<Integer> bootstrapArguments = new ArrayList<>();

    public static BootstrapMethod parse(ByteCodeIterator iter) {
        int bootstrapMethodRef = iter.nextU2ToInt();
        int numBootstrapArguments = iter.nextU2ToInt();
        BootstrapMethod bootstrapMethod = new BootstrapMethod();
        bootstrapMethod.setBootstrapMethodRef(bootstrapMethodRef);
        for (int i = 1; i <= numBootstrapArguments; i++) {
            int index = iter.nextU2ToInt();
            bootstrapMethod.addBootstrapArgument(index);
        }
        return bootstrapMethod;
    }

    public void addBootstrapArgument(int index) {
        bootstrapArguments.add(index);
    }

    public int getBootstrapMethodRef() {
        return bootstrapMethodRef;
    }

    public void setBootstrapMethodRef(int bootstrapMethodRef) {
        this.bootstrapMethodRef = bootstrapMethodRef;
    }

    public int getNumBootstrapArguments() {
        return numBootstrapArguments;
    }

    public void setNumBootstrapArguments(int numBootstrapArguments) {
        this.numBootstrapArguments = numBootstrapArguments;
    }

    public List<Integer> getBootstrapArguments() {
        return bootstrapArguments;
    }

    public void setBootstrapArguments(List<Integer> bootstrapArguments) {
        this.bootstrapArguments = bootstrapArguments;
    }
}
