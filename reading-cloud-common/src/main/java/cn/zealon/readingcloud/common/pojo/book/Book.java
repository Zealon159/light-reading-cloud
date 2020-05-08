package cn.zealon.readingcloud.common.pojo.book;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 图书
 */
@Data
public class Book implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 作者
     */
    private Integer authorId;

    /**
     * 分类
     */
    private Integer dicCategory;

    /**
     * 频道id:0全部,1男生,2女生,3出版物
     */
    private Integer dicChannel;

    /**
     * 连载状态
     */
    private Integer dicSerialStatus;

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

    /**
     * 创建者
     */
    private String creater;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新者
     */
    private String updater;

}
