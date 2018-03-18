package pers.yangsongbao.minijvm.cmd;


import pers.yangsongbao.minijvm.clz.ClassFile;
import pers.yangsongbao.minijvm.engine.ExecuteResult;
import pers.yangsongbao.minijvm.engine.JavaObject;
import pers.yangsongbao.minijvm.engine.StackFrame;

/**
 * @author songbao.yang
 */
public class NoOperandCmd extends BaseByteCodeCommand{

	public NoOperandCmd(ClassFile clzFile, String opCode) {
		super(clzFile, opCode);
	}

	@Override
	public void execute(StackFrame frame, ExecuteResult result) {
	}
	@Override
	public  int getLength(){
		return 1;
	}

	@Override
	public String toString() {
		return this.getOffset()+":" +this.getOpCode() + " "+ this.getReadableCodeText();
	}

}
