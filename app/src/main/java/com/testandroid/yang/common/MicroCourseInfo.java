package com.testandroid.yang.common;

import java.io.Serializable;

/**
 * Created by yangjiajia on 2017/4/19 0019.
 */

public class MicroCourseInfo implements Serializable{

    /**
     * numberOfVotes : 1
     * create_time : 1491360598000
     * headerPicture : 3987
     * description : 描述信息显示器
     * pid : 1476
     * cover_id : 5470
     * title : 回答电脑
     * video_ids : 46740FA9206A5FCE9C33DC5901307461
     * questionIdForAnswer : 602
     * totalScore : 1
     * video_paths : /storage/emulated/0/CuoTiBao/MiaoJiang/ctb_1485166625097.mp4
     * knowledge_point : 平面向量的概念
     * score : 1
     * user_type : teacher
     * answer :
     * course_code : 5211491360597247
     * createUsername : 王晓嫚
     * stage : 高中
     * user_id : 521
     * school : null
     * price : 0
     * subject_name : 数学
     * id : 1728
     * question_ids : 5469
     */

    private int numberOfVotes;
    private long create_time;
    private String headerPicture;
    private String description;
    private int pid;
    private String cover_id;
    private String title;
    private String video_ids;
    private int questionIdForAnswer;
    private int totalScore;
    private String video_paths;
    private String knowledge_point;
    private int score;
    private String user_type;
    private String answer;
    private String course_code;
    private String createUsername;
    private String stage;
    private int user_id;
//    private Object school;
    private int price;
    private String subject_name;
    private int id;
    private String question_ids;

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public String getHeaderPicture() {
        return headerPicture;
    }

    public void setHeaderPicture(String headerPicture) {
        this.headerPicture = headerPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCover_id() {
        return cover_id;
    }

    public void setCover_id(String cover_id) {
        this.cover_id = cover_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideo_ids() {
        return video_ids;
    }

    public void setVideo_ids(String video_ids) {
        this.video_ids = video_ids;
    }

    public int getQuestionIdForAnswer() {
        return questionIdForAnswer;
    }

    public void setQuestionIdForAnswer(int questionIdForAnswer) {
        this.questionIdForAnswer = questionIdForAnswer;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getVideo_paths() {
        return video_paths;
    }

    public void setVideo_paths(String video_paths) {
        this.video_paths = video_paths;
    }

    public String getKnowledge_point() {
        return knowledge_point;
    }

    public void setKnowledge_point(String knowledge_point) {
        this.knowledge_point = knowledge_point;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getCreateUsername() {
        return createUsername;
    }

    public void setCreateUsername(String createUsername) {
        this.createUsername = createUsername;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

//    public Object getSchool() {
//        return school;
//    }
//
//    public void setSchool(Object school) {
//        this.school = school;
//    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_ids() {
        return question_ids;
    }

    public void setQuestion_ids(String question_ids) {
        this.question_ids = question_ids;
    }
}
