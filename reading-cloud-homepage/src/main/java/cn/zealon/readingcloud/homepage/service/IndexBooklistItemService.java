package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.homepage.vo.BooklistBookVO;
import java.util.List;

/**
 * 书单图书服务
 * @author: zealon
 * @since: 2020/4/6
 */
public interface IndexBooklistItemService {

    /**
     * 随机获取书单图书信息
     * @param bookIds               图书IDs
     * @param showNumber            显示数量
     * @param clientRandomNumber    客户端当前随机编号
     * @return
     */
    List<BooklistBookVO> getBooklistRandomBooks(String bookIds, Integer showNumber, Integer clientRandomNumber);

    /**
     * 顺序获取书单图书
     * @param bookIds               图书IDs
     * @param showNumber            显示数量
     * @return
     */
    List<BooklistBookVO> getBooklistOrderBooks(String bookIds, Integer showNumber);

}
