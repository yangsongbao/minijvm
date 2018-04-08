package pers.yangsongbao.minijvm.constant;

import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池（Runtime Constant Pool）是每一个类或接口的常量池（Constant_Pool）的运行时表示形式，
 * 它包括了若干种不同的常量：从编译期可知的数值字面量到必须运行期解析后才能获得的方法或字段引
 * <p>
 * 每一个运行时常量池都分配在 Java 虚拟机的方法区之中，在类和接口被加载到虚拟机后，对应的运行时常量池就被创建出来
 *
 * @author songbao.yang
 * @date 2017/12/17
 */
public class ConstantPool {

    private List<ConstantInfo> constantInfos = new ArrayList<ConstantInfo>();

    public ConstantPool() {

    }

    public void addConstantInfo(ConstantInfo info) {
        this.constantInfos.add(info);
    }

    public ConstantInfo getConstantInfo(int index) {
        return this.constantInfos.get(index);
    }

    public String getUTF8String(int index) {
        return ((Utf8Info) this.constantInfos.get(index)).getValue();
    }

    public int getSize() {
        return this.constantInfos.size();
    }

}
