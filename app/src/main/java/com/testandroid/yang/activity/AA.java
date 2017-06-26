package com.testandroid.yang.activity;

/**
 * Created by Administrator on 2016/6/28 0028.
 */
public class AA {


    /**
     * id : 182
     * createTime : 1498214702000
     * updateTime : 1498214702000
     * homeworkId : 74
     * teacherId : 18
     * pupilId : 1511
     * topicId : 58951
     * answerStatus : 2
     * answerUrl :
     * textAnswer : B
     * pupilInfo : {"pupilId":1511,"pupilUsername":"dsd","pupilPhonenumber":"15803894412","pupilName":"戴少东","pupilPassword":"dsd","region":" 北京市 海淀区","school":"北京大学","grade":"高二","pupilClass":"4","nickName":"dsd","signature":"人都是生气啊四十五","gender":"男","qr_code":"15112016-09-14-12-05-09","parentUsername":"parent","infoComplete":1,"pupilHeaderPic":"15112016-03-10-13-50-23","channelId":"10005","pupilRegisterTime":0,"jjwUserId":"0","relateUserId":null,"loginTime":1498442758000,"province":null,"city":null,"distirct":null,"classCode":null}
     * topicInfo : {"id":58951,"pupilId":18,"topicUrl":"182017-06-23-18-44-29","answerUrl":null,"textAnswer":"DEF","topicType":null,"knowledgePoint":"","faultAnilysis":null,"importance":null,"errorNum":0,"surmmarize":"","subjectType":"math","createTime":1498214668315,"lastModify":1498214670274,"topicUploaded":0,"answerUploaded":0,"isParentTopic":0,"topicCategory":"singleOption","topicSource":"","topicImg":null,"answerImg":null,"isDraft":0,"voiceMsgTime":"","voiceMsgUrl":"","topicTag":"","isLearn":1,"learnTime":1498214668000,"similarTopicCount":2,"similarTopicCountForClass":1,"topicContext":"例刁加12四川达州今年我市人数约为601104人对于这个近惶确的是参加中考数下列iA精确到百分位有3个有效数字B精确到百位有3个有效数字C精确到十位有4个有效数字D精确到个位有5个有效数字","similarTopicPupilsForClass":null,"similarTopicPupils":"18, 3428","similarTopicIdDetail":"58896, 58951","isabandon":0,"deleteFlag":0,"teacherId":0}
     */

    private int id;
    private long createTime;
    private long updateTime;
    private int homeworkId;
    private int teacherId;
    private int pupilId;
    private int topicId;
    private int answerStatus;
    private String answerUrl;
    private String textAnswer;
    private PupilInfoBean pupilInfo;
    private TopicInfoBean topicInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public int getPupilId() {
        return pupilId;
    }

