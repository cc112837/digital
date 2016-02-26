package com.cc.digital.entity;

import java.util.List;

/**
 * Created by PENG on 2015/11/12 0012.
 */
public class CommunityItem1 {

    private ReturnDataEntity returnData;

    public void setReturnData(ReturnDataEntity returnData) {
        this.returnData = returnData;
    }

    public ReturnDataEntity getReturnData() {
        return returnData;
    }

    public static class ReturnDataEntity {
        /**
         * tid : 235235
         * fid : 2
         * typeid : 8
         * sortid : 0
         * readperm : 0
         * price : 0
         * author : 小啤
         * authorid : 11549
         * subject : 我要上首页，让文章更受欢迎！ -- 首页申请通道
         * dateline : 2015-2-3
         * lastpost : 3 小时前</span>
         * lastposter : YangXM
         * views : 37167
         * replies : 1593
         * displayorder : 1
         * highlight :  style="font-weight: bold;color: #2897C5"
         * digest : 0
         * special : 0
         * attachment : 1
         * closed : 0
         * favtimes : 0
         * cover : 0
         * dbdateline : 1422943814
         * dblastpost : 1447300628
         */

        private List<ThreadlistEntity> threadlist;

        public void setThreadlist(List<ThreadlistEntity> threadlist) {
            this.threadlist = threadlist;
        }

        public List<ThreadlistEntity> getThreadlist() {
            return threadlist;
        }

        public static class ThreadlistEntity {
            private String tid;
            private String fid;
            private String typeid;
            private String sortid;
            private String readperm;
            private String price;
            private String author;
            private String authorid;
            private String subject;
            private String dateline;
            private String lastpost;
            private String lastposter;
            private String views;
            private String replies;
            private String displayorder;
            private String highlight;
            private String digest;
            private String special;
            private String attachment;
            private String closed;
            private String favtimes;
            private String cover;
            private String dbdateline;
            private String dblastpost;

            public void setTid(String tid) {
                this.tid = tid;
            }

            public void setFid(String fid) {
                this.fid = fid;
            }

            public void setTypeid(String typeid) {
                this.typeid = typeid;
            }

            public void setSortid(String sortid) {
                this.sortid = sortid;
            }

            public void setReadperm(String readperm) {
                this.readperm = readperm;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public void setAuthorid(String authorid) {
                this.authorid = authorid;
            }

            public void setSubject(String subject) {
                this.subject = subject;
            }

            public void setDateline(String dateline) {
                this.dateline = dateline;
            }

            public void setLastpost(String lastpost) {
                this.lastpost = lastpost;
            }

            public void setLastposter(String lastposter) {
                this.lastposter = lastposter;
            }

            public void setViews(String views) {
                this.views = views;
            }

            public void setReplies(String replies) {
                this.replies = replies;
            }

            public void setDisplayorder(String displayorder) {
                this.displayorder = displayorder;
            }

            public void setHighlight(String highlight) {
                this.highlight = highlight;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public void setSpecial(String special) {
                this.special = special;
            }

            public void setAttachment(String attachment) {
                this.attachment = attachment;
            }

            public void setClosed(String closed) {
                this.closed = closed;
            }

            public void setFavtimes(String favtimes) {
                this.favtimes = favtimes;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public void setDbdateline(String dbdateline) {
                this.dbdateline = dbdateline;
            }

            public void setDblastpost(String dblastpost) {
                this.dblastpost = dblastpost;
            }

            public String getTid() {
                return tid;
            }

            public String getFid() {
                return fid;
            }

            public String getTypeid() {
                return typeid;
            }

            public String getSortid() {
                return sortid;
            }

            public String getReadperm() {
                return readperm;
            }

            public String getPrice() {
                return price;
            }

            public String getAuthor() {
                return author;
            }

            public String getAuthorid() {
                return authorid;
            }

            public String getSubject() {
                return subject;
            }

            public String getDateline() {
                return dateline;
            }

            public String getLastpost() {
                return lastpost;
            }

            public String getLastposter() {
                return lastposter;
            }

            public String getViews() {
                return views;
            }

            public String getReplies() {
                return replies;
            }

            public String getDisplayorder() {
                return displayorder;
            }

            public String getHighlight() {
                return highlight;
            }

            public String getDigest() {
                return digest;
            }

            public String getSpecial() {
                return special;
            }

            public String getAttachment() {
                return attachment;
            }

            public String getClosed() {
                return closed;
            }

            public String getFavtimes() {
                return favtimes;
            }

            public String getCover() {
                return cover;
            }

            public String getDbdateline() {
                return dbdateline;
            }

            public String getDblastpost() {
                return dblastpost;
            }
        }
    }
}
