package cn.zealon.readingcloud.account.service.impl;

import cn.zealon.readingcloud.account.bo.UserBookshelfBO;
import cn.zealon.readingcloud.account.dao.UserBookshelfMapper;
import cn.zealon.readingcloud.account.service.UserBookshelfService;
import cn.zealon.readingcloud.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书架服务
 * @author: zealon
 * @since: 2020/4/13
 */
@Service
public class UserBookshelfServiceImpl implements UserBookshelfService {

    @Autowired
    private UserBookshelfMapper bookshelfMapper;

    @Override
    public Result syncUserBookshelf(Integer userId, UserBookshelfBO bookshelf) {
        return null;
    }

    @Override
    public Result getUserBookshelf(Integer userId) {

        return null;
    }
}
