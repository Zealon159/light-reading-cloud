package cn.zealon.bookstore.app.api.controller;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: tangyl
 * @since: 2019/9/25
 */
@Api(description = "图书章节接口")
@RestController
@RequestMapping("book/chapter")
public class BookChapterController {

    @ApiOperation(value = "查询章节内容", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "chapterId", value = "章节ID", required = true, dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 200, message = "", response = String.class)})
    @RequestMapping("/getChapterContent")
    public String getChapterContent(String bookId,String chapterId){
        System.out.println("controller currentThread:"+Thread.currentThread().getName());
        return "";
    }
}
