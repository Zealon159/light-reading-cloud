package cn.zealon.bookstore.homepage.vo;

import java.util.Date;

/**
 * 图书详情VO
 * @author: tangyl
 * @since: 2019/7/4
 */
public class BookVO {

    private String bookId;
    private String bookName;
    private String introduction;
    private String imgUrl;
    private String authorName;
    private Integer categoryId;
    private String categoryName;
    private String keyword;
    private Date lastUpdateChapterDate;
    private String lastUpdateChapterName;
    private Long lastUpdateChapterId;
    private Integer wordCount;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getLastUpdateChapterDate() {
        return lastUpdateChapterDate;
    }

    public void setLastUpdateChapterDate(Date lastUpdateChapterDate) {
        this.lastUpdateChapterDate = lastUpdateChapterDate;
    }

    public String getLastUpdateChapterName() {
        return lastUpdateChapterName;
    }

    public void setLastUpdateChapterName(String lastUpdateChapterName) {
        this.lastUpdateChapterName = lastUpdateChapterName;
    }

    public Long getLastUpdateChapterId() {
        return lastUpdateChapterId;
    }

    public void setLastUpdateChapterId(Long lastUpdateChapterId) {
        this.lastUpdateChapterId = lastUpdateChapterId;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
