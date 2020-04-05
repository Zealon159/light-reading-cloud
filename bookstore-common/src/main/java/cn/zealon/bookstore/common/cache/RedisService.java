package cn.zealon.bookstore.common.cache;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * cache 操作类
 * @author zealon
 */
@Component
public class RedisService {

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);

    /** 默认缓存时间(10分钟) */
    public static final Long DEFAULT_CACHE_TIME = RedisConstant.Expire.MINUTE_TEN;

    /** 操作字符串 */
    private ValueOperations<String, String> valueOperations;
    /** 操作hash */
    private HashOperations hashOperations;
    /** 操作list */
    private ListOperations listOperations;
    /** 操作set */
    private SetOperations setOperations;
    /** 操作有序set */
    private ZSetOperations zSetOperations;
    /** Redis模板 */
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 构造方法初始化操作类
     */
    @PostConstruct
    private void inInit() {
        valueOperations = stringRedisTemplate.opsForValue();
        hashOperations = stringRedisTemplate.opsForHash();
        listOperations = stringRedisTemplate.opsForList();
        setOperations = stringRedisTemplate.opsForSet();
        zSetOperations = stringRedisTemplate.opsForZSet();
    }

    /************************************** 公共方法 */
    /**
     * 删除缓存
     * @param key 缓存key
     * @return Boolean 成功失败
     */
    public Boolean removeCache(String key) {
        Long begTime = System.currentTimeMillis();
        Boolean result = this.removeCacheComm(key);
        Long endTime = System.currentTimeMillis();
        LOGGER.info("删除缓存耗时 key={} time={}", key, endTime - begTime);
        return result;
    }

    /**
     * 根据Key删除缓存
     * @param key 缓存key
     * @return Boolean 成功失败
     */
    public Boolean removeCacheComm(String key) {
        Boolean hasExist = hasKey(key);
        if (hasExist) {
            return stringRedisTemplate.delete(key);
        } else {
            return true;
        }
    }

    /**
     * 删除多个key
     *
     * @param keys 缓存key
     * @return Long 删除key的数量
     */
    public Long removeList(Set<String> keys) {
        if (StringUtils.isEmpty(keys)) {
            return null;
        }
        return stringRedisTemplate.delete(keys);
    }

    /**
     * 获取匹配的key
     *
     * @param keyRegular key正则表达式
     * @return Set<String> 匹配的可以列表
     */
    public Set<String> getKeyRegualr(String keyRegular) {
        if (StringUtils.isEmpty(keyRegular)) {
            return null;
        }
        return stringRedisTemplate.keys(keyRegular);
    }

    /**
     * 设置过期时间
     *
     * @param key  缓存key
     * @param time 缓存时间
     */
    public void setExpire(final String key, Long time) {
        stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 获取缓存时间剩余时间
     *
     * @param key 缓存key
     * @return long 缓存剩余时间
     */
    public Long getExpire(final String key) {
        return stringRedisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 缓存key
     * @return boolean 成功失败
     */
    public Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }
    /************************************** 公共方法 End */

    /************************************** String处理（实体转化为json字符串） */
    /**
     * 从缓存获取值
     *
     * @param key 缓存key
     * @param c   参数类型
     * @param <T> 参数对象
     * @return T 返回对象
     */
    public <T> T getCache(String key, Class<T> c) {
        String cache = getCache(key);
        return JSONObject.parseObject(cache, c);
    }

    public <T> List getCacheForList(String key, Class<T> c) {
        String cache = getCache(key);
        return JSONObject.parseArray(cache, c);
    }

    /**
     * 从缓存中获取
     *
     * @param key     缓存key
     * @param typeOff 对象类型
     * @return Object 返回实体
     */
    public Object getCache(String key, Type typeOff) {
        return JSONObject.parseObject(getCache(key), typeOff);
    }

    /**
     * 获取缓存信息
     *
     * @param key 缓存key
     * @return String
     */
    public String getCache(String key) {
        return valueOperations.get(key);
    }

    /**
     * 设置缓存值没有设置过期时间
     *
     * @param key   缓存key
     * @param value 缓存值
     */
    public void setCache(String key, String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        valueOperations.set(key, value);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key   缓存KEY
     * @param value 缓存值
     */
    public void setExpireCacheDefault(final String key, final String value) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
            return;
        }
        valueOperations.set(key, value, DEFAULT_CACHE_TIME, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key   缓存KEY
     * @param value 缓存值
     * @param time  缓存时间
     */
    public void setExpireCache(final String key, final String value, Long time) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value) || StringUtils.isEmpty(time) || time <= 0) {
            return;
        }
        valueOperations.set(key, value, time, TimeUnit.SECONDS);
    }
    /************************************** String处理（实体转化为json字符串） End */

    /************************************** Object处理 */
    /**
     * 添加缓存
     *
     * @param key 缓存KEY
     * @param obj 缓存对象
     */
    public void setCache(String key, Object obj) {
        setCache(key, JSONObject.toJSONString(obj));
    }

    /**
     * 设置缓存并添加默认过期时间
     *
     * @param key 缓存KEY
     * @param obj 添加的缓存对象
     */
    public void setExpireCacheDefault(final String key, final Object obj) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(obj)) {
            return;
        }
        setExpireCache(key, obj, DEFAULT_CACHE_TIME);
    }

    /**
     * 设置缓存并添加缓存过期时间
     *
     * @param key  缓存KEY
     * @param obj  缓存对象
     * @param time 缓存时间
     */
    public void setExpireCache(final String key, final Object obj, Long time) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(obj) || StringUtils.isEmpty(time)) {
            return;
        }
        this.setExpireCache(key, JSONObject.toJSONString(obj), time);
    }
    /************************************** Object处理 End */

    /************************************** List处理  */
    /**
     * 添加缓存
     *
     * @param key  缓存KEY
     * @param list 缓存列表值
     */
    public void rightPushAll(String key, List list) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(list)) {
            return;
        }
        List<String> redisList = new ArrayList<>();
        if (list != null && !list.isEmpty() && list.size() > 0) {
            for (Object obj : list) {
                if (obj != null) {
                    redisList.add(JSONObject.toJSONString(obj));
                }
            }
        }
        listOperations.rightPushAll(key, redisList);
    }

    /**
     * 添加缓存并设置过期时间 有默认值
     *
     * @param key  缓存KEY
     * @param list 缓存列表值
     */
    public void rightPushAllExpireDefault(String key, List list) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(list)) {
            return;
        }
        this.rightPushAll(key, list);
        setExpire(key, DEFAULT_CACHE_TIME);
    }


    /**
     * 添加缓存并设置过期时间
     *
     * @param key  缓存KEY
     * @param list 缓存列表
     * @param time 缓存时间
     */
    public void rightPushAllExpire(String key, List list, Long time) {
        this.rightPushAll(key, list);
        setExpire(key, time);
    }

    /**
     * 添加缓存
     *
     * @param key  缓存KEY
     * @param list 缓存列表
     */
    public void leftPushAll(String key, List list) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(list)) {
            return;
        }
        List<String> redisList = new ArrayList<>();
        if (list != null && !list.isEmpty() && list.size() > 0) {
            for (Object obj : list) {
                if (obj != null) {
                    redisList.add(JSONObject.toJSONString(obj));
                }
            }
        }
        listOperations.leftPushAll(key, redisList);
    }

    /**
     * 添加缓存并设置过期时间 有默认值
     *
     * @param key  缓存KEY
     * @param list 缓存列表
     */
    public void leftPushAllExpireDefault(String key, List list) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(list)) {
            return;
        }
        this.leftPushAll(key, list);
        setExpire(key, DEFAULT_CACHE_TIME);
    }


    /**
     * 添加缓存并设置过期时间
     *
     * @param key  缓存KEY
     * @param list 缓存列表
     * @param time 缓存时间
     */
    public void leftPushAllExpire(String key, List list, Long time) {
        this.leftPushAll(key, list);
        setExpire(key, time);
    }

    /**
     * 从缓存获取数据
     *
     * @param key 缓存KEY
     * @param c   值类型
     * @param <T> 值对象
     * @return List<T> 返回对象列表
     */
    public <T> List<T> getRedisList(String key, Class<T> c) {
        List<String> listStr = listOperations.range(key, 0, -1);
        if (listStr.isEmpty()) {
            return null;
        }
        List<T> backList = new ArrayList<>();
        for (String str : listStr) {
            if (!StringUtils.isEmpty(str)) {
                backList.add(JSONObject.parseObject(str, c));
            }
        }
        return backList;
    }
    /************************************** List处理 End */

    /************************************** Hash处理 */
    public <T> T getHashVal(String key, String field, Class<T> c){
        Object val = this.hashOperations.get(key, field);
        if (val == null) {
            return null;
        }
        return JSONObject.parseObject(val.toString(), c);
    }

    public void setHashValExpire(String key, String field, Object val, Long time){
        this.hashOperations.put(key, field, JSONObject.toJSONString(val));
        this.setExpire(key, time);
    }

    /************************************** Hash处理 End */


    /************************************** zSet处理 End */
    /**
     * 批量保存到有序集合
     * @param key
     * @param list
     */
    public void zSetAddExpireDefault(String key, List list) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(list)) {
            return;
        }

        Double index = 1d;
        Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
        if (!list.isEmpty() && list.size() > 0) {
            for (Object obj : list) {
                if (obj != null) {
                    String value = JSONObject.toJSONString(obj);
                    ZSetOperations.TypedTuple<String> typedTuple = new DefaultTypedTuple<>(value, index);
                    tuples.add(typedTuple);
                    index++;
                }
            }
        }
        zSetOperations.add(key,tuples);
        setExpire(key, DEFAULT_CACHE_TIME);

    }

    /**
     * 范围查询，返回集合
     * @param key
     * @param start
     * @param end
     * @param c
     * @return
     */
    public <T> List<T> zSetRange(String key,long start,long end,Class<T> c){
        List<T> backList = new ArrayList<>();

        // 缓存获取数据
        Set<String> data = zSetOperations.range(key, start, end);
        if (data == null || data.isEmpty()) {
            return null;
        }

        // 转换为List
        Iterator<String> iterator = data.iterator();
        while (iterator.hasNext()){
            String jsonStr = iterator.next();
            if (!StringUtils.isEmpty(jsonStr)) {
                backList.add(JSONObject.parseObject(jsonStr, c));
            }
        }
        return backList;
    }

    /**
     * 获取 zSet 长度
     * @param key
     * @return
     */
    public Long zSetGetSize(String key){
        return zSetOperations.size(key);
    }

    /**
     * 分页获取 zSet 数据集合
     * @param key
     * @param c
     * @param pageNo
     * @param pageSize
     * @return
     */
    public <T> List<T> zSetRangeByPage(String key, Class<T> c, Integer pageNo, Integer pageSize) {
        long start = 0;
        if (pageNo > 1) {
            start = (pageNo - 1l) * pageSize;
        }
        long end = pageNo * pageSize - 1;
        return zSetRange(key,start,end,c);
    }
    /************************************** zSet处理 End */
}
