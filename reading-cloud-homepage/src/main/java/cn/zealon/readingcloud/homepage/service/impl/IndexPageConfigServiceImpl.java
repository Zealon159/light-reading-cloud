package cn.zealon.readingcloud.homepage.service.impl;

import cn.zealon.readingcloud.common.cache.RedisExpire;
import cn.zealon.readingcloud.common.cache.RedisHomepageKey;
import cn.zealon.readingcloud.common.cache.RedisService;
import cn.zealon.readingcloud.common.pojo.index.IndexPageConfig;
import cn.zealon.readingcloud.common.result.Result;
import cn.zealon.readingcloud.common.result.ResultUtil;
import cn.zealon.readingcloud.common.utils.Utils;
import cn.zealon.readingcloud.homepage.dao.IndexPageConfigMapper;
import cn.zealon.readingcloud.homepage.service.IndexBannerService;
import cn.zealon.readingcloud.homepage.service.IndexBooklistService;
import cn.zealon.readingcloud.homepage.service.IndexPageConfigService;
import cn.zealon.readingcloud.homepage.vo.IndexPageVO;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 精品页服务
 * @author: zealon
 * @since: 2020/4/6
 */
@Service
public class IndexPageConfigServiceImpl implements IndexPageConfigService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexPageConfigServiceImpl.class);

    @Autowired
    private IndexPageConfigMapper indexPageConfigMapper;

    @Autowired
    private IndexBannerService indexBannerService;

    @Autowired
    private IndexBooklistService indexBooklistService;

    @Autowired
    private RedisService redisService;

    @Override
    public Result getIndexPageByType(Integer type, Integer page, Integer limit) {
        String key = RedisHomepageKey.getHomepageKey(type);
        // 精品页VO列表
        List<IndexPageVO> pageVOS = this.redisService.getHashListVal(key, page.toString(), IndexPageVO.class);
        if (pageVOS != null) {
            return ResultUtil.success(pageVOS);
        }

        // 获得精品页配置
        List<IndexPageConfig> pageConfigs = this.getIndexPageWithPaging(type, page, limit);
        if (Utils.isEmpty(pageConfigs)) {
            LOGGER.warn("当前请求页没有配置项！");
            return ResultUtil.success(new ArrayList<>()).buildMessage("当前请求页没有配置项！");
        }

        pageVOS = new ArrayList<>(pageConfigs.size());
        for (int i = 0; i < pageConfigs.size(); i++) {
            IndexPageConfig pageConfig = pageConfigs.get(i);
            IndexPageVO vo = new IndexPageVO();
            BeanUtils.copyProperties(pageConfig, vo);

            // 模块是否有效
            boolean okFlag = true;
            switch (pageConfig.getItemType()){
                case 1:
                    // 书单
                    vo.setBooklist(this.indexBooklistService.getIndexBooklistVO(pageConfig.getItemId(), null));
                    if (vo.getBooklist() == null) {
                        okFlag = false;
                    }
                    break;
                case 2:
                    // Banner
                    vo.setBanner(this.indexBannerService.getBannerVO(pageConfig.getItemId()));
                    if (vo.getBanner() == null) {
                        okFlag = false;
                    }
                    break;
                default:
                    break;
            }

            // 对应的模块值不为空，才进行添加到VO中
            if (okFlag) {
                pageVOS.add(vo);
            }
        }

        if (pageVOS.size() > 0) {
            // 缓存精品页
            this.redisService.setHashValExpire(key, page.toString(), pageVOS, RedisExpire.DAY);
        }
        return ResultUtil.success(pageVOS);
    }

    /**
     * 分页获取精品页配置列表
     * @param type
     * @param page
     * @param limit
     * @return
     */
    private List<IndexPageConfig> getIndexPageWithPaging(Integer type, Integer page, Integer limit){
        if (page <= 0) {
            page = 1;
        }
        PageHelper.startPage(page, limit);
        List<IndexPageConfig> pageWithResult = this.indexPageConfigMapper.findPageWithResult(type);
        return pageWithResult;
    }
}
