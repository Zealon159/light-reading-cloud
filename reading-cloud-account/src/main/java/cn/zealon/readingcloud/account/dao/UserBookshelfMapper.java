package cn.zealon.readingcloud.account.dao;

import cn.zealon.readingcloud.common.pojo.account.UserBookshelf;
import java.util.List;

/**
 * 用户书架
 * @author: zealon
 * @since: 2020/4/10
 */
public interface UserBookshelfMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(UserBookshelf userBookshelf);

    int updateByPrimaryKey(UserBookshelf userBookshelf);

    UserBookshelf selectById(Integer id);

    List<UserBookshelf> findPageWithResult(Integer userId);
}
