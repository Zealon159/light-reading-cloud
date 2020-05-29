package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.result.Result;

/**
 * 搜索服务
 * @author: zealon
 * @since: 2020/5/29
 */
public interface SearchService {

    /**
     * 获取热搜词
     * @param size
     * @return
     */
    Result getHotSearchWordList(Integer size);

    /**
     * 查询结果图书列表
     * @param keyword
     * @param from
     * @param size
     * @return
     */
    Result getSearchResultBooks(String keyword, Integer from, Integer size);
}
