package com.hk.crypto;

import com.hk.commons.crypot.AESUtils;

import javax.crypto.spec.IvParameterSpec;

/**
 * 对称加密 ： AES 算法
 */
public class AESUtilsTest {

    public static void main(String[] args) throws Exception {
        String input = "我是中国人,我是中国人,我是中国人,我是中国人我是中国人我是中国人我是中国人";
        String key = AESUtils.generatorKey(128);
        System.out.println("key:" + key);
        String transformation = "AES/CBC/PKCS7Padding";
        IvParameterSpec iv = AESUtils.randomIv();
        String encrypt = AESUtils.encrypt(input, key, iv, transformation);
        System.out.println("encrypt:" + encrypt);
        System.out.println("decrypt:" + AESUtils.decrypt(encrypt, key, iv, transformation));
    }

}
