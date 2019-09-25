package cn.zealon.bookstore.bookcenter.service.impl;

import cn.zealon.bookstore.bookcenter.service.BookChapterService;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 图书章节服务
 * @author: tangyl
 * @since: 2019/9/25
 */
@Service
public class BookChapterServiceImpl implements BookChapterService {

    @Override
    public String getChapterContent(String bookId, String chapterId) {
        System.out.println(System.currentTimeMillis());
        String content = "";
        String[] words = {"唐代诗人，","李白，","朝辞白帝彩云间，","遥看瀑布挂前川，","飞流直下三千尺，","疑是银河落九天。"};
        Random random = new Random();
        for (int i = 0; i < words.length; i++) {
            int index = random.nextInt(words.length);
            content += words[index];
        }
        if (bookId.equals("0")){
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(System.currentTimeMillis());
        return content;
    }
}
