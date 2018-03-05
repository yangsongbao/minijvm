package pers.yangsongbao.minijvm.print;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.yangsongbao.minijvm.constant.ConstantInfo;
import pers.yangsongbao.minijvm.constant.ConstantPool;

/**
 *
 * @author songbao.yang
 * @date 2018/3/5
 */
public class ConstantPoolPrinter {
    private static final Logger log = LoggerFactory.getLogger(ConstantPoolPrinter.class);

    ConstantPool constantPool;

    public ConstantPoolPrinter(ConstantPool constantPool) {
        this.constantPool = constantPool;
    }

    public void print() {
        ConstantPoolVisitor constantPoolVisitor = new ConstantPoolVisitor();
        System.out.println("Constant Pool:");
        for(int i = 1; i < constantPool.getSize(); i++){
            ConstantInfo constantInfo = constantPool.getConstantInfo(i);
            System.out.print("#"+i+"=");
            constantInfo.accept(constantPoolVisitor);
        }
    }
}
