package cn.zealon.bookstore.common.pojo.book;

import lombok.Data;

/**
 * 章节
 * @author: zealon
 * @since: 2020/3/18
 */
@Data
public class BookChapter {

    /** 主键ID */
    protected Integer id;

    /**
     * 所属图书
     */
    private Integer bookId;

    /**
     * 章节名称
     */
    private String name;

    /**
     * 章节内容
     */
    private String content;

    /**
     * 锁章状态(0:无,1:锁章)
     */
    private Boolean lockStatus;

    /**
     * 排序
     */
    private Integer sortNumber;
}
