package cn.zealon.bookstore.bookcenter.controller;

import cn.zealon.bookstore.bookcenter.service.BookChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图书章节接口
 * @author: tangyl
 * @since: 2019/9/25
 */
@RestController
@RequestMapping("book/chapter")
public class BookChapterController {

    @Autowired
    private BookChapterService bookChapterService;

    @RequestMapping("/getChapterContent")
    public String getChapterContent(String bookId, String chapterId){
        return bookChapterService.getChapterContent(bookId, chapterId);
    }
}
