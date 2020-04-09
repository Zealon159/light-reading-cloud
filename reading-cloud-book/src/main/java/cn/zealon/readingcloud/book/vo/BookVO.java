package cn.zealon.readingcloud.book.vo;

import lombok.Data;

/**
 * 图书VO
 * @author: zealon
 * @since: 2020/4/9
 */
@Data
public class BookVO {

    private Integer id;

    /**
     * 作者
     */
    private Integer authorId;

    /**
     * 分类
     */
    private Integer dicCategory;

    private String categoryName;

    /**
     * 频道id:0全部,1男生,2女生,3出版物
     */
    private Integer dicChannel;

    /**
     * 连载状态
     */
    private Integer dicSerialStatus;

    private String serialStatusName;

    /**
     * 状态：0下架，1上架
     */
    private Boolean onlineStatus;

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
     * 关键词
     */
    private String keyWord;

    /**
     * 封面
     */
    private String imgUrl;

    /**
     * 作者名称
     */
    private String authorName;

    /**
     * 简介
     */
    private String introduction;

    /**
     * isbn
     */
    private String isbn;

    /**
     * 字数
     */
    private Integer wordCount;
}
