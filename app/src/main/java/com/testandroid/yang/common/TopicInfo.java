package com.testandroid.yang.common;

import java.io.Serializable;

/**
 * Created by yangjiajia on 2017/10/12.
 * {@link com.testandroid.yang.provider.TopicContract}
 */

public class TopicInfo implements Serializable {
    private String name;
    private int topicId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "TopicInfo{" +
                "name='" + name + '\'' +
                ", topicId=" + topicId +
                '}';
    }
}
