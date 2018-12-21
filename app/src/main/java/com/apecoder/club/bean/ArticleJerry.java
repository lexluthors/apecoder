package com.apecoder.club.bean;

/**
 * Description:
 * Data：2018/12/20-16:08
 * Author: Allen
 */
public class ArticleJerry {

    private int article_id;
    private String category;
    private int child_category;
    private int comments;
    private String contributor;
    private int contributor_id;
    private String date;
    private String des;
    private String img_url;
    private String link;
    private int rank;
    private int review_status;
    private int stars;
    private String tag;
    private String title;
    private int un_stars;
    private UserBean user;
    private int views;
    private String wrap_link;

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getChild_category() {
        return child_category;
    }

    public void setChild_category(int child_category) {
        this.child_category = child_category;
    }

    public int getComments() {
        return comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public int getContributor_id() {
        return contributor_id;
    }

    public void setContributor_id(int contributor_id) {
        this.contributor_id = contributor_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getReview_status() {
        return review_status;
    }

    public void setReview_status(int review_status) {
        this.review_status = review_status;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUn_stars() {
        return un_stars;
    }

    public void setUn_stars(int un_stars) {
        this.un_stars = un_stars;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getWrap_link() {
        return wrap_link;
    }

    public void setWrap_link(String wrap_link) {
        this.wrap_link = wrap_link;
    }

    public static class UserBean {
        /**
         * admin_status : 1
         * avatar : https://wx.qlogo.cn/mmopen/vi_32/ZtYmxPFhJgn29xuLFiatw3xI55ekSGZBTwoxzj5KEtR8qvz2bclwrv3EUicFxva9ODMCBStHZ3ehmFtyPQU4Edaw/132
         * city :
         * date : 2018-09-09 14:40:13
         * gender :
         * nick_name : V V
         * open_id : oIXfM4r2dHJSSjr_udB6v_hwMoWI
         * score : 0
         * session_key : +d5wnhlJrl/VAETsaQtVyw==
         * user_id : 2
         */

        private int admin_status;
        private String avatar;
        private String city;
        private String date;
        private String gender;
        private String nick_name;
        private String open_id;
        private int score;
        private String session_key;
        private int user_id;

        public int getAdmin_status() {
            return admin_status;
        }

        public void setAdmin_status(int admin_status) {
            this.admin_status = admin_status;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getOpen_id() {
            return open_id;
        }

        public void setOpen_id(String open_id) {
            this.open_id = open_id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getSession_key() {
            return session_key;
        }

        public void setSession_key(String session_key) {
            this.session_key = session_key;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }
    }
}
