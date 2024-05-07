package cn.maxs.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * JSON统一返回状态码枚举类
 * 2024/5/6
 * @author Marcel.Maxs
 */
@AllArgsConstructor
@Getter
public enum ResultStatus {
    SUCCESS(1, "操作成功"),
    FAIL(-1, "操作失败");

    private final Integer code;
    private final String msg;
}
