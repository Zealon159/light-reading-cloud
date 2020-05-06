package cn.zealon.readingcloud.account.service;

import cn.zealon.readingcloud.account.bo.UserBookshelfBO;
import cn.zealon.readingcloud.common.result.Result;

/**
 * 书架服务
 * @author: zealon
 * @since: 2020/4/13
 */
public interface UserBookshelfService {

    /**
     * 同步书架
     * @param userId
     * @param bookshelf
     * @return
     */
    Result syncUserBookshelf(Integer userId, UserBookshelfBO bookshelf);

    /**
     * 获取用户书架数据
     * @param userId
     * @return
     */
    Result getUserBookshelf(Integer userId);

    /**
     * 用户书架是否存在该图书
     * @param userId
     * @param bookId
     * @return
     */
    Result userBookshelfExistBook(Integer userId, String bookId);
}
