package org.potholes.enums;

/**
 * 状态
 */
public enum StatusEnum {

    NORMAL("1", "启用"),
    DELETE("2", "禁用");

    private String type;
    private String name;

    private StatusEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByType(String type) {
        StatusEnum[] values = StatusEnum.values();
        for (StatusEnum value : values) {
            if (value.getType().equals(type)) {
                return value.name;
            }
        }
        return null;
    }

}
