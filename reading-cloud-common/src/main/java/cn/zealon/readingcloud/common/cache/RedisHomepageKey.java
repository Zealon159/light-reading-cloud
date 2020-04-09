package cn.zealon.readingcloud.common.cache;

/**
 * 精品页缓存Key
 * @author: zealon
 * @since: 2020/4/6
 */
public class RedisHomepageKey {

    /**
     * 主页缓存Key
     * @param type 类型1.主页 2.男生 3.女生
     * @return
     */
    public static final String getHomepageKey(Integer type){
        return String.format("home-page:index-%s", type);
    }

    /** 主页Banner缓存Key */
    public static final String getBannerVoKey(Integer bannerId){
        return String.format("home-page:banner-vo-%s", bannerId);
    }

    /** 主页书单DB缓存Key */
    public static final String getBooklistDbKey(Integer booklistId){
        return String.format("home-page:booklist-db-%s", booklistId);
    }

    /** 主页书单缓存Key */
    public static final String getBooklistVoKey(Integer booklistId){
        return String.format("home-page:booklist-vo-%s", booklistId);
    }

    /** 主页书单随机缓存Key */
    public static final String getBooklistRandomVoKey(Integer booklistId){
        return String.format("home-page:booklist-random-vo-%s", booklistId);
    }

    /** 主页书单图书分页更多 */
    public static final String getBooklistItemPagingKey(Integer booklistId){
        return String.format("home-page:booklist-item-paging-%s", booklistId);
    }

    public static final class BookCenter{
        /** 资源中心图书缓存 */
        public static final String getBookKey(String bookId){
            return String.format("home-page:book-%s",bookId);
        }

    }
}