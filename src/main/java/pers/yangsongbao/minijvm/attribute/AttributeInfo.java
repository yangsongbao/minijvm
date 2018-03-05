package pers.yangsongbao.minijvm.attribute;

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

}
