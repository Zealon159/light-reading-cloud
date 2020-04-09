package cn.zealon.readingcloud.common.pojo.index;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 书单配置明细项
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
public class IndexBooklistItem implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 书单id
     */
    private Integer booklistId;

    /**
     * 图书id
     */
    private String bookId;

    /**
     * 排序
     */
    private Integer sortNumber;

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
