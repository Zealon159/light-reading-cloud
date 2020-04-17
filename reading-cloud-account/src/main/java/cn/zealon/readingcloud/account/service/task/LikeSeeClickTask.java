package cn.zealon.readingcloud.account.service.task;

import cn.zealon.readingcloud.common.cache.RedisAccountKey;
import cn.zealon.readingcloud.common.cache.RedisService;

/**
 * 喜欢看点击任务
 * @author: zealon
 * @since: 2020/4/17
 */
public class LikeSeeClickTask implements Runnable {

    private RedisService redisService;
    private String bookId;
    private Integer value;

    @Override
    public void run() {
        // 喜欢数若存在，进行相应的递增或递减
        if (this.redisService.hashHasKey(RedisAccountKey.ACCOUNT_CENTER_BOOK_LIKES_COUNT, this.bookId)) {
            int val = 1;
            if (value <= 0) {
                val = -1;
            }
            this.redisService.hashIncrement(RedisAccountKey.ACCOUNT_CENTER_BOOK_LIKES_COUNT, this.bookId, val);
        }
    }

    public LikeSeeClickTask(){}

    public LikeSeeClickTask(RedisService redisService, String bookId, Integer value) {
        this.redisService = redisService;
        this.bookId = bookId;
        this.value = value;
    }
}
