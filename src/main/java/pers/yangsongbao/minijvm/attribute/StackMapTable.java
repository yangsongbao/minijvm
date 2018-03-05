package pers.yangsongbao.minijvm.attribute;


import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

/**
 * @author songbao.yang
 */
public class StackMapTable extends AttributeInfo {

    private String originalCode;

    public StackMapTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static StackMapTable parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        StackMapTable stackMapTable = new StackMapTable(attrNameIndex, attrLen);
        //todo 后面的StackMapTable太过复杂， 不再处理， 只把原始的代码读进来保存
        String code = iter.nextUxToHexString(attrLen);
        stackMapTable.setOriginalCode(code);

        return stackMapTable;
    }

    private void setOriginalCode(String code) {
        this.originalCode = code;
    }
}
