package cn.zealon.readingcloud.homepage.service;

import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.homepage.vo.BooklistBookVO;
import java.util.List;

/**
 * 书单图书服务
 * @author: zealon
 * @since: 2020/4/6
 */
public interface IndexBooklistItemService {

    /**
     * 书单更多分页接口
     * @param booklistId            书单ID
     * @param page                  页数
     * @param limit                 每页数量
     * @return
     */
    Result getBooklistPagingBooks(Integer booklistId, Integer page, Integer limit);

    /**
     * 书单换一换接口
     * @param booklistId            书单ID
     * @param clientRandomNumber    客户端当前随机编号
     * @return
     */
    Result getBooklistExchange(Integer booklistId, Integer clientRandomNumber);

    /**
     * 随机获取书单图书信息
     * @param booklistId            书单ID
     * @param bookIds               图书IDs
     * @param showNumber            显示数量
     * @param clientRandomNumber    客户端当前随机编号
     * @return
     */
    List<BooklistBookVO> getBooklistRandomBooks(Integer booklistId, String bookIds, Integer showNumber, Integer clientRandomNumber, Boolean showLikeCount);

    /**
     * 顺序获取书单图书
     * @param booklistId            书单ID
     * @param bookIds               图书IDs
     * @param showNumber            显示数量
     * @param showLikeCount         显示喜欢数
     * @return
     */
    List<BooklistBookVO> getBooklistOrderBooks(Integer booklistId, String bookIds, Integer showNumber, Boolean showLikeCount);
}
