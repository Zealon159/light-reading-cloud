package cn.zealon.readingcloud.common.utils;

import java.util.Collection;

/**
 * 工具类
 * @author: zealon
 * @since: 2020/4/6
 */
public class Utils {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.size() <= 0;
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }
}
