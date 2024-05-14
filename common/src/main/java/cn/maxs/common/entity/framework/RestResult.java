package cn.maxs.common.entity.framework;

import cn.maxs.common.enums.ResultStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一JSON返回结果
 * 2024/5/6
 * @author Marcel.Maxs
 */
@Data
@NoArgsConstructor
public class RestResult<T> {

    /**
     * 业务状态码
     */
    private Integer code;
    /**
     * 返回提示信息
     */
    private String msg;
    /**
     * 结果集
     */
    private T data;

    public RestResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T>RestResult<T> ok() {
        return new RestResult<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), null);
    }

    public static <T>RestResult<T> ok(T data) {
        return new RestResult<>(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMsg(), data);
    }

    public static <T>RestResult<T> ok(String msg, T data) {
        return new RestResult<>(ResultStatus.SUCCESS.getCode(), msg, data);
    }

    public static <T>RestResult<T> ok(Integer code, String msg, T data) {
        return new RestResult<>(code, msg, data);
    }

    public static <T>RestResult<T> fail() {
        return new RestResult<>(ResultStatus.FAIL.getCode(), ResultStatus.FAIL.getMsg(), null);
    }

    public static <T>RestResult<T> fail(String msg) {
        return new RestResult<>(ResultStatus.FAIL.getCode(), msg, null);
    }

    public static <T>RestResult<T> fail(Integer code, String msg) {
        return new RestResult<>(code, msg, null);
    }

    public static <T>RestResult<T> result(ResultStatus status, T data) {
        return new RestResult<>(status.getCode(), status.getMsg(), data);
    }
}
