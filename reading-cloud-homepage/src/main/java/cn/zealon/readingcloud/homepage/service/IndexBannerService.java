package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.homepage.vo.IndexBannerVO;

/**
 * Banner服务
 * @author: zealon
 * @since: 2020/4/6
 */
public interface IndexBannerService {

    /**
     * 获取Banner VO
     * @param bannerId
     * @return
     */
    IndexBannerVO getBannerVO(Integer bannerId);
}
