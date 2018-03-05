package pers.yangsongbao.minijvm.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class LocalVariableTypeTable extends AttributeInfo {
    private List<LocalVariableTypeTableItem> items = new ArrayList<>();

    private void addLocalVariableTypeTableItem(LocalVariableTypeTableItem item) {
        this.items.add(item);
    }
    public LocalVariableTypeTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }
}
