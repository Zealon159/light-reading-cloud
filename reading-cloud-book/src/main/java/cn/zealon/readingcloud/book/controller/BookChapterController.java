package cn.zealon.readingcloud.book.controller;

import cn.zealon.readingcloud.book.service.BookChapterService;
import cn.zealon.readingcloud.book.vo.BookChapterReadVO;
import cn.zealon.readingcloud.common.pojo.book.BookChapter;
import cn.zealon.readingcloud.common.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 图书章节接口
 * @author: zealon
 * @since: 2019/9/25
 */
@Api(description = "章节查询接口")
@RestController
@RequestMapping("book/chapter")
public class BookChapterController {

    @Autowired
    private BookChapterService bookChapterService;

    @ApiOperation(value = "查询图书章节基本信息" , httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "chapterId", value = "章节ID", dataType = "Integer")
    })
    @ApiResponses({@ApiResponse(code = 200, message = "", response = BookChapter.class)})
    @RequestMapping("/getChapter")
    public Result getChapter(String bookId, Integer chapterId){
        return bookChapterService.getChapterById(bookId, chapterId);
    }

    @ApiOperation(value = "查询图书章节列表信息" , httpMethod = "GET")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 200, message = "", response = BookChapter.class)})
    @RequestMapping("/getChapterList")
    public Result getBookChapterList(String bookId) {
        return this.bookChapterService.getBookChapterListByBookId(bookId);
    }

    @ApiOperation(value = "阅读内容" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "chapterId", value = "章节ID", dataType = "Integer")
    })
    @ApiResponses({@ApiResponse(code = 200, message = "", response = BookChapterReadVO.class)})
    @RequestMapping("/readChapter")
    public Result<BookChapterReadVO> readChapter(String bookId, Integer chapterId){
        return this.bookChapterService.readChapter(bookId, chapterId);
    }
}
