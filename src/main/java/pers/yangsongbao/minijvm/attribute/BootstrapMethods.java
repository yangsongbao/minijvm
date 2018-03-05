package pers.yangsongbao.minijvm.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class BootstrapMethods extends AttributeInfo {
    private int numBootstrapMethods;
    private List<BootstrapMethod> bootstrapMethods = new ArrayList<>();

    public BootstrapMethods(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public void addBootstrapMethod(BootstrapMethod bootstrapMethod){
        bootstrapMethods.add(bootstrapMethod);
    }

}
