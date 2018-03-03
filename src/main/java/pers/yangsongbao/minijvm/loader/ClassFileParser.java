package pers.yangsongbao.minijvm.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.clz.AccessFlag;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.clz.ClassIndex;
import pers.yangsongbao.minijvm.constant.*;
import pers.yangsongbao.minijvm.constant.constantInfo.*;
import pers.yangsongbao.minijvm.field.Field;
import pers.yangsongbao.minijvm.interfaze.Interface;
import pers.yangsongbao.minijvm.method.Method;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * @author songbao.yang
 * @date 2017/10/14
 */
public class ClassFileParser {

    private static final String CAFEBABE = "cafebabe";
    private static final Logger logger = LoggerFactory.getLogger(ClassFileParser.class);

    public ClassFile parse(byte[] codes) {
        ClassFile clzFile = new ClassFile();
        ByteCodeIterator iter = new ByteCodeIterator(codes);

        String magicNumber = iter.nextU4ToHexString();
        if (!CAFEBABE.equals(magicNumber)) {
            logger.error("wrong magic number!!!");
            return null;
        }

        clzFile.setMinorVersion(iter.nextU2ToInt());
        clzFile.setMajorVersion(iter.nextU2ToInt());

        clzFile.setConstantPool(parseConstantPool(iter));
        clzFile.setAccessFlag(parseAccessFlag(iter));
        clzFile.setClassIndex(parseClassInfex(iter));

        parseInterfaces(clzFile, iter);
        parseFileds(clzFile, iter);
        parseMethods(clzFile, iter);

        return clzFile;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int constPoolCount = iter.nextU2ToInt();
        ConstantPool pool = new ConstantPool();

        /* 代表常量池容量计数值是从1而不是从0开始的
        *  设计者将第0项常量空出来的目的在于满足后面某些指向常量池的索引值的数据
        *  在特定情况下需要表达“不引用任何一个常量池项目”的含义
        *  这种情况就可以把索引值置为0来表示。
        *  见《深入理解java虚拟机》第2版第168页
        * */
        pool.addConstantInfo(new NullConstantInfo());
        for (int i = 1; i <= constPoolCount - 1; i++) {
            int tag = iter.nextU1toInt();
            switch (tag) {
                case ConstantInfo.UTF8_INFO:
                    int len = iter.nextU2ToInt();
                    byte[] data = iter.getBytes(len);
                    String value = new String(data);
                    Utf8Info utf8Str = new Utf8Info(pool);
                    utf8Str.setLength(len);
                    utf8Str.setValue(value);
                    pool.addConstantInfo(utf8Str);
                    break;
                case ConstantInfo.INTEGER_INFO:
                    byte[] integerBytes = iter.getBytes(4);
                    Integer integerValue = ByteBuffer.wrap(integerBytes).order(ByteOrder.BIG_ENDIAN).getInt();
                    IntegerInfo integerInfo = new IntegerInfo(pool, integerValue);
                    pool.addConstantInfo(integerInfo);
                    break;
                case ConstantInfo.FLOAT_INFO:
                    byte[] floatBytes = iter.getBytes(4);
                    Float floatValue = ByteBuffer.wrap(floatBytes).order(ByteOrder.BIG_ENDIAN).getFloat();
                    FloatInfo floatInfo = new FloatInfo(pool, floatValue);
                    pool.addConstantInfo(floatInfo);
                    break;
                case ConstantInfo.LONG_INFO:
                    byte[] longBytes = iter.getBytes(4);
                    Long longValue = ByteBuffer.wrap(longBytes).order(ByteOrder.BIG_ENDIAN).getLong();
                    LongInfo longInfo = new LongInfo(pool, longValue);
                    pool.addConstantInfo(longInfo);
                    break;
                case ConstantInfo.DOUBLE_INFO:
                    byte[] doubleBytes = iter.getBytes(4);
                    Double doubleValue = ByteBuffer.wrap(doubleBytes).order(ByteOrder.BIG_ENDIAN).getDouble();
                    DoubleInfo doubleInfo = new DoubleInfo(pool, doubleValue);
                    pool.addConstantInfo(doubleInfo);
                    break;
                case ConstantInfo.CLASS_INFO:
                    int utf8Index = iter.nextU2ToInt();
                    ClassInfo clzInfo = new ClassInfo(pool);
                    clzInfo.setUtf8Index(utf8Index);
                    pool.addConstantInfo(clzInfo);
                    break;
                case ConstantInfo.STRING_INFO:
                    StringInfo info = new StringInfo(pool);
                    info.setIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(info);
                    break;
                case ConstantInfo.FIELD_INFO:
                    FieldRefInfo field = new FieldRefInfo(pool);
                    field.setClassInfoIndex(iter.nextU2ToInt());
                    field.setNameAndTypeIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(field);
                    break;
                case ConstantInfo.METHOD_INFO:
                    MethodRefInfo method = new MethodRefInfo(pool);
                    method.setClassInfoIndex(iter.nextU2ToInt());
                    method.setNameAndTypeIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(method);
                    break;
                case ConstantInfo.INTERFACEMETHODREF_INFO:
                    InterfaceMethodrefInfo interfaceMethodrefInfo = new InterfaceMethodrefInfo(pool);
                    interfaceMethodrefInfo.setClassInfoIndex(iter.nextU2ToInt());
                    interfaceMethodrefInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(interfaceMethodrefInfo);
                    break;
                case ConstantInfo.NAME_AND_TYPE_INFO:
                    NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
                    nameType.setNameIndex(iter.nextU2ToInt());
                    nameType.setDescriptorIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(nameType);
                    break;
                case ConstantInfo.METHODHANDLE_INFO:
                    MethodHandleInfo methodHandleInfo = new MethodHandleInfo(pool);
                    methodHandleInfo.setReferenceKind(iter.nextU1toInt());
                    methodHandleInfo.setReferenceIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(methodHandleInfo);
                    break;
                case ConstantInfo.METHODTYPE_INFO:
                    MethodTypeInfo methodTypeInfo = new MethodTypeInfo(pool);
                    methodTypeInfo.setDescriptorIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(methodTypeInfo);
                    break;
                case ConstantInfo.INVOKEDYNAMIC_INFO:
                    InvokeDynamicInfo invokeDynamicInfo = new InvokeDynamicInfo(pool);
                    invokeDynamicInfo.setBootstrapMethodAttrIndex(iter.nextU2ToInt());
                    invokeDynamicInfo.setNameAndTypeIndex(iter.nextU2ToInt());
                    pool.addConstantInfo(invokeDynamicInfo);
                    break;
                default:
                    throw new RuntimeException("the constant pool tag " + tag + " has not been implemented yet.");
            }
        }
        return pool;
    }

