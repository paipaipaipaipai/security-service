package org.potholes.enums;

/**
 * 隐藏
 */
public enum HiddenEnum {

    HIDE("1", "隐藏"),
    SHOW("2", "展示");

    private String type;
    private String name;

    private HiddenEnum(String type, String name) {
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
        HiddenEnum[] values = HiddenEnum.values();
        for (HiddenEnum value : values) {
            if (value.getType().equals(type)) {
                return value.name;
            }
        }
        return null;
    }

}
