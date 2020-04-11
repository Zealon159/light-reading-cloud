package cn.zealon.readingcloud.common.utils;

import java.util.Collection;
import java.util.UUID;

/**
 * 工具类
 * @author: zealon
 * @since: 2020/4/6
 */
public class CommonUtil {

    /**
     * 判断集合为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.size() <= 0;
    }

    /**
     * 判断集合非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * 随机生成UUID
     * @return
     */
    public static String getUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-","");
    }
}
