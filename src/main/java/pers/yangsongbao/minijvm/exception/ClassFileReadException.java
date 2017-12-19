package pers.yangsongbao.minijvm.exception;

/**
 * @author songbao.yang
 * @date 2017/10/14
 */
public class ClassFileReadException extends RuntimeException {

    public ClassFileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
