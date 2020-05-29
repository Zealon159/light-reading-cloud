package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.homepage.dao.HotSearchWordMapper;
import cn.zealon.readingcloud.homepage.domain.RequestQuery;
import cn.zealon.readingcloud.homepage.domain.SearchBookItem;
import cn.zealon.readingcloud.homepage.domain.SearchBookResult;
import cn.zealon.readingcloud.homepage.service.SearchService;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 图书查询服务
 * @author: zealon
 * @since: 2020/5/29
 */
@Service
public class SearchServiceImpl implements SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);

    /** ES Jest 客户端对象 */
    @Autowired
    private JestClient jestClient;

    /** 索引别名 */
    @Value("${es.aliasName}")
    private String aliasName;

    /** 类型 */
    @Value("${es.indexType}")
    private String indexType;

    @Autowired
    private HotSearchWordMapper hotSearchWordMapper;

    @Override
    public Result getHotSearchWordList(Integer size) {
        List<String> hotSearchWordList = this.hotSearchWordMapper.getHotSearchWordList(size);
        return ResultUtil.success(hotSearchWordList);
    }

    @Override
    public Result getSearchResultBooks(String keyword, Integer page, Integer limit){
        // 查询条件
        Map query = new HashMap();
        // 多字段匹配
        Map multiMatch = new HashMap();
        multiMatch.put("query", keyword);
        multiMatch.put("type", "most_fields");
        String[] fields = new String[]{"bookName^2","bookName.pinyin","author"};
        multiMatch.put("fields", fields);
        query.put("multi_match",multiMatch);

        int from = (page - 1) * limit;
        int size = from + limit;
        RequestQuery requestQuery = new RequestQuery(from, size, query);
        SearchBookResult searchBookResult = this.getSearchResult(requestQuery.toString());
        return ResultUtil.success(searchBookResult);
    }

    /**
     * ES 执行查询结果
     * @param query
     * @return
     */
    private SearchBookResult getSearchResult(String query){
        SearchBookResult result = new SearchBookResult();
        // 封装查询对象
        Search search = new Search.Builder(query)
                .addIndex(aliasName)
                .addType(indexType).build();

        // 执行查询
        try {
            SearchResult searchResult = this.jestClient.execute(search);
            List<SearchBookItem> bookList;
            if (searchResult.isSucceeded()) {
                // 查询成功，处理结果项
                List<SearchResult.Hit<SearchBookItem, Void>> hitList = searchResult.getHits(SearchBookItem.class);
                bookList = new ArrayList<>(hitList.size());
                for (SearchResult.Hit<SearchBookItem, Void> hit : hitList) {
                    bookList.add(hit.source);
                }
            } else {
                bookList = new ArrayList<>();
            }

            // 赋值
            result.setTotal(searchResult.getTotal());
            result.setBookList(bookList);
        } catch (IOException e) {
            LOGGER.error("查询图书异常，查询语句:{}", query, e);
        }
        return result;
    }
}
