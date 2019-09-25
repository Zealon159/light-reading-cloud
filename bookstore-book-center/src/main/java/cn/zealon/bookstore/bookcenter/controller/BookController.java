package cn.zealon.bookstore.bookcenter.controller;

import cn.zealon.bookstore.bookcenter.service.BookService;
import cn.zealon.bookstore.common.model.NomalBook;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 图书接口
 * @author: zealon
 * @since: 2019/4/3
 */
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询图书信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 610, message = "", response = NomalBook.class)})
    @RequestMapping("/getBookById")
    public NomalBook getBookById(String bookId){
        return bookService.getBookById(bookId);
    }

    @RequestMapping("/getBookList")
    public List<NomalBook> getBookList(){
        return bookService.getBookList();
    }
}
