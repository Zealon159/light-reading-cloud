package cn.zealon.bookstore.common.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 图书
 */
public class NomalBook implements Serializable {

    private Long id;
    private String bookId;
    private String bookName;
    private String newBookName;
    private Integer status;
    private String imgUrl;
    private String fullImgUrl;
    private String authorPenname;
    private String introduction;
    private Integer wordCount;
    private String keyWord;
    private Integer haveVolume;
    private Float bookPrice;
    private String bookStatue;
    private String publish;
    private Date copyRightTime;
    private Integer pindaoId;
    private Integer categoryId;
    private Integer categorySec;
    private Integer categoryThr;
    private Integer lastUpdateChapterId;
    private String lastUpdateChapterName;
    private Date lastUpdateChapterDate;
    private Date createDate;
    private Date updateDate;
    private Date sysCreateDate;
    private Date sysUpdateDate;
    /** 短简介 */
    private String shortIntroduction;
    /** 推荐语 */
    private String recommended;
    /** 长简介 */
    private String longIntroduction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getNewBookName() {
        return newBookName;
    }

    public void setNewBookName(String newBookName) {
        this.newBookName = newBookName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getFullImgUrl() {
        return fullImgUrl;
    }

    public void setFullImgUrl(String fullImgUrl) {
        this.fullImgUrl = fullImgUrl;
    }

    public String getAuthorPenname() {
        return authorPenname;
    }

    public void setAuthorPenname(String authorPenname) {
        this.authorPenname = authorPenname;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public Integer getHaveVolume() {
        return haveVolume;
    }

    public void setHaveVolume(Integer haveVolume) {
        this.haveVolume = haveVolume;
    }

    public Float getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Float bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookStatue() {
        return bookStatue;
    }

    public void setBookStatue(String bookStatue) {
        this.bookStatue = bookStatue;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Date getCopyRightTime() {
        return copyRightTime;
    }

    public void setCopyRightTime(Date copyRightTime) {
        this.copyRightTime = copyRightTime;
    }

    public Integer getPindaoId() {
        return pindaoId;
    }

    public void setPindaoId(Integer pindaoId) {
        this.pindaoId = pindaoId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategorySec() {
        return categorySec;
    }

    public void setCategorySec(Integer categorySec) {
        this.categorySec = categorySec;
    }

    public Integer getCategoryThr() {
        return categoryThr;
    }

    public void setCategoryThr(Integer categoryThr) {
        this.categoryThr = categoryThr;
    }

    public Integer getLastUpdateChapterId() {
        return lastUpdateChapterId;
    }

    public void setLastUpdateChapterId(Integer lastUpdateChapterId) {
        this.lastUpdateChapterId = lastUpdateChapterId;
    }

    public String getLastUpdateChapterName() {
        return lastUpdateChapterName;
    }

    public void setLastUpdateChapterName(String lastUpdateChapterName) {
        this.lastUpdateChapterName = lastUpdateChapterName;
    }

    public Date getLastUpdateChapterDate() {
        return lastUpdateChapterDate;
    }

    public void setLastUpdateChapterDate(Date lastUpdateChapterDate) {
        this.lastUpdateChapterDate = lastUpdateChapterDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getSysCreateDate() {
        return sysCreateDate;
    }

    public void setSysCreateDate(Date sysCreateDate) {
        this.sysCreateDate = sysCreateDate;
    }

    public Date getSysUpdateDate() {
        return sysUpdateDate;
    }

    public void setSysUpdateDate(Date sysUpdateDate) {
        this.sysUpdateDate = sysUpdateDate;
    }

    public String getShortIntroduction() {
        return shortIntroduction;
    }

    public void setShortIntroduction(String shortIntroduction) {
        this.shortIntroduction = shortIntroduction;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    public String getLongIntroduction() {
        return longIntroduction;
    }

    public void setLongIntroduction(String longIntroduction) {
        this.longIntroduction = longIntroduction;
    }
}
