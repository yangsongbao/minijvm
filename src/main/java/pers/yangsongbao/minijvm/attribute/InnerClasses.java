package pers.yangsongbao.minijvm.attribute;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author songbao.yang
 * @date 2018/3/4
 */
public class InnerClasses extends AttributeInfo {
    private int numberOfClasses;
    List<ClassInfo> classes = new ArrayList<>();

    public InnerClasses(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);
    }

    public void addClassInfo(ClassInfo classInfo) {
        classes.add(classInfo);
    }

    private static class ClassInfo {
        private int innerClassInfoIndex;
        private int outerClassInfoIndex;
        private int innerNameIndex;
        private int innerClassAccessFlags;

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
