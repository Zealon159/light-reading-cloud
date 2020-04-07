package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.result.Result;

/**
 * 主页服务
 * @author: zealon
 * @since: 2020/4/6
 */
public interface IndexPageConfigService {

    /**
     * 获取精品页VO
     * @param type    页面类型(1.精品页 2.男生 3.女生)
     * @param page    页数
     * @param limit   每页数量
     * @return
     */
    Result getIndexPageByType(Integer type, Integer page, Integer limit);

}
