package cn.zealon.readingcloud.book.vo;

import lombok.Data;

/**
 * 图书章节信息
 * @author: zealon
 * @since: 2020/5/7
 */
@Data
public class BookChapterVO {
    private Integer id;
    private String name;
    private String content;

    public BookChapterVO(Integer id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
    }
}
