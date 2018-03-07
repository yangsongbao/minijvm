package pers.yangsongbao.minijvm.print;

import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;
import pers.yangsongbao.minijvm.constant.constantInfo.NullConstantInfo;

/**
 * @author songbao.yang
 * @date 2018/3/5
 */
public class ConstantPoolPrinter {

    ConstantPool constantPool;

    public ConstantPoolPrinter(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void print() {
        ConstantPoolVisitor constantPoolVisitor = new ConstantPoolVisitor();
        System.out.println("Constant Pool:");
        for (int i = 1; i < constantPool.getSize(); i++) {
            ConstantInfo constantInfo = constantPool.getConstantInfo(i);
            if (constantInfo instanceof NullConstantInfo){
                continue;
            }
            System.out.print("#" + i + "=");
            constantInfo.accept(constantPoolVisitor);
        }
    }
}
