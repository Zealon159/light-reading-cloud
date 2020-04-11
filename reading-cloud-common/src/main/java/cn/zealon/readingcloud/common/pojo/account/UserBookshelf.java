package cn.zealon.readingcloud.common.pojo.account;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户书架
 * @author: zealon
 * @since: 2020/4/10
 */
@Data
public class UserBookshelf implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户
     */
    private Integer userId;

    /**
     * 图书id
     */
    private String bookId;

    /**
     * 图书最后章节id
     */
    private Integer lastChapterId;

    /**
     * 最后阅读时间
     */
    private Date lastReadTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
