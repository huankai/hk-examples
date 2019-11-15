package com.hk;

import com.hk.commons.util.JsonUtils;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;

/**
 * 获取文件信息，如视频时长，单位: 毫秒
 *
 * @author kevin
 * @date 2019-11-15 11:26
 */
public class JaveTest {

    public static void main(String[] args) throws EncoderException {
        File file = new File("C:/Users/kevin/Downloads/4f3c8d444c7d43028e71c01722659ee2.mp4");
        MultimediaObject multimediaObject = new MultimediaObject(file);
        MultimediaInfo info = multimediaObject.getInfo();
        System.out.println(JsonUtils.serialize(info, true));
    }
}
