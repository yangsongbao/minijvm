package pers.yangsongbao.minijvm.attribute;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class BootstrapMethod {
    private int bootstrapMethodRef;
    private int numBootstrapArguments;
    private int[] bootstrapArguments;

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

    public int[] getBootstrapArguments() {
        return bootstrapArguments;
    }

    public void setBootstrapArguments(int[] bootstrapArguments) {
        this.bootstrapArguments = bootstrapArguments;
    }
}
