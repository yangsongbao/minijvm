package pers.yangsongbao.minijvm.attribute;


import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 被调试器用于确定源文件中行号表示的内容在 Java 虚拟机的 code[]数组中对应的部分
 *
 * @author songbao.yang
 */
public class LineNumberTable extends AttributeInfo {
    private List<LineNumberItem> items = new ArrayList<>();

    public LineNumberTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static LineNumberTable parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        LineNumberTable table = new LineNumberTable(attrNameIndex, attrLen);
        int itemLen = iter.nextU2ToInt();
        for (int i = 1; i <= itemLen; i++) {
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLineNum(iter.nextU2ToInt());
            table.addLineNumberItem(item);
        }
        return table;
    }

    private void addLineNumberItem(LineNumberItem item) {
        this.items.add(item);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Line Number Table:\n");
        for (LineNumberItem item : items) {
            buffer.append("startPC:").append(item.getStartPC()).append(",");
            buffer.append("lineNum:").append(item.getLineNum()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();
    }

    private static class LineNumberItem {
        /**
         * start_pc 项的值必须是 code[]数组的一个索引， code[]数组在该索引处的字符
         * 表示源文件中新的行的起点。 start_pc 项的值必须小于当前 LineNumberTable
         * 属性所在的 Code 属性的 code_length 项的值
         */
        int startPC;

        /**
         * line_number 项的值必须与源文件的行数相匹配
         */
        int lineNum;

        int getStartPC() {
            return startPC;
        }

        void setStartPC(int startPC) {
            this.startPC = startPC;
        }

        int getLineNum() {
            return lineNum;
        }

        void setLineNum(int lineNum) {
            this.lineNum = lineNum;
        }
    }
}
