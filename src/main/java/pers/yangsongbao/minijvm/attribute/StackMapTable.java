package pers.yangsongbao.minijvm.attribute;


import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

public class StackMapTable extends AttributeInfo {

    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static StackMapTable parse(ByteCodeIterator iter) {
        int index = iter.nextU2ToInt();
        int len = iter.nextU4ToInt();
        StackMapTable stackMapTable = new StackMapTable(index, len);

        //后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        String code = iter.nextUxToHexString(len);
        stackMapTable.setOriginalCode(code);

        return stackMapTable;
    }

    private void setOriginalCode(String code) {
        this.originalCode = code;
    }
}
