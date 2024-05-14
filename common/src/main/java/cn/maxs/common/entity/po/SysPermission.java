package cn.maxs.common.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 系统-权限
 * @TableName sys_permission
 */
@TableName(value ="sys_permission")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysPermission implements Serializable {
    /**
     * 权限id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 父级权限id
     */
    private Long parentId;

    /**
     * 权限标识符
     */
    private String permissionCode;

    /**
     * 访问路径
     */
    private String accessPath;

    /**
     * 权限类型（1：目录；2：菜单；3：按钮）
     */
    private Integer permissionType;

    /**
     * 描述
     */
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}