package me.zji.entity;

/**
 * 角色
 * Created by imyu on 2017/2/12.
 */
public class Role extends Id {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
