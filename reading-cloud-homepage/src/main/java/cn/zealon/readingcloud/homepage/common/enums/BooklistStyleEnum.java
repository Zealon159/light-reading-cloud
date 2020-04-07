package cn.zealon.readingcloud.homepage.common.enums;

/**
 * 榜单类型枚举
 * @author: zealon
 * @since: 2020/4/6
 */
public enum BooklistStyleEnum {
    CATEGORY(1,"书籍分类"),
    CHANNEL(2,"所属频道"),
    SERIAL_STATUS(3,"连载状态")
    ;

    private Integer value;
    private String name;

    BooklistStyleEnum(Integer value, String name) {
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
