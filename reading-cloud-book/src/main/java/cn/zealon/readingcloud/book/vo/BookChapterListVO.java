package cn.zealon.readingcloud.book.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 章节目录列表VO
 * @author: zealon
 * @since: 2020/5/10
 */
@Data
public class BookChapterListVO  implements Serializable {

    private static final Long serialVersionUID = 1L;

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
     * 锁章状态(0:无,1:锁章)
     */
    private Boolean lockStatus;

    private Integer sortNumber;
}
