package cn.zealon.bookstore.bookcenter.service;

/**
 * 图书章节服务
 * @author: tangyl
 * @since: 2019/9/25
 */
public interface BookChapterService {

    /**
     * 获取章节内容
     * @param bookId
     * @param chapterId
     * @return
     */
    String getChapterContent(String bookId,String chapterId);
}
