package com.testandroid.yang.common;

import java.util.List;

/**
 * Created by yangjiajia on 2017/4/26 0026.
 */

public class TagInfo {

    /**
     * result : [{"typeName":"错因分析","alias":"faultAnilysis","id":12,"tags":[{"id":709,"tagId":10688,"tagTypeId":12,"subjectType":"ALL","topicTag":"知识错误","userName":null},{"id":710,"tagId":10689,"tagTypeId":12,"subjectType":"ALL","topicTag":"思路错误","userName":null},{"id":712,"tagId":10691,"tagTypeId":12,"subjectType":"ALL","topicTag":"审题错误","userName":null},{"id":713,"tagId":10692,"tagTypeId":12,"subjectType":"ALL","topicTag":"运算错误","userName":null}]},{"typeName":"自定义标签","alias":"topicTag","id":11,"tags":[]},{"typeName":"学科题型","alias":"topicType","id":10,"tags":[{"id":702,"tagId":10681,"tagTypeId":10,"subjectType":"history","topicTag":"选择题","userName":null},{"id":703,"tagId":10682,"tagTypeId":10,"subjectType":"history","topicTag":"材料阅读题","userName":null},{"id":704,"tagId":10683,"tagTypeId":10,"subjectType":"history","topicTag":"简答题","userName":null}]},{"typeName":"错题来源","alias":"topicSource","id":9,"tags":[{"id":666,"tagId":10646,"tagTypeId":9,"subjectType":"ALL","topicTag":"平时作业","userName":null},{"id":670,"tagId":10650,"tagTypeId":9,"subjectType":"ALL","topicTag":"期末考试","userName":null}]}]
     * flag : 0
     */

    private int flag;
    private List<ResultBean> result;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * typeName : 错因分析
         * alias : faultAnilysis
         * id : 12
         * tags : [{"id":709,"tagId":10688,"tagTypeId":12,"subjectType":"ALL","topicTag":"知识错误","userName":null},{"id":710,"tagId":10689,"tagTypeId":12,"subjectType":"ALL","topicTag":"思路错误","userName":null},{"id":712,"tagId":10691,"tagTypeId":12,"subjectType":"ALL","topicTag":"审题错误","userName":null},{"id":713,"tagId":10692,"tagTypeId":12,"subjectType":"ALL","topicTag":"运算错误","userName":null}]
         */

        private String typeName;
        private String alias;
        private int id;
        private List<TagsBean> tags;

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * id : 709
             * tagId : 10688
             * tagTypeId : 12
             * subjectType : ALL
             * topicTag : 知识错误
             * userName : null
             */

            private int id;
            private int tagId;
            private int tagTypeId;
            private String subjectType;
            private String topicTag;
            private Object userName;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getTagId() {
                return tagId;
            }

            public void setTagId(int tagId) {
                this.tagId = tagId;
            }

            public int getTagTypeId() {
                return tagTypeId;
            }

            public void setTagTypeId(int tagTypeId) {
                this.tagTypeId = tagTypeId;
            }

            public String getSubjectType() {
                return subjectType;
            }

            public void setSubjectType(String subjectType) {
                this.subjectType = subjectType;
            }

            public String getTopicTag() {
                return topicTag;
            }

            public void setTopicTag(String topicTag) {
                this.topicTag = topicTag;
            }

            public Object getUserName() {
                return userName;
            }

            public void setUserName(Object userName) {
                this.userName = userName;
            }
        }
    }
}
