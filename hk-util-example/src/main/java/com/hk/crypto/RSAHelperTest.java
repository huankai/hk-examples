package com.hk.crypto;

import com.hk.commons.crypot.RSAHelper;
import com.hk.commons.util.Base64Utils;

public class RSAHelperTest {

    public static void main(String[] args) {
//        RSAHelper.makeKeyPair();
        String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ+Llbg09DhRnHpgBYzE6GsnLhIqw76gQG+Cgfe+kZsMPxac/y0y4fO22ipuuBgiT/hCBOKtG+IAqooUw8ocqhFLVR4iFmOUT4QUoFXUXa84TIHpznDpqiiDk5wBQGnnX8aHC1sOb/ncRFmIhJqvxJIXAdcVY2J9Hpe5F2WOClgXAgMBAAECgYAoaBu1GYQ4t0KgI62EJ2KNsNCUPUxx/PgUxcLQ0ezijcl0mrJt6hMbvFWopX3ubtI+F8pQ9nK3XF/UtMmZaIURkdlcrjYONjA9M5jRI0hsj9xvMgmtxfc54G1lcdGa9giJ1y5W+pO3E5cG7UB7bjBna4pYi33mPvCvkZr1WZoCgQJBAMvHkVGUJcktevpNLS9nIGNW+rtl/ejrMCPWgMSm7ZvsuVqTKUlOdk+AqRCJKIBaqH9LwX39nImiZQSUpnzL46ECQQDIbiO5m95pGAzuLq+pGndiOTC1qLTl1Pd0M5eQBlU3j45dmywsLiqg4AejZKep6PpIIR9XcdaoQ/ygLKMsRqC3AkBEOqquTNMl+8NcQTDq2TWf7USAMFMGhJCWv0BYF6gg9+7Lo9BECjBUyDhTIAleiHw4Ou4rOBoMf0IheYAQ8tdhAkBAACN6PlJr3pkqaVAAQIpEnBx4tXFmPnXx7hOEkvEdN8LvEwCs7uT/z+nAfACp2dIpvCHk613vBx6hCv/jwH3LAkEAgEyMykfhgZHnCv0NtDxziWt95LoTFYfq3EBqsh+18tZsK1CIcOua2VxtODJbkiQnc0NZ9MJf58N1+3Ua5OFp3g==",
                publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfi5W4NPQ4UZx6YAWMxOhrJy4SKsO+oEBvgoH3vpGbDD8WnP8tMuHzttoqbrgYIk/4QgTirRviAKqKFMPKHKoRS1UeIhZjlE+EFKBV1F2vOEyB6c5w6aoog5OcAUBp51/GhwtbDm/53ERZiISar8SSFwHXFWNifR6XuRdljgpYFwIDAQAB",
                content = "hahaha";
        RSAHelper rsaUtils = new RSAHelper(privateKey, publicKey, null, RSAHelper.SHA256_SIGN_ALGORITHMS);
        String sign = Base64Utils.encodeToString(rsaUtils.sign(content));//获取签名
        boolean verify = rsaUtils.verify(content, sign);//验证签名是否正确
        System.out.println("签名:" + sign);
        System.out.println("签名是否合法:" + verify);
        byte[] encrypt = rsaUtils.encryptByPrivateKey(content);// 私钥对 content 加密
        String result = Base64Utils.encodeToString(encrypt);
        System.out.println("加密后的结果:" + result);
        System.out.println("解密后的结果:" + new String(rsaUtils.decryptByPublicKey(result)));// 公钥解密
    }
}
