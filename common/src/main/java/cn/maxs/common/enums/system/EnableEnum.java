package cn.maxs.common.enums.system;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用状态枚举类（通用）
 * 2024/5/14
 *
 * @author Marcel.Maxs
 */
@AllArgsConstructor
@Getter
public enum EnableEnum {
    DISABLE(0, "停用"),
    ENABLE(1, "启用"),
    ;

    private final Integer code;
    private final String description;
}
