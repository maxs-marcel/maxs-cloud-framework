package cn.maxs.common.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 系统-权限
 * @TableName sys_permission
 */
@TableName(value ="sys_permission")
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

    /**
     * 权限id
     */
    public Long getId() {
        return id;
    }

    /**
     * 权限id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 权限名称
     */
    public String getPermissionName() {
        return permissionName;
    }

    /**
     * 权限名称
     */
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    /**
     * 父级权限id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父级权限id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 权限标识符
     */
    public String getPermissionCode() {
        return permissionCode;
    }

    /**
     * 权限标识符
     */
    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    /**
     * 访问路径
     */
    public String getAccessPath() {
        return accessPath;
    }

    /**
     * 访问路径
     */
    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    /**
     * 权限类型（1：目录；2：菜单；3：按钮）
     */
    public Integer getPermissionType() {
        return permissionType;
    }

    /**
     * 权限类型（1：目录；2：菜单；3：按钮）
     */
    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }

    /**
     * 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysPermission other = (SysPermission) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPermissionName() == null ? other.getPermissionName() == null : this.getPermissionName().equals(other.getPermissionName()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getPermissionCode() == null ? other.getPermissionCode() == null : this.getPermissionCode().equals(other.getPermissionCode()))
            && (this.getAccessPath() == null ? other.getAccessPath() == null : this.getAccessPath().equals(other.getAccessPath()))
            && (this.getPermissionType() == null ? other.getPermissionType() == null : this.getPermissionType().equals(other.getPermissionType()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPermissionName() == null) ? 0 : getPermissionName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getPermissionCode() == null) ? 0 : getPermissionCode().hashCode());
        result = prime * result + ((getAccessPath() == null) ? 0 : getAccessPath().hashCode());
        result = prime * result + ((getPermissionType() == null) ? 0 : getPermissionType().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", permissionName=").append(permissionName);
        sb.append(", parentId=").append(parentId);
        sb.append(", permissionCode=").append(permissionCode);
        sb.append(", accessPath=").append(accessPath);
        sb.append(", permissionType=").append(permissionType);
        sb.append(", description=").append(description);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}