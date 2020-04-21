package cn.zealon.readingcloud.homepage.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * 书单VO
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
public class IndexBooklistVO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    /** 名称 */
    private String name;

    /**
     * 书单样式：1.横版(2*4),2.竖版(1*3),3.1+4,4.竖版more
     */
    private Integer style;

    /**
     * 更多类型：1.无,2.更多,3.换一换
     */
    private Integer moreType;

    /** 更多链接 */
    private String moreUrl;

    /** 换一换随机编码 */
    private Integer randomNumber;

    /** 榜单图书 */
    private List<BooklistBookVO> books;

    /** 是否显示喜欢看 */
    private Boolean showLikeCount;
}
