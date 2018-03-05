package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class BootstrapMethods extends AttributeInfo {
    private List<BootstrapMethod> bootstrapMethods = new ArrayList<>();

    public BootstrapMethods(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public void addBootstrapMethod(BootstrapMethod bootstrapMethod){
        bootstrapMethods.add(bootstrapMethod);
    }

    public static BootstrapMethods parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        int numBootstrapMethods = iter.nextU2ToInt();
        BootstrapMethods bootstrapMethods = new BootstrapMethods(attrNameIndex, attrLen);
        for (int i = 1; i <= numBootstrapMethods; i++) {
            BootstrapMethod bootstrapMethod = BootstrapMethod.parse(iter);
            bootstrapMethods.addBootstrapMethod(bootstrapMethod);
        }
        return bootstrapMethods;
    }

}
