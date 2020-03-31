package com.hk.crypto;

import com.hk.commons.util.Base64Utils;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 对称加密 ： AES 算法
 */
public class AESUtils {

    /**
     * algorithm ，为 AES
     */
    private static final String AES = "AES";

    static {
        // BouncyCastle是一个开源的加解密解决方案，主页在http://www.bouncycastle.org/
        // 不加会报错: Cannot find any provider supporting AES/ECB/PKCS7Padding
        Security.addProvider(new BouncyCastleProvider());
    }

    public static void main(String[] args) throws Exception {
        String input = "我是中国人,我是中国人,我是中国人,我是中国人我是中国人我是中国人我是中国人";
        String key = generatorKey(128);//数据块，如 128、192、256
        System.out.println("key:" + key);
        String encrypt = encrypt(input, key);
        System.out.println("encrypt:" + encrypt);
        System.out.println("decrypt:" + decrypt(encrypt, key));
    }

    public static String generatorKey(int length) throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(AES);
        generator.init(length);
        return Base64Utils.encodeToString(generator.generateKey().getEncoded());
    }

    /**
     * 解密
     *
     * @param input
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String input, String key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), AES);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding");
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] bytes = cipher.doFinal(Base64Utils.decodeFromString(input));
        return new String(bytes);
    }

    /**
     * 加密
     *
     * @param input
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String input, String key) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), AES);
        SecureRandom secureRandom = new SecureRandom(); //随机偏移量(IV)
        IvParameterSpec iv = new IvParameterSpec(secureRandom.generateSeed(16));
        AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance(AES);
        algorithmParameters.init(iv);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding"); // algorithm/mode/padding
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, algorithmParameters);
        byte[] bytes = cipher.doFinal(input.getBytes());
        return Base64.encodeBase64String(bytes);
    }

}
