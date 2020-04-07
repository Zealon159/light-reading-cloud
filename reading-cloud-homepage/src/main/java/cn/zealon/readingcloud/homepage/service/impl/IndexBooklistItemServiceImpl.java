package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.book.Book;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.homepage.service.BookCenterService;
import cn.zealon.readingcloud.homepage.service.IndexBooklistItemService;
import cn.zealon.readingcloud.homepage.vo.BooklistBookVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 书单图书服务
 * @author: zealon
 * @since: 2020/4/7
 */
@Service
public class IndexBooklistItemServiceImpl implements IndexBooklistItemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexBooklistItemServiceImpl.class);

    @Autowired
    private BookCenterService bookCenterService;

    @Autowired
    private RedisService redisService;

    @Override
    public List<BooklistBookVO> getBooklistRandomBooks(String bookIds, Integer showNumber, Integer clientRandomNumber) {
        // todo
        return null;
    }

    @Override
    public List<BooklistBookVO> getBooklistOrderBooks(String bookIds, Integer showNumber) {
        List<BooklistBookVO> vos = new ArrayList<>();
        String[] bookIdArray = bookIds.split(",");
        for (int i = 0; i < bookIdArray.length; i++) {
            String bookId = bookIdArray[i];
            BooklistBookVO vo = this.getBookVO(bookId);
            vos.add(vo);
        }
        return vos;
    }

    /**
     * 获取榜单图书VO
     * @param bookId
     * @return
     */
    private BooklistBookVO getBookVO(String bookId){
        Result result = this.bookCenterService.getBookById(bookId);
        if (result.getData() == null) {
            LOGGER.warn("图书资源中心获取Book:{}失败！返回了空数据", bookId);
            return null;
        }
        Book book = (Book) result.getData();
        BooklistBookVO vo = new BooklistBookVO();
        BeanUtils.copyProperties(book, vo);
        // todo 获取分类

        return vo;
    }
}