    public void setPupilId(int pupilId) {
        this.pupilId = pupilId;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(int answerStatus) {
        this.answerStatus = answerStatus;
    }

    public String getAnswerUrl() {
        return answerUrl;
    }

    public void setAnswerUrl(String answerUrl) {
        this.answerUrl = answerUrl;
    }

    public String getTextAnswer() {
        return textAnswer;
    }

    public void setTextAnswer(String textAnswer) {
        this.textAnswer = textAnswer;
    }

    public PupilInfoBean getPupilInfo() {
        return pupilInfo;
    }

    public void setPupilInfo(PupilInfoBean pupilInfo) {
        this.pupilInfo = pupilInfo;
    }

    public TopicInfoBean getTopicInfo() {
        return topicInfo;
    }

    public void setTopicInfo(TopicInfoBean topicInfo) {
        this.topicInfo = topicInfo;
    }

    public static class PupilInfoBean {
        /**
         * pupilId : 1511
         * pupilUsername : dsd
         * pupilPhonenumber : 15803894412
         * pupilName : 戴少东
         * pupilPassword : dsd
         * region :  北京市 海淀区
         * school : 北京大学
         * grade : 高二
         * pupilClass : 4
         * nickName : dsd
         * signature : 人都是生气啊四十五
         * gender : 男
         * qr_code : 15112016-09-14-12-05-09
         * parentUsername : parent
         * infoComplete : 1
         * pupilHeaderPic : 15112016-03-10-13-50-23
         * channelId : 10005
         * pupilRegisterTime : 0
         * jjwUserId : 0
         * relateUserId : null
         * loginTime : 1498442758000
         * province : null
         * city : null
         * distirct : null
         * classCode : null
         */

        private int pupilId;
        private String pupilUsername;
        private String pupilPhonenumber;
        private String pupilName;
        private String pupilPassword;
        private String region;
        private String school;
        private String grade;
        private String pupilClass;
        private String nickName;
        private String signature;
        private String gender;
        private String qr_code;
        private String parentUsername;
        private int infoComplete;
        private String pupilHeaderPic;
        private String channelId;
        private int pupilRegisterTime;
        private String jjwUserId;
        private Object relateUserId;
        private long loginTime;
        private Object province;
        private Object city;
        private Object distirct;
        private Object classCode;

        public int getPupilId() {
            return pupilId;
        }

        public void setPupilId(int pupilId) {
            this.pupilId = pupilId;
        }

        public String getPupilUsername() {
            return pupilUsername;
        }

        public void setPupilUsername(String pupilUsername) {
            this.pupilUsername = pupilUsername;
        }

        public String getPupilPhonenumber() {
            return pupilPhonenumber;
        }

        public void setPupilPhonenumber(String pupilPhonenumber) {
            this.pupilPhonenumber = pupilPhonenumber;
        }

        public String getPupilName() {
            return pupilName;
        }

        public void setPupilName(String pupilName) {
            this.pupilName = pupilName;
        }

        public String getPupilPassword() {
            return pupilPassword;
        }

        public void setPupilPassword(String pupilPassword) {
            this.pupilPassword = pupilPassword;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getPupilClass() {
            return pupilClass;
        }

        public void setPupilClass(String pupilClass) {
            this.pupilClass = pupilClass;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }

        public String getParentUsername() {
            return parentUsername;
        }

        public void setParentUsername(String parentUsername) {
            this.parentUsername = parentUsername;
        }

        public int getInfoComplete() {
            return infoComplete;
        }

        public void setInfoComplete(int infoComplete) {
            this.infoComplete = infoComplete;
        }

        public String getPupilHeaderPic() {
            return pupilHeaderPic;
        }

        public void setPupilHeaderPic(String pupilHeaderPic) {
            this.pupilHeaderPic = pupilHeaderPic;
        }

        public String getChannelId() {
            return channelId;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        public int getPupilRegisterTime() {
            return pupilRegisterTime;
        }

        public void setPupilRegisterTime(int pupilRegisterTime) {
            this.pupilRegisterTime = pupilRegisterTime;
        }

        public String getJjwUserId() {
            return jjwUserId;
        }

        public void setJjwUserId(String jjwUserId) {
            this.jjwUserId = jjwUserId;
        }

        public Object getRelateUserId() {
            return relateUserId;
        }

        public void setRelateUserId(Object relateUserId) {
            this.relateUserId = relateUserId;
        }

        public long getLoginTime() {
            return loginTime;
        }

        public void setLoginTime(long loginTime) {
            this.loginTime = loginTime;
        }

        public Object getProvince() {
            return province;
        }

        public void setProvince(Object province) {
            this.province = province;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public Object getDistirct() {
            return distirct;
        }

        public void setDistirct(Object distirct) {
            this.distirct = distirct;
        }

        public Object getClassCode() {
            return classCode;
        }

        public void setClassCode(Object classCode) {
            this.classCode = classCode;
        }
    }

    public static class TopicInfoBean {
        /**
         * id : 58951
         * pupilId : 18
         * topicUrl : 182017-06-23-18-44-29
         * answerUrl : null
         * textAnswer : DEF
         * topicType : null
         * knowledgePoint :
         * faultAnilysis : null
         * importance : null
         * errorNum : 0
         * surmmarize :
         * subjectType : math
         * createTime : 1498214668315
         * lastModify : 1498214670274
         * topicUploaded : 0
         * answerUploaded : 0
         * isParentTopic : 0
         * topicCategory : singleOption
         * topicSource :
         * topicImg : null
         * answerImg : null
         * isDraft : 0
         * voiceMsgTime :
         * voiceMsgUrl :
         * topicTag :
         * isLearn : 1
         * learnTime : 1498214668000
         * similarTopicCount : 2
         * similarTopicCountForClass : 1
         * topicContext : 例刁加12四川达州今年我市人数约为601104人对于这个近惶确的是参加中考数下列iA精确到百分位有3个有效数字B精确到百位有3个有效数字C精确到十位有4个有效数字D精确到个位有5个有效数字
         * similarTopicPupilsForClass : null
         * similarTopicPupils : 18, 3428
         * similarTopicIdDetail : 58896, 58951
         * isabandon : 0
         * deleteFlag : 0
         * teacherId : 0
         */

        private int id;
        private int pupilId;
        private String topicUrl;
        private Object answerUrl;
        private String textAnswer;
        private Object topicType;
        private String knowledgePoint;
        private Object faultAnilysis;
        private Object importance;
        private int errorNum;
        private String surmmarize;
        private String subjectType;
        private long createTime;
        private long lastModify;
        private int topicUploaded;
        private int answerUploaded;
        private int isParentTopic;
        private String topicCategory;
        private String topicSource;
        private Object topicImg;
        private Object answerImg;
        private int isDraft;
        private String voiceMsgTime;
        private String voiceMsgUrl;
        private String topicTag;
        private int isLearn;
        private long learnTime;
        private int similarTopicCount;
        private int similarTopicCountForClass;
        private String topicContext;
        private Object similarTopicPupilsForClass;
        private String similarTopicPupils;
        private String similarTopicIdDetail;
        private int isabandon;
        private int deleteFlag;
        private int teacherId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPupilId() {
            return pupilId;
        }

        public void setPupilId(int pupilId) {
            this.pupilId = pupilId;
        }

        public String getTopicUrl() {
            return topicUrl;
        }

        public void setTopicUrl(String topicUrl) {
            this.topicUrl = topicUrl;
        }

        public Object getAnswerUrl() {
            return answerUrl;
        }

        public void setAnswerUrl(Object answerUrl) {
            this.answerUrl = answerUrl;
        }

        public String getTextAnswer() {
            return textAnswer;
        }

        public void setTextAnswer(String textAnswer) {
            this.textAnswer = textAnswer;
        }

        public Object getTopicType() {
            return topicType;
        }

        public void setTopicType(Object topicType) {
            this.topicType = topicType;
        }

        public String getKnowledgePoint() {
            return knowledgePoint;
        }

        public void setKnowledgePoint(String knowledgePoint) {
            this.knowledgePoint = knowledgePoint;
        }

        public Object getFaultAnilysis() {
            return faultAnilysis;
        }

        public void setFaultAnilysis(Object faultAnilysis) {
            this.faultAnilysis = faultAnilysis;
        }

        public Object getImportance() {
            return importance;
        }

        public void setImportance(Object importance) {
            this.importance = importance;
        }

        public int getErrorNum() {
            return errorNum;
        }

        public void setErrorNum(int errorNum) {
            this.errorNum = errorNum;
        }

        public String getSurmmarize() {
            return surmmarize;
        }

        public void setSurmmarize(String surmmarize) {
            this.surmmarize = surmmarize;
        }

        public String getSubjectType() {
            return subjectType;
        }

        public void setSubjectType(String subjectType) {
            this.subjectType = subjectType;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public long getLastModify() {
            return lastModify;
        }

        public void setLastModify(long lastModify) {
            this.lastModify = lastModify;
        }

        public int getTopicUploaded() {
            return topicUploaded;
        }

        public void setTopicUploaded(int topicUploaded) {
            this.topicUploaded = topicUploaded;
        }

        public int getAnswerUploaded() {
            return answerUploaded;
        }

        public void setAnswerUploaded(int answerUploaded) {
            this.answerUploaded = answerUploaded;
        }

        public int getIsParentTopic() {
            return isParentTopic;
        }

        public void setIsParentTopic(int isParentTopic) {
            this.isParentTopic = isParentTopic;
        }

        public String getTopicCategory() {
            return topicCategory;
        }

        public void setTopicCategory(String topicCategory) {
            this.topicCategory = topicCategory;
        }

        public String getTopicSource() {
            return topicSource;
        }

        public void setTopicSource(String topicSource) {
            this.topicSource = topicSource;
        }

        public Object getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(Object topicImg) {
            this.topicImg = topicImg;
        }

        public Object getAnswerImg() {
            return answerImg;
        }

        public void setAnswerImg(Object answerImg) {
            this.answerImg = answerImg;
        }

        public int getIsDraft() {
            return isDraft;
        }

        public void setIsDraft(int isDraft) {
            this.isDraft = isDraft;
        }

        public String getVoiceMsgTime() {
            return voiceMsgTime;
        }

        public void setVoiceMsgTime(String voiceMsgTime) {
            this.voiceMsgTime = voiceMsgTime;
        }

        public String getVoiceMsgUrl() {
            return voiceMsgUrl;
        }

        public void setVoiceMsgUrl(String voiceMsgUrl) {
            this.voiceMsgUrl = voiceMsgUrl;
        }

        public String getTopicTag() {
            return topicTag;
        }

        public void setTopicTag(String topicTag) {
            this.topicTag = topicTag;
        }

        public int getIsLearn() {
            return isLearn;
        }

        public void setIsLearn(int isLearn) {
            this.isLearn = isLearn;
        }

        public long getLearnTime() {
            return learnTime;
        }

        public void setLearnTime(long learnTime) {
            this.learnTime = learnTime;
        }

        public int getSimilarTopicCount() {
            return similarTopicCount;
        }

        public void setSimilarTopicCount(int similarTopicCount) {
            this.similarTopicCount = similarTopicCount;
        }

        public int getSimilarTopicCountForClass() {
            return similarTopicCountForClass;
        }

        public void setSimilarTopicCountForClass(int similarTopicCountForClass) {
            this.similarTopicCountForClass = similarTopicCountForClass;
        }

        public String getTopicContext() {
            return topicContext;
        }

        public void setTopicContext(String topicContext) {
            this.topicContext = topicContext;
        }

        public Object getSimilarTopicPupilsForClass() {
            return similarTopicPupilsForClass;
        }

        public void setSimilarTopicPupilsForClass(Object similarTopicPupilsForClass) {
            this.similarTopicPupilsForClass = similarTopicPupilsForClass;
        }

        public String getSimilarTopicPupils() {
            return similarTopicPupils;
        }

        public void setSimilarTopicPupils(String similarTopicPupils) {
            this.similarTopicPupils = similarTopicPupils;
        }

        public String getSimilarTopicIdDetail() {
            return similarTopicIdDetail;
        }

        public void setSimilarTopicIdDetail(String similarTopicIdDetail) {
            this.similarTopicIdDetail = similarTopicIdDetail;
        }

        public int getIsabandon() {
            return isabandon;
        }

        public void setIsabandon(int isabandon) {
            this.isabandon = isabandon;
        }

        public int getDeleteFlag() {
            return deleteFlag;
        }

        public void setDeleteFlag(int deleteFlag) {
            this.deleteFlag = deleteFlag;
        }

        public int getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(int teacherId) {
            this.teacherId = teacherId;
        }
    }
}
