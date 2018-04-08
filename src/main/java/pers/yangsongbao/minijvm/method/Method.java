package pers.yangsongbao.minijvm.method;

import com.google.common.base.Splitter;
import pers.yangsongbao.minijvm.attribute.AttributeInfo;
import pers.yangsongbao.minijvm.attribute.CodeAttr;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.constant.constantInfo.Utf8Info;
import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.*;

/**
 * @author songbao.yang
 */
public class Method {

    private static final Set<String> acceptableAttribute;

    static {
        acceptableAttribute = new HashSet<>();
        acceptableAttribute.add(AttributeInfo.CODE);
        acceptableAttribute.add(AttributeInfo.DEPRECATED);
        acceptableAttribute.add(AttributeInfo.EXCEPTIONS);
        acceptableAttribute.add(AttributeInfo.SIGNATURE);
        acceptableAttribute.add(AttributeInfo.SYNTHETIC);
        acceptableAttribute.add(AttributeInfo.RUNTIME_VISIBLE_ANNOTATIONS);
        acceptableAttribute.add(AttributeInfo.RUNTIME_INVISIBLE_ANNOTATIONS);
        acceptableAttribute.add(AttributeInfo.RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS);
        acceptableAttribute.add(AttributeInfo.RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS);
        acceptableAttribute.add(AttributeInfo.ANNOTATION_DEFAULT);
    }

    private int accessFlag;
    private int nameIndex;
    private int descriptorIndex;
    private ClassFile clzFile;
    private Map<String, AttributeInfo> attributes;

    private Method(ClassFile clzFile, int accessFlag, int nameIndex, int descriptorIndex) {
        this.clzFile = clzFile;
        this.accessFlag = accessFlag;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = new HashMap<>();
    }

    public static Method parse(ClassFile clzFile, ByteCodeIterator iter) {
        int accessFlag = iter.nextU2ToInt();
        int nameIndex = iter.nextU2ToInt();
        int descIndex = iter.nextU2ToInt();
        int attribCount = iter.nextU2ToInt();

        Method method = new Method(clzFile, accessFlag, nameIndex, descIndex);
        for (int j = 1; j <= attribCount; j++) {
            int attrNameIndex = iter.nextU2ToInt();
            iter.back(2);
            String attrName = clzFile.getConstantPool().getUTF8String(attrNameIndex);
            if (acceptableAttribute.contains(attrName)) {
                AttributeInfo attributeInfo = AttributeInfo.parse(clzFile, iter);
                method.addAttribute(attrName, attributeInfo);
            } else {
                throw new RuntimeException("unsupported method attribute " + attrName);
            }

        }
        return method;
    }

    public void addAttribute(String attrName, AttributeInfo attributeInfo) {
        attributes.put(attrName, attributeInfo);
    }

    public CodeAttr getCodeAttr() {
        return (CodeAttr) attributes.get(AttributeInfo.CODE);
    }

    public String getMethodName() {
        return getClzFile().getConstantPool().getUTF8String(nameIndex);
    }

    private String getParamAndReturnType() {
        Utf8Info nameAndTypeInfo = (Utf8Info) this.getClzFile()
                .getConstantPool().getConstantInfo(this.getDescriptorIndex());
        return nameAndTypeInfo.getValue();
    }

    public List<String> getParameterList() {
        // e.g. (Ljava/util/List;Ljava/lang/String;II)V
        String paramAndType = getParamAndReturnType();

        int first = paramAndType.indexOf("(");
        int last = paramAndType.lastIndexOf(")");

        // e.g. Ljava/util/List;Ljava/lang/String;II
        String param = paramAndType.substring(first + 1, last);
        List<String> paramList = new ArrayList<>();
        List<String> params = Splitter.on(";").splitToList(param);
        for (String paramItem : params) {
            if (paramItem.equals("")) {
                continue;
            }
            char charAtPos = paramItem.charAt(0);
            if (charAtPos == 'L') {
                paramList.add(paramItem.substring(1));
            } else {
                List<String> chars = Splitter.on("").splitToList(paramItem);
                paramList.addAll(chars);
            }
        }
        return paramList;
    }

    public ClassFile getClzFile() {
        return clzFile;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
