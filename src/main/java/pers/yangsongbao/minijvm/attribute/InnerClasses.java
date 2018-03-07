package pers.yangsongbao.minijvm.attribute;

import pers.yangsongbao.minijvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author songbao.yang
 * @date 2018/3/4
 */
public class InnerClasses extends AttributeInfo {
    List<ClassInfo> classes = new ArrayList<>();

    public InnerClasses(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public static InnerClasses parse(ByteCodeIterator iter, int attrNameIndex, int attrLen) {
        InnerClasses innerClasses = new InnerClasses(attrNameIndex, attrLen);
        int number = iter.nextU2ToInt();
        for (int i = 1; i <= number; i++) {
            int innerClassInfoIndex = iter.nextU2ToInt();
            int outerClassInfoIndex = iter.nextU2ToInt();
            int innerNameIndex = iter.nextU2ToInt();
            int innerClassAccessFlags = iter.nextU2ToInt();
            ClassInfo classInfo = new ClassInfo(innerClassInfoIndex, outerClassInfoIndex, innerNameIndex, innerClassAccessFlags);
            innerClasses.addClassInfo(classInfo);
        }
        return innerClasses;
    }

    public void addClassInfo(ClassInfo classInfo) {
        classes.add(classInfo);
    }

    private static class ClassInfo {
        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;

        public ClassInfo(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex, int innerClassAccessFlags) {
            this.innerClassInfoIndex = innerClassInfoIndex;
            this.outerClassInfoIndex = outerClassInfoIndex;
            this.innerNameIndex = innerNameIndex;
            this.innerClassAccessFlags = innerClassAccessFlags;
        }

        public int getInnerClassInfoIndex() {
            return innerClassInfoIndex;
        }

        public void setInnerClassInfoIndex(int innerClassInfoIndex) {
            this.innerClassInfoIndex = innerClassInfoIndex;
        }

        public int getOuterClassInfoIndex() {
            return outerClassInfoIndex;
        }

        public void setOuterClassInfoIndex(int outerClassInfoIndex) {
            this.outerClassInfoIndex = outerClassInfoIndex;
        }

        public int getInnerNameIndex() {
            return innerNameIndex;
        }

        public void setInnerNameIndex(int innerNameIndex) {
            this.innerNameIndex = innerNameIndex;
        }

        public int getInnerClassAccessFlags() {
            return innerClassAccessFlags;
        }

        public void setInnerClassAccessFlags(int innerClassAccessFlags) {
            this.innerClassAccessFlags = innerClassAccessFlags;
        }
    }


}
