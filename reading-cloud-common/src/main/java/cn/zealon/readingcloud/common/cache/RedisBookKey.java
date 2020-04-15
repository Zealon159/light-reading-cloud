package cn.zealon.readingcloud.common.cache;

/**
 * 图书资源中心缓存Key
 * @author: zealon
 * @since: 2020/4/6
 */
public class RedisBookKey {

    /** 图书信息缓存 */
    public static final String getBookKey(String bookId){
        return String.format("book-center:detail-%s",bookId);
    }

    /** 资源中心图书章节缓存 */
    public static final String getBookChapterKey(String bookId){
        return String.format("book-center:chapter-%s",bookId);
    }

    /** 资源中心图书章节列表缓存 */
    public static final String getBookChapterListKey(String bookId){
        return String.format("book-center:chapter-list-%s",bookId);
    }

    /** 图书资源中心feign-client缓存 */
    public static final class BookCenter {
        /** 资源中心图书缓存 */
        public static final String getFeignClientBookKey(String bookId){
            return String.format("book-center:feign-client-book-%s",bookId);
        }

    }
}
