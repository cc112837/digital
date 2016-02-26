package com.cc.digital.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by PENG on 2015/11/11 0011.
 */
public class CommunityHome {

    /**
     * forumdata : {"pengyu":{"forums":[{"name":"尾巴主版","posts":"1657513"},{"name":"甩甩尾巴","posts":"1039331"},{"name":"尾巴认证","posts":"11699"},{"name":"尾巴良品","posts":"2591"},{"name":"纠结尾巴","posts":"52940"},{"name":"站务交流","posts":"22075"}]}}
     */

    private ReturnDataEntity returnData;

    public void setReturnData(ReturnDataEntity returnData) {
        this.returnData = returnData;
    }

    public ReturnDataEntity getReturnData() {
        return returnData;
    }

    public static class ReturnDataEntity {
        /**
         * pengyu : {"forums":[{"name":"尾巴主版","posts":"1657513"},{"name":"甩甩尾巴","posts":"1039331"},{"name":"尾巴认证","posts":"11699"},{"name":"尾巴良品","posts":"2591"},{"name":"纠结尾巴","posts":"52940"},{"name":"站务交流","posts":"22075"}]}
         */

        private ForumdataEntity forumdata;

        public void setForumdata(ForumdataEntity forumdata) {
            this.forumdata = forumdata;
        }

        public ForumdataEntity getForumdata() {
            return forumdata;
        }

        public static class ForumdataEntity {
            @SerializedName("1")
            private PengyuEntity pengyu;

            public void setPengyu(PengyuEntity pengyu) {
                this.pengyu = pengyu;
            }

            public PengyuEntity getPengyu() {
                return pengyu;
            }

            public static class PengyuEntity {
                /**
                 * name : 尾巴主版
                 * posts : 1657513
                 */

                private List<ForumsEntity> forums;

                public void setForums(List<ForumsEntity> forums) {
                    this.forums = forums;
                }

                public List<ForumsEntity> getForums() {
                    return forums;
                }

                public static class ForumsEntity {
                    private String name;
                    private String posts;

                    public void setName(String name) {
                        this.name = name;
                    }

                    public void setPosts(String posts) {
                        this.posts = posts;
                    }

                    public String getName() {
                        return name;
                    }

                    public String getPosts() {
                        return posts;
                    }
                }
            }
        }
    }


}
