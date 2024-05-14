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
 * 系统-角色
 * @TableName sys_role
 */
@TableName(value ="sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRole implements Serializable {
    /**
     * 角色id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescription;

    /**
     * 角色状态（0：禁用；1：启用）
     */
    private Integer roleState;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}