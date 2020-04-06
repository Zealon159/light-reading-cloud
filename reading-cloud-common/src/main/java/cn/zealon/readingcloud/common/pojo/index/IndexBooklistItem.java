package cn.zealon.readingcloud.common.pojo.index;

import java.util.List;
//import org.apache.ibatis.annotations.Param;

/**
 * 书单配置明细项
 * @author: zealon
 * @since: 2020/4/6
 */
public interface IndexBooklistItem {
    /**
     * 查询书单图书ID
     * @param booklistId
     * @return
     */
    List<String> selectBookIds(Integer booklistId,Integer limit);
}
