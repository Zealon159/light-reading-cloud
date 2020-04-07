package cn.zealon.readingcloud.homepage.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Banner明细VO
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
public class BannerItemVO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

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
}
