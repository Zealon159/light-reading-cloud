package cn.zealon.readingcloud.common.enums;

/**
 * 图书连载状态枚举
 * @author: zealon
 * @since: 2020/4/6
 */
public enum BookSerialStatusEnum {
    SERIAL(1,"连载"),
    SUSPEND(2,"暂更"),
    END(3,"完结");

    private Integer value;
    private String name;

    BookSerialStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
