package pers.yangsongbao.minijvm.loader;

import com.google.common.base.Joiner;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.exception.ClassFileReadException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 * @date 2017/10/14
 */
public class ClassFileLoader {

    private static final Logger logger = LoggerFactory.getLogger(ClassFileLoader.class);
    private List<String> classPaths = new ArrayList<String>();

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);
    }

    private byte[] readBinaryCode(String className) {
        className = className.replace('.', File.separatorChar) + ".class";
        for (String path : classPaths) {
            String classFileName = path + File.separatorChar + className;
            byte[] classFileBytes = loadClassFile(classFileName);
            if (classFileBytes != null) {
                return classFileBytes;
            }
        }
        return null;
    }

    private byte[] loadClassFile(String classFileName) {
        try {
            return IOUtils.toByteArray(new FileInputStream(new File(classFileName)));
        } catch (IOException e) {
            logger.error("fail to read class file to byte array");
            throw new ClassFileReadException("fail to read class file to byte array", e);
        }
    }

    public void addClassPath(String path) {
        if (!classPaths.contains(path)) {
            classPaths.add(path);
        }
    }

    public String getClassPath() {
        Joiner joiner = Joiner.on(";");
        return joiner.join(classPaths);
    }
}
