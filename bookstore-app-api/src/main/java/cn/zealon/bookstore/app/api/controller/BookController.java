package cn.zealon.bookstore.app.api.controller;

import cn.zealon.bookstore.app.api.service.BookService;
import cn.zealon.bookstore.app.api.vo.BookVO;
import cn.zealon.bookstore.common.pojo.book.Book;
import cn.zealon.bookstore.common.result.Result;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 图书
 * @author: tangyl
 * @since: 2019/7/4
 */
@Api(description = "图书接口")
@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookService bookService;

    @ApiOperation(value = "查询图书信息（根据bookId）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", required = true, dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 0, message = "", response = BookVO.class)})
    @GetMapping("/getBookDetails")
    public BookVO getBookDetails(String bookId){
        return bookService.getBookDetailsById(bookId);
    }

    @ApiOperation(value = "查询图书（根据bookId）")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "bookId", value = "图书ID", required = true, dataType = "String")
    })
    @ApiResponses({@ApiResponse(code = 0, message = "", response = Book.class)})
    @GetMapping("/getBookById")
    public Result getBookById(String bookId){
        System.out.println("controller currentThread:"+Thread.currentThread().getName());
        return bookService.getBookById(bookId);
    }

    @ApiOperation(value = "查询图书列表")
    @ApiResponses({@ApiResponse(code = 0, message = "", response = BookVO.class)})
    @GetMapping("/getBookList")
    public ResponseEntity<byte[]> getBookList(){
        return bookService.getBookList();
    }

    @ApiOperation(value = "查询图书列表2")
    @ApiResponses({@ApiResponse(code = 0, message = "", response = BookVO.class)})
    @GetMapping("/getBookList2")
    public List<Book> getBookList2(){
        return bookService.getBookList2();
    }
}
