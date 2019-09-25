package cn.zealon.bookstore.bookcenter.service;

import cn.zealon.bookstore.common.model.NomalBook;

import java.util.List;

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
    NomalBook getBookById(String bookId);

    List<NomalBook> getBookList();
}
