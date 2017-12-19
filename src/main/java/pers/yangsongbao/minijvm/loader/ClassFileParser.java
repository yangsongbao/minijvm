package pers.yangsongbao.minijvm.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.clz.AccessFlag;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.clz.ClassIndex;
import pers.yangsongbao.minijvm.constant.*;
import pers.yangsongbao.minijvm.field.Field;
import pers.yangsongbao.minijvm.method.Method;

import java.io.UnsupportedEncodingException;

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

        ConstantPool pool = parseConstantPool(iter);
        clzFile.setConstantPool(pool);

        AccessFlag flag = parseAccessFlag(iter);
        clzFile.setAccessFlag(flag);

        ClassIndex clzIndex = parseClassInfex(iter);
        clzFile.setClassIndex(clzIndex);

        parseInterfaces(iter);
        parseFileds(clzFile, iter);
        parseMethods(clzFile, iter);

        return clzFile;
    }

    private ConstantPool parseConstantPool(ByteCodeIterator iter) {
        int constPoolCount = iter.nextU2ToInt();
        ConstantPool pool = new ConstantPool();

        //
        pool.addConstantInfo(new NullConstantInfo());
        for (int i = 1; i <= constPoolCount - 1; i++) {
            int tag = iter.nextU1toInt();
            switch (tag) {
                case ConstantInfo.CLASS_INFO:
                    int utf8Index = iter.nextU2ToInt();
                    ClassInfo clzInfo = new ClassInfo(pool);
                    clzInfo.setUtf8Index(utf8Index);
                    pool.addConstantInfo(clzInfo);
                    break;
                case ConstantInfo.UTF8_INFO:
                    int len = iter.nextU2ToInt();
                    byte[] data = iter.getBytes(len);
                    String value = null;
                    try {
                        value = new String(data, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        logger.error("parse utf8_info error", e);
                    }
                    UTF8Info utf8Str = new UTF8Info(pool);
                    utf8Str.setLength(len);
                    utf8Str.setValue(value);
                    pool.addConstantInfo(utf8Str);
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
                case ConstantInfo.NAME_AND_TYPE_INFO:
                    NameAndTypeInfo nameType = new NameAndTypeInfo(pool);
                    nameType.setIndex1(iter.nextU2ToInt());
                    nameType.setIndex2(iter.nextU2ToInt());
                    pool.addConstantInfo(nameType);
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

    private void parseInterfaces(ByteCodeIterator iter) {
        int interfaceCount = iter.nextU2ToInt();
        logger.info("interfaceCount:{}", interfaceCount);
        if (interfaceCount > 0) {
            throw new RuntimeException("interfaces has not been implemented yet.");
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
