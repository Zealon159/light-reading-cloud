package cn.zealon.readingcloud.account.common.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.concurrent.*;

/**
 * 书架线程池配置
 * @author: zealon
 * @since: 2020/4/1
 */
@Configuration
@ConfigurationProperties(prefix = "spring.thread-pool.bookshelf")
public class BookshelfThreadPoolConfig {

    /** 核心线程数 */
    private int corePoolSize;
    /** 最大线程数 */
    private int maximumPoolSize;
    /** 线程存活时间 */
    private Long keepAliveTime;
    /** 队列容量 */
    private int queueCapacity;

    /**
     * 云书架数据消费线程池
     * @return
     */
    @Bean(value = "userBookshelfQueueThreadPool")
    public ExecutorService buildUserBookshelfQueueThreadPool(){
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
                .setNameFormat("user-bookshelf-queue-thread-%d").build();
        // 实例化线程池
        ExecutorService pool = new ThreadPoolExecutor(this.getCorePoolSize(), this.getMaximumPoolSize(), this.getKeepAliveTime(), TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(this.getQueueCapacity()),namedThreadFactory,new ThreadPoolExecutor.AbortPolicy());
        return pool;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public Long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(Long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueCapacity() {
        return queueCapacity;
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }
}
