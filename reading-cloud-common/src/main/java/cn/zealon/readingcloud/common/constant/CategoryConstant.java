package cn.zealon.readingcloud.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 分类
 * @author: zealon
 * @since: 2020/5/17
 */
public class CategoryConstant {

    public static Map<Integer,String> categorys;

    static {
        categorys = new HashMap<>();
        categorys.put(1100, "东方玄幻");
        categorys.put(1200, "都市小说");
        categorys.put(1300, "西方玄幻");
        categorys.put(1400, "武侠仙侠");
        categorys.put(1500, "历史穿越");
        categorys.put(1600, "科幻未来");
        categorys.put(1700, "网游竞技");
        categorys.put(1800, "悬疑惊悚");
        categorys.put(2100, "古代言情");
        categorys.put(2200, "幻想言情");
        categorys.put(2300, "都市言情");
        categorys.put(2400, "总裁豪门");
        categorys.put(2500, "青春校园");
        categorys.put(2600, "唯美同人");
        categorys.put(2700, "历史穿越");
        categorys.put(2800, "唯美同人");
        categorys.put(3100, "文学小说");
        categorys.put(3200, "经典名著");
    }
}
