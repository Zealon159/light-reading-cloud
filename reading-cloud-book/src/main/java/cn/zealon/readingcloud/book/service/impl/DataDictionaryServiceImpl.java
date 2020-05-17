package cn.zealon.readingcloud.book.service.impl;

import cn.zealon.readingcloud.book.dao.DataDictionaryMapper;
import cn.zealon.readingcloud.book.service.DataDictionaryService;
import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.book.DataDictionary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 字典服务
 * @author: zealon
 * @since: 2020/5/16
 */
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private DataDictionaryMapper mapper;

    @Override
    public Map<String, DataDictionary> getDictionarys(String dicType, String field) {
        String key = "dictionary:"+dicType;
        HashMap<String, DataDictionary> map = new HashMap<>();
        List<DataDictionary> dictionaries = this.redisService.getHashListVal(key, field, DataDictionary.class);
        if (dictionaries.size() == 0) {
            dictionaries = this.mapper.findPageWithResult(dicType);
            if (dictionaries.size() > 0) {
                map = this.getMap(dictionaries);
                this.redisService.setHashValsExpire(key, map, RedisExpire.DAY);
            }
        }else{
            map = this.getMap(dictionaries);
        }
        return map;
    }

    private HashMap<String, DataDictionary> getMap(List<DataDictionary> dictionaries){
        HashMap<String, DataDictionary> map = new HashMap<>();
        for (int i = 0; i < dictionaries.size(); i++) {
            DataDictionary dictionary = dictionaries.get(i);
            map.put(dictionary.getCode().toString(), dictionary);
        }
        return map;
    }
}
