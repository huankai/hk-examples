package com.hk.rocketmq.simple.four;

import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 消息分隔，
 * 大于 4M 的消息进行分隔
 *
 * @author huangkai
 * @date 2019-11-21 10:36
 */
public class ListSplitter implements Iterator<List<Message>> {

    private final int SIZE_LIMIT = 1024 * 1024 * 4;

    private final List<Message> messages;

    private int currIndex;

    public ListSplitter(List<Message> messages) {
        this.messages = messages;
    }


    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int nexIndex = currIndex;
        int totalSize = 0;
        for (; nexIndex < messages.size(); nexIndex++) {
            Message message = messages.get(nexIndex);
            int tmpSize = message.getTopic().length() + message.getBody().length;
            Map<String, String> properties = message.getProperties();
            for (Map.Entry<String, String> entry : properties.entrySet()) {
                tmpSize += entry.getKey().length() + entry.getValue().length();

            }
            tmpSize += tmpSize + 20; //增加日志开销 20字节
            if (tmpSize > SIZE_LIMIT) {
                //单个消息超过最大限制
                // 忽略，否则会阻塞分裂的过程
                if (nexIndex - currIndex == 0) {
                    nexIndex++; // 假如下一个子列表没有元素，则添加这个列表后退出循环，否则只是退出循环
                }
                break;
            }
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = messages.subList(currIndex, nexIndex);
        currIndex = nexIndex;
        return subList;


    }
}
