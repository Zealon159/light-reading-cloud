package cn.zealon.bookstore.common.pojo.book;

import lombok.Data;

import java.util.Date;

/**
 * 图书作者
 * @author: tangyl
 * @since: 2020/3/14
 */
@Data
public class BookAuthor  {

    /** 主键ID */
    protected Integer id;

    /**
     * 作者名称
     */
    private String name;

    /**
     * 作者简介
     */
    private String introduction;

    /**
     * 头像附件id
     */
    private String headImgUrl;

    /** 创建人 */
    protected String creater;
    /** 创建时间 */
    protected Date createTime;
    /** 修改人 */
    protected String updater;
    /** 修改时间 */
    protected Date updateTime;

}
