package cn.zealon.readingcloud.book.service;

import cn.zealon.readingcloud.book.vo.BookChapterReadVO;
import cn.zealon.readingcloud.common.pojo.book.BookChapter;
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
    Result<BookChapter> getChapterById(String bookId, Integer chapterId);

    /**
     * 阅读章节
     * @param bookId
     * @param chapterId 章节ID(0获取首章节，-1获取末章节)
     * @return
     */
    Result<BookChapterReadVO> readChapter(String bookId, Integer chapterId);
}
