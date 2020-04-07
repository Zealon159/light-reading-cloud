package cn.zealon.readingcloud.common.cache;

/**
 * Redis 缓存有效时间(单位：秒)
 */
public class RedisExpire {

    // 1分钟
    public static final long MINUTE = 60;
    // 2分钟 60*2
    public static final long MINUTE_TWO = 120;
    // 3分钟 60*3
    public static final long MINUTE_THR = 180;
    // 5分钟 60*5
    public static final long MINUTE_FIV = 300;
    // 10分钟 60*10
    public static final long MINUTE_TEN = 600;
    // 20分钟
    public static final long MINUTE_TWENTY = 1200;
    // 30分钟 60*30
    public static final long MINUTE_THIRTY = 1800;
    // 1小时 60*60*1
    public static final long HOUR = 3600;
    // 2小时  60*60*2
    public static final long HOUR_TWO = 7200;
    // 4小时
    public static final long HOUR_FOUR = 14400;
    // 1天 60*60*24
    public static final long DAY = 86400;
    // 2天 60*60*24*2
    public static final long DAY_TWO = 172800;
    // 1周 60*60*24*7
    public static final long WEEK = 604800;
    // 1月 60*60*24*30
    public static final long MONTH = 2592000;
    // 1年 60*60*24*365
    public static final long YEAR = 31536000;

}
