package cn.zealon.bookstore.bookcenter.service;

import cn.zealon.bookstore.common.pojo.book.Book;
import cn.zealon.bookstore.common.result.Result;

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
    Result getBookById(String bookId);

}
