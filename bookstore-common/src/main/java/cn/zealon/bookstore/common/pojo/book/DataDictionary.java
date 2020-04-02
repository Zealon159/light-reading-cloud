package cn.zealon.bookstore.common.pojo.book;

import lombok.Data;
import java.io.Serializable;

/**
 * 数据字典
 * @author: tangyl
 * @since: 2020/4/1
 */
@Data
public class DataDictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 字典类型
     */
    private String dicType;

    /**
     * 类型名称
     */
    private String dicTypeName;

    /**
     * 字典编码
     */
    private Integer code;

    /**
     * 字典名称
     */
    private String name;

    /**
     * 排序
     */
    private Integer sortNumber;
}
