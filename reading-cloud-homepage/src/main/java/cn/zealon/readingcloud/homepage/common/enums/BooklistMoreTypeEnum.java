package cn.zealon.readingcloud.homepage.common.enums;

/**
 * 书单更多枚举
 * @author: zealon
 * @since: 2020/4/6
 */
public enum BooklistMoreTypeEnum {
    NOTHING(1,"无"),
    MORE(2,"更多"),
    EXCHANGE(3,"换一换"),
    LOADING(4,"加载");

    private int value;
    private String name;

    BooklistMoreTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
