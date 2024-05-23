package cn.maxs.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSON统一返回状态码枚举类
 * 注意：如果有需要定制的业务异常状态码，需要把所有异常码都放在这里
 * 否则网关的统一异常处理的时候会返回统一的错误码及错误信息
 * 2024/5/6
 * @author Marcel.Maxs
 */
@AllArgsConstructor
@Getter
public enum ResultStatus {
    // 统一成功默认提示
    SUCCESS(1, "操作成功"),
    // 统一失败默认提示
    FAIL(-1, "操作失败"),

    // 401错误默认提示
    ERR_401(401, "登录已过期，请重新登录"),
    // 403错误默认提示
    ERR_403(403, "您的访问权限不足"),
    // 404错误默认提示
    ERR_404(404, "服务器迷路了～"),
    // 500错误默认提示
    ERR_500(500, "服务器出错了～"),

    // 455错误默认提示（在本系统中定义为参数错误）
    ERR_455(455, "参数错误，请检查"),
    ;
    private final Integer code;
    private final String msg;

    /**
     * 根据错误码获取提示语
     * @param statusCode 错误码
     * @return 提示语
     */
    public static String getMsgByCode(Integer statusCode) {
        String result = "服务器来到了一片未知的领域～";
        for (ResultStatus value : ResultStatus.values()) {
            if(value.getCode().equals(statusCode)){
                result = value.getMsg();
                break;
            }
        }
        return result;
    }
}
