package cn.zealon.readingcloud.homepage.dao;

import cn.zealon.readingcloud.common.pojo.index.IndexBannerItem;

import java.util.List;

/**
 * Banner项
 * @author: zealon
 * @since: 2020/4/6
 */
public interface IndexBannerItemMapper {

    IndexBannerItem selectById(Integer id);

    /**
     * 按banner查询明细列表
     * @param bannerId
     * @return
     */
    List<IndexBannerItem> findPageWithResult(Integer bannerId);
}
