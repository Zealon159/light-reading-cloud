package cn.zealon.readingcloud.book.vo;

import lombok.Data;

/**
 * 章节阅读VO
 * @author: zealon
 * @since: 2020/5/6
 */
@Data
public class BookChapterReadVO {
    private BookChapterVO current;
    private BookChapterVO pre;
    private BookChapterVO next;
}
