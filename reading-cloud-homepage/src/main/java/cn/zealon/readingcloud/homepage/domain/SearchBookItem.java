package cn.zealon.readingcloud.homepage.domain;

import lombok.Data;

/**
 * Book搜索结果项
 * @author: zealon
 * @since: 2020/5/29
 */
@Data
public class SearchBookItem {
    /**
     * 图书id
     */
    private String bookId;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书评分
     */
    private Integer bookScore;

    /**
     * 封面
     */
    private String imgUrl;

    /**
     * 作者名称
     */
    private String author;

    /**
     * 简介
     */
    private String introduction;

    /**
     * 分类
     */
    private Integer dicCategory;

    private String categoryName;
}
