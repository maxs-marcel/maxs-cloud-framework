package cn.maxs.common.exception;

/**
 * 业务自定义异常
 * @author Marcel.Maxs
 */
public class BusinessException extends Throwable {
    public BusinessException(String msg){
        super(msg);
    }
}
