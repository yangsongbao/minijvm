package pers.yangsongbao.minijvm.constant;

import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;

import java.util.ArrayList;
import java.util.List;

/**
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
