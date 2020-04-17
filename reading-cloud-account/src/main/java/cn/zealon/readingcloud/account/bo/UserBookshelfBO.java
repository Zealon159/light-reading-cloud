package cn.zealon.readingcloud.account.bo;

import lombok.Data;

/**
 * 用户书架上报数据 BO
 * @author: zealon
 * @since: 2020/4/13
 */
@Data
public class UserBookshelfBO {

    private Integer id;

    /**
     * 同步类型：
     * 1.新增  2.更新  3.删除
     */
    private int syncType;

    /** 图书id */
    private String bookId;

    /** 最后章节ID */
    private Integer lastChapterId;
}
