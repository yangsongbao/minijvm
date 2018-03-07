package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 * @date 2018/3/4
 */
public class LocalVariableTypeTable extends AttributeInfo {
    private List<LocalVariableTypeTableItem> items = new ArrayList<>();

    public LocalVariableTypeTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTypeTable parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {

        LocalVariableTypeTable table = new LocalVariableTypeTable(attrNameIndex, attrLen);
        int itemLen = iter.nextU2ToInt();
        for (int i = 1; i <= itemLen; i++) {
            LocalVariableTypeTableItem item = new LocalVariableTypeTableItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLength(iter.nextU2ToInt());
            item.setNameIndex(iter.nextU2ToInt());
            item.setSignatureIndex(iter.nextU2ToInt());
            item.setIndex(iter.nextU2ToInt());
            table.addLocalVariableTypeTableItem(item);
        }
        return table;
    }

    private void addLocalVariableTypeTableItem(LocalVariableTypeTableItem item) {
        this.items.add(item);
    }
}
