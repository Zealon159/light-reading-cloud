package cn.zealon.readingcloud.common.pojo.index;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 主页配置
 * @author: zealon
 * @since: 2020/4/5
 */
@Data
public class IndexPageConfig implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 页面类型：1.home,2.男生,3.女生
     */
    private Integer pageType;

    /**
     * 配置项类型：1.banner,2.榜单
     */
    private Integer itemType;

    /**
     * 榜单/banner id
     */
    private Integer itemId;

    /**
     * 生效开始时间
     */
    private Date startDate;

    /**
     * 生效截止时间
     */
    private Date endDate;

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
