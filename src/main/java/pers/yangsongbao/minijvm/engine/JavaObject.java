package pers.yangsongbao.minijvm.engine;

import java.util.HashMap;
import java.util.Map;

/**
 * Java 虚拟机规范不强制规定对象的内部结构应当如何表示
 * 在HotSpot虚拟机中，对象在内存中存储的布局可以分为3块区域：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。
 *
 * @author songbao.yang
 * @date 2018/3/8
 */
public class JavaObject {

    /**
     * 对象头，包括两部分信息
     * 第一部分用于存储对象自身的运行时数据，
     * 如哈希码（HashCode）、 GC分代年龄、 锁状态标志、 线程持有的锁、 偏向线程ID、 偏向时间戳等
     *
     * 另外一部分是类型指针
     * 即对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例
     *
     * Header header
     */

    /**
     * 在程序代码中所定义的各种类型的字段内容
     * 无论是从父类继承下来的，还是在子类中定义的，都需要记录起来
     *
     * InstanceData instanceData
     */

    /**
     * 对齐填充
     * 并不是必然存在的，也没有特别的含义，它仅仅起着占位符的作用
     * Padding padding
     */


    public static final int OBJECT = 1;
    public static final int STRING = 2;
    public static final int INT = 3;
    public static final int FLOAT = 4;

    int type;
    private String className;

    private Map<String, JavaObject> fieldValues = new HashMap<>();

    private String stringValue;

    private int intValue;

    private float floatValue;

    public JavaObject(int type) {
        this.type = type;
    }

    public void setFieldValue(String fieldName, JavaObject fieldValue) {
        fieldValues.put(fieldName, fieldValue);
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public void setStringValue(String value) {
        stringValue = value;
    }

    public int getIntValue() {
        return this.intValue;
    }

    public void setIntValue(int value) {
        this.intValue = value;
    }

    public int getType() {
        return type;
    }

    public JavaObject getFieldValue(String fieldName) {
        return this.fieldValues.get(fieldName);
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setFloatValue(float value) {
        this.floatValue = value;
    }

    @Override
    public String toString() {
        switch (this.getType()) {
            case INT:
                return String.valueOf(this.intValue);
            case STRING:
                return this.stringValue;
            case OBJECT:
                return this.className + ":" + this.fieldValues;
            case FLOAT:
                return String.valueOf(this.floatValue);
            default:
                return null;
        }
    }

}
