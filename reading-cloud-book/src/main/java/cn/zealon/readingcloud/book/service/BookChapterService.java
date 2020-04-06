package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.common.result.Result;

/**
 * 图书章节服务
 * @author: tangyl
 * @since: 2019/9/25
 */
public interface BookChapterService {

    /**
     * 获取章节目录
     * @param bookId
     * @return
     */
    Result getBookChapterListByBookId(String bookId);

    /**
     * 获取章节内容
     * @param chapterId
     * @return
     */
    Result getChapterById(String bookId, Integer chapterId);
}
