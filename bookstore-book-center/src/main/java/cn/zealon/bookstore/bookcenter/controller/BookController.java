package cn.zealon.bookstore.bookcenter.controller;

import cn.zealon.bookstore.bookcenter.service.BookService;
import cn.zealon.bookstore.common.pojo.book.Book;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation(value = "查询图书信息" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 610, message = "", response = Book.class)})
    @GetMapping("/getBookById")
    public Book getBookById(String bookId){
        return bookService.getBookById(bookId);
    }

    @GetMapping("/getBookList")
    public List<Book> getBookList(){
        return bookService.getBookList();
    }
}
