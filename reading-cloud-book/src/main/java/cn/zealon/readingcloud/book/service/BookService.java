package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.result.Result;

/**
 * 图书服务
 * @author: zealon
 * @since: 2019/7/4
 */
public interface BookService {

    /**
     * 查询图书详情
     * @param bookId
     * @return
     */
    Result getBookById(String bookId);

}
