package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.pojo.book.Book;

/**
 * 图书中心服务
 * @author: zealon
 * @since: 2019/7/4
 */
public interface BookCenterService {

    /**
     * 获取图书详情
     * @param bookId
     * @return
     */
    Book getBookById(String bookId);
}
