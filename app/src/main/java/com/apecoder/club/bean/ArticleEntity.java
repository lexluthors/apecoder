package com.apecoder.club.bean;


import android.os.Parcel;
import android.os.Parcelable;

public class ArticleEntity implements Parcelable {
    //文章id
    private Long id;

    private String date;

    private String updateDate;

    private Long contributorId;

    //描述
    private String des;

    //封面图
    private String coverImage;

    private String title;
    private String link;

    private String tag;//文章标签
    /**
     * 文章分类（Android、iOS、Java等）0安卓，1 ios, 2 前端，3 java, 4 PHP, 5 python, 6 大数据, 7 人工智能
     */
    private Integer category;
    //文章二级分类(开源库0、资讯1、资料2等)
    private Integer sencondCategory;
    //审核状态(审核中0、通过1、未通过2)
    private Integer auditSatus;

//    @TableField(exist = false)
    private UserInfoBean user;

    //收藏量
    private Integer collect;
    //点赞量，（之前用的like字段，不能使用mysql关键字当做实体类字段，会导致sql语法错误）
    private Integer praise;
    //阅读量
    private Integer pageViews;
    //评论数
    private Integer comments;

    public Long getContributorId() {
        return contributorId;
    }

    public void setContributorId(Long contributorId) {
        this.contributorId = contributorId;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public UserInfoBean getUser() {
        return user;
    }

    public void setUser(UserInfoBean user) {
        this.user = user;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getSencondCategory() {
        return sencondCategory;
    }

    public void setSencondCategory(Integer sencondCategory) {
        this.sencondCategory = sencondCategory;
    }

    public Integer getAuditSatus() {
        return auditSatus;
    }

    public void setAuditSatus(Integer auditSatus) {
        this.auditSatus = auditSatus;
    }

    public Integer getCollect() {
        return collect;
    }

    public void setCollect(Integer collect) {
        this.collect = collect;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getPageViews() {
        return pageViews;
    }

    public void setPageViews(Integer pageViews) {
        this.pageViews = pageViews;
    }

    public Integer getComments() {
        return comments;
    }

    public void setComments(Integer comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public ArticleEntity() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.date);
        dest.writeString(this.updateDate);
        dest.writeValue(this.contributorId);
        dest.writeString(this.des);
        dest.writeString(this.coverImage);
        dest.writeString(this.title);
        dest.writeString(this.link);
        dest.writeString(this.tag);
        dest.writeValue(this.category);
        dest.writeValue(this.sencondCategory);
        dest.writeValue(this.auditSatus);
        dest.writeParcelable(this.user, flags);
        dest.writeValue(this.collect);
        dest.writeValue(this.praise);
        dest.writeValue(this.pageViews);
        dest.writeValue(this.comments);
    }

    protected ArticleEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.date = in.readString();
        this.updateDate = in.readString();
        this.contributorId = (Long) in.readValue(Long.class.getClassLoader());
        this.des = in.readString();
        this.coverImage = in.readString();
        this.title = in.readString();
        this.link = in.readString();
        this.tag = in.readString();
        this.category = (Integer) in.readValue(Integer.class.getClassLoader());
        this.sencondCategory = (Integer) in.readValue(Integer.class.getClassLoader());
        this.auditSatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.user = in.readParcelable(UserInfoBean.class.getClassLoader());
        this.collect = (Integer) in.readValue(Integer.class.getClassLoader());
        this.praise = (Integer) in.readValue(Integer.class.getClassLoader());
        this.pageViews = (Integer) in.readValue(Integer.class.getClassLoader());
        this.comments = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<ArticleEntity> CREATOR = new Creator<ArticleEntity>() {
        @Override
        public ArticleEntity createFromParcel(Parcel source) {
            return new ArticleEntity(source);
        }

        @Override
        public ArticleEntity[] newArray(int size) {
            return new ArticleEntity[size];
        }
    };
}
