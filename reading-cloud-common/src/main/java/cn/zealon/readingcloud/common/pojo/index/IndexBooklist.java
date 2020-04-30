package cn.zealon.readingcloud.common.pojo.index;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 主页书单配置
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
public class IndexBooklist implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 显示数量
     */
    private Integer showNumber;

    /**
     * 书单样式：1.横版(2*4),2.竖版(1*3),3.1+4,4.竖版more
     */
    private Integer style;

    /**
     * 更多类型：1.无,2.更多,3.换一换
     */
    private Integer moreType;

    /**
     * 更多链接
     */
    private String moreUrl;

    /**
     * 图书ids
     */
    private String bookIds;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

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

    /** 是否显示喜欢数 */
    private Boolean showLikeCount;
}
