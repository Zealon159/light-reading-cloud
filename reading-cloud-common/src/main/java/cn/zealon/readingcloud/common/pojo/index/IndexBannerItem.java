package cn.zealon.readingcloud.common.pojo.index;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Banner项
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
public class IndexBannerItem implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * bannerid
     */
    private Integer bannerId;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片链接
     */
    private String imgUrl;

    /**
     * 跳转链接
     */
    private String url;

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
