package cn.zealon.readingcloud.common.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

/**
 * 请求参数封装
 * @author: zealon
 * @since: 2020/5/6
 */
public class RequestParams extends HashMap {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestParams.class);

    /**
     * 获取整型参数值
     * @param paramName
     * @return
     */
    public Integer getIntValue(String paramName){
        Integer value = 0;
        Object object = this.get(paramName);
        if (null == object) {
            return value;
        }
        try{
            value = Integer.parseInt(this.get(paramName).toString());
        } catch (Exception ex){
            value = 0;
            LOGGER.error("获取参数{}转换整型异常！{}", paramName, ex);
        }
        return value;
    }

    /**
     * 获取字符串参数值
     * @param paramName
     * @return
     */
    public String getStringValue(String paramName){
        String value = "";
        Object object = this.get(paramName);
        if (null == object) {
            return value;
        }
        return object.toString();
    }
}
