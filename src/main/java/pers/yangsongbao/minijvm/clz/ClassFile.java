package pers.yangsongbao.minijvm.clz;

import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.field.Field;
import pers.yangsongbao.minijvm.method.Method;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songbao.yang on 2017/12/17.
 */
public class ClassFile {

    private int minorVersion;
    private int majorVersion;

    private AccessFlag accessFlag;
    private ClassIndex classIndex;
    private ConstantPool constantPool;
    private List<Field> fields = new ArrayList<Field>();
    private List<Method> methods = new ArrayList<Method>();

    public void addField(Field f) {
        fields.add(f);
    }

    public void addMethod(Method m) {
        methods.add(m);
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public AccessFlag getAccessFlag() {
        return accessFlag;
    }

    public void setAccessFlag(AccessFlag accessFlag) {
        this.accessFlag = accessFlag;
    }

    public ClassIndex getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(ClassIndex classIndex) {
        this.classIndex = classIndex;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public void setConstantPool(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

}
