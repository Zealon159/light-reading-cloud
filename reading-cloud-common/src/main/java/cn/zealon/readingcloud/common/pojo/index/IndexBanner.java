package cn.zealon.readingcloud.common.pojo.index;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Banner
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
public class IndexBanner implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /**
     * 名称
     */
    private String name;

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
