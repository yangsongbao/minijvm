package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

/**
 *
 * @author songbao.yang
 * @date 2017/12/17
 */
public abstract class AttributeInfo {
    public static final String CODE = "Code";
    public static final String CONSTANT_VALUE = "ConstantValue";
    public static final String DEPRECATED = "Deprecated";
    public static final String EXCEPTIONS = "Exceptions";
    public static final String EnclosingMethod = "EnclosingMethod";
    public static final String INNER_CLASSES = "InnerClasses";
    public static final String LINE_NUM_TABLE = "LineNumberTable";
    public static final String LOCAL_VAR_TABLE = "LocalVariableTable";
    public static final String STACK_MAP_TABLE = "StackMapTable";
    public static final String SIGNATURE = "Signature";
    public static final String SOURCE_FILE = "SourceFile";
    public static final String SOURCE_DEBUG_EXTENSION = "SourceDebugExtension";
    public static final String SYNTHETIC = "Synthetic";
    public static final String LOCAL_VAR_TYPE_TABLE= "LocalVariableTypeTable";
    public static final String RUNTIME_VISIBLE_ANNOTATIONS = "RuntimeVisibleAnnotations";
    public static final String RUNTIME_INVISIBLE_ANNOTATIONS = "RuntimeInvisibleAnnotations";
    public static final String RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS = "RuntimeVisibleParameterAnnotations";
    public static final String RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS = "RuntimeInvisibleParameterAnnotations";
    public static final String ANNOTATION_DEFAULT = "AnnotationDefault";
    public static final String BOOTSTRAP_METHODS = "BootstrapMethods";

    int attrNameIndex;
    int attrLen;

    public AttributeInfo(int attrNameIndex, int attrLen) {
        this.attrNameIndex = attrNameIndex;
        this.attrLen = attrLen;
    }

    public static AttributeInfo parse(ClassFile clzFile, ByteCodeIterator iter) {
        int attrNameIndex = iter.nextU2ToInt();
        int attrLen = iter.nextU4ToInt();
        String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
        switch (attrName){
            case AttributeInfo.CODE:
                CodeAttr codeAttr = CodeAttr.parse(clzFile, iter, attrNameIndex, attrLen);
                return codeAttr;
            case AttributeInfo.CONSTANT_VALUE:
                ConstantValue constValue = new ConstantValue(attrNameIndex, attrLen);
                constValue.setConstValueIndex(iter.nextU2ToInt());
                return constValue;
            case AttributeInfo.DEPRECATED:
                Deprecated deprecated = new Deprecated(attrNameIndex, attrLen);
                return deprecated;
            case AttributeInfo.EXCEPTIONS:
                Exceptions exceptions = Exceptions.parse(iter, attrNameIndex, attrLen);
                return exceptions;
            case AttributeInfo.EnclosingMethod:
                EnclosingMethod enclosingMethod = new EnclosingMethod(attrNameIndex, attrLen);
                enclosingMethod.setClassIndex(iter.nextU2ToInt());
                enclosingMethod.setMethodIndex(iter.nextU2ToInt());
                return enclosingMethod;
            case AttributeInfo.INNER_CLASSES:
                InnerClasses innerClasses = InnerClasses.parse(iter, attrNameIndex, attrLen);
                return innerClasses;
            case AttributeInfo.LINE_NUM_TABLE:
                LineNumberTable lineNumberTable = LineNumberTable.parse(iter, attrNameIndex, attrLen);
                return lineNumberTable;
            case AttributeInfo.LOCAL_VAR_TABLE:
                LocalVariableTable localVariableTable = LocalVariableTable.parse(iter, attrNameIndex, attrLen);
                return localVariableTable;
            case AttributeInfo.STACK_MAP_TABLE:
                StackMapTable stackMapTable = StackMapTable.parse(iter, attrNameIndex, attrLen);
                return stackMapTable;
            case AttributeInfo.SIGNATURE:
                Signature signature = new Signature(attrNameIndex, attrLen);
                signature.setIgnatureIndex(iter.nextU2ToInt());
                return signature;
            case AttributeInfo.SOURCE_DEBUG_EXTENSION:
                SourceDebugExtension sourceDebugExtension = SourceDebugExtension.parse(iter, attrNameIndex, attrLen);
                return sourceDebugExtension;
            case AttributeInfo.SOURCE_FILE:
                SourceFile sourceFile = new SourceFile(attrNameIndex, attrLen);
                sourceFile.setSourcefileIndex(iter.nextU2ToInt());
                return sourceFile;
            case AttributeInfo.SYNTHETIC:
                Synthetic synthetic = new Synthetic(attrNameIndex, attrLen);
                return synthetic;
            case AttributeInfo.LOCAL_VAR_TYPE_TABLE:
                LocalVariableTypeTable localVariableTypeTable = LocalVariableTypeTable.parse(iter, attrNameIndex, attrLen);
                return localVariableTypeTable;
            case AttributeInfo.RUNTIME_VISIBLE_ANNOTATIONS:
            case AttributeInfo.RUNTIME_INVISIBLE_ANNOTATIONS:
            case AttributeInfo.RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS:
            case AttributeInfo.RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS:
            case AttributeInfo.ANNOTATION_DEFAULT:
                throw new RuntimeException("the filed attribute " + attrName + " has not been implemented yet.");
            case AttributeInfo.BOOTSTRAP_METHODS:
                BootstrapMethods bootstrapMethods = BootstrapMethods.parse(iter, attrNameIndex, attrLen);
                return bootstrapMethods;
            default:
                throw new RuntimeException("the filed attribute " + attrName + " has not been implemented yet.");
        }
    }
}
