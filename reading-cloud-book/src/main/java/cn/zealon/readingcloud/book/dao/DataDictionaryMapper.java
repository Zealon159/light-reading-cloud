package cn.zealon.readingcloud.book.dao;

import cn.zealon.readingcloud.common.pojo.book.DataDictionary;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 数据字典
 * @author: tangyl
 * @since: 2020/3/13
 */
public interface DataDictionaryMapper {

    DataDictionary selectById(Integer id);

    DataDictionary selectByDicTypeAndCode(@Param("dicType") String dicType, @Param("code") Integer code);

    List<DataDictionary> findPageWithResult(@Param("dicType") String dicType);

    Integer findPageWithCount(@Param("dicType") String dicType);

}
