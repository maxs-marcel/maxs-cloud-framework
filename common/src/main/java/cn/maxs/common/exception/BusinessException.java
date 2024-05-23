package cn.maxs.common.exception;

import cn.maxs.common.enums.ResultStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 业务自定义异常
 * @author Marcel.Maxs
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException extends RuntimeException {
    Integer code;
    String msg;

    public BusinessException(){
        super();
        this.code = ResultStatus.ERR_500.getCode();
        this.msg = ResultStatus.ERR_500.getMsg();
    }

    public BusinessException(String msg){
        super(msg);
        this.code = ResultStatus.ERR_500.getCode();
        this.msg = msg;
    }

    public BusinessException(Integer code, String msg){
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(ResultStatus resultStatus){
        super(resultStatus.getMsg());
        this.code = resultStatus.getCode();
        this.msg = resultStatus.getMsg();
    }
}
