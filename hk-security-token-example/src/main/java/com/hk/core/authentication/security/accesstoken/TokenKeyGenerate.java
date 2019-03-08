package com.hk.core.authentication.security.accesstoken;

import com.hk.commons.util.IDGenerator;
import org.springframework.util.Base64Utils;

import java.nio.charset.StandardCharsets;

/**
 * token 生成器
 *
 * @author huangkai
 * @date 2019/3/8 9:29
 */
@FunctionalInterface
public interface TokenKeyGenerate {

    String generate();

    TokenKeyGenerate base64Token = () -> Base64Utils.encodeToString(IDGenerator.STRING_UUID.generate().getBytes(StandardCharsets.UTF_8));
}
