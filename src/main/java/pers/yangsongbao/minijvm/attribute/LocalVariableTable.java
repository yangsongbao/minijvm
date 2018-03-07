package pers.yangsongbao.minijvm.attribute;


import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 */
public class LocalVariableTable extends AttributeInfo {

    List<LocalVariableTableItem> items = new ArrayList<LocalVariableTableItem>();

    public LocalVariableTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LocalVariableTable parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {

        LocalVariableTable table = new LocalVariableTable(attrNameIndex, attrLen);
        int itemLen = iter.nextU2ToInt();
        for (int i = 1; i <= itemLen; i++) {
            LocalVariableTableItem item = new LocalVariableTableItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLength(iter.nextU2ToInt());
            item.setNameIndex(iter.nextU2ToInt());
            item.setDescIndex(iter.nextU2ToInt());
            item.setIndex(iter.nextU2ToInt());
            table.addLocalVariableTableItem(item);
        }
        return table;
    }

    private void addLocalVariableTableItem(LocalVariableTableItem item) {
        this.items.add(item);
    }

    public String toString(ConstantPool pool) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Local Variable Table:\n");
        for (LocalVariableTableItem item : items) {
            buffer.append("startPC:" + item.getStartPC()).append(",");
            buffer.append("name:" + pool.getUTF8String(item.getNameIndex())).append(",");
            buffer.append("desc:" + pool.getUTF8String(item.getDescIndex())).append(",");
            buffer.append("slotIndex:" + item.getIndex()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();
    }
}
