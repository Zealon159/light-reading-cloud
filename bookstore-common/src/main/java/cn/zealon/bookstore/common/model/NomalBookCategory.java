package cn.zealon.bookstore.common.model;


import java.io.Serializable;
import java.util.Date;

/**
 * 图书分类
 */
public class NomalBookCategory implements Serializable {

    private Integer id;
    private String name;
    private String authorCategory;
    private Date createDate;
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAuthorCategory() {
        return authorCategory;
    }

    public void setAuthorCategory(String authorCategory) {
        this.authorCategory = authorCategory;
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
}
