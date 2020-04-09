package cn.zealon.readingcloud.common.enums;

/**
 * 图书分类枚举
 * @author: zealon
 * @since: 2020/4/9
 */
public enum BookCategoryEnum {
    CATEGORY_1(1,"都市小说"),
    CATEGORY_2(2,"东方玄幻"),
    CATEGORY_3(3,"武侠仙侠"),
    CATEGORY_4(4,"悬疑惊悚"),
    CATEGORY_5(5,"唯美同人"),
    CATEGORY_6(6,"网游竞技"),
    CATEGORY_7(7,"科幻未来"),
    CATEGORY_8(8,"历史穿越");

    private Integer value;
    private String name;

    BookCategoryEnum(Integer value, String name) {
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
