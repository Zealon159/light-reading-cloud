package cn.zealon.readingcloud.homepage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

/**
 * Banner VO
 * @author: zealon
 * @since: 2020/4/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexBannerVO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private List<BannerItemVO> items;


}