    private AccessFlag parseAccessFlag(ByteCodeIterator iter) {
        return new AccessFlag(iter.nextU2ToInt());
    }

    private ClassIndex parseClassInfex(ByteCodeIterator iter) {
        int thisClassIndex = iter.nextU2ToInt();
        int superClassIndex = iter.nextU2ToInt();

        ClassIndex clzIndex = new ClassIndex();
        clzIndex.setThisClassIndex(thisClassIndex);
        clzIndex.setSuperClassIndex(superClassIndex);
        return clzIndex;
    }

    private void parseInterfaces(ClassFile clzFile, ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();
        for (int i = 1; i <= interfaceCount; i++) {
            Interface anInterface = Interface.parse(clzFile.getConstantPool(), iter);
            clzFile.addInterface(anInterface);
        }
    }

    private void parseFileds(ClassFile clzFile, ByteCodeIterator iter) {
        int fieldCount = iter.nextU2ToInt();
        for (int i = 1; i <= fieldCount; i++) {
            Field f = Field.parse(clzFile.getConstantPool(), iter);
            clzFile.addField(f);
        }
    }

    private void parseMethods(ClassFile clzFile, ByteCodeIterator iter) {
        int methodCount = iter.nextU2ToInt();
        for (int i = 1; i <= methodCount; i++) {
            Method m = Method.parse(clzFile, iter);
            clzFile.addMethod(m);
        }
    }
}
