package cn.zealon.readingcloud.common.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Base64工具类
 * @author: zealon
 * @since: 2020/4/11
 */
public class Base64Util {

    private static final Charset DEFAULT_CHARSET;

    public Base64Util() {
    }

    public static byte[] encode(byte[] src) {
        return src.length == 0 ? src : Base64.encodeBase64(src);
    }

    public static byte[] decode(byte[] src) {
        return src.length == 0 ? src : Base64.decodeBase64(src);
    }

    public static byte[] encodeUrlSafe(byte[] src) {
        return src.length == 0 ? src : Base64.encodeBase64(src);
    }

    public static byte[] decodeUrlSafe(byte[] src) {
        return src.length == 0 ? src : Base64.decodeBase64(src);
    }

    public static String encodeToString(byte[] src) {
        return src.length == 0 ? "" : new String(encode(src), DEFAULT_CHARSET);
    }

    public static byte[] decodeFromString(String src) {
        return src.isEmpty() ? new byte[0] : decode(src.getBytes(DEFAULT_CHARSET));
    }

    public static String encodeToUrlSafeString(byte[] src) {
        return new String(encodeUrlSafe(src), DEFAULT_CHARSET);
    }

    public static byte[] decodeFromUrlSafeString(String src) {
        return decodeUrlSafe(src.getBytes(DEFAULT_CHARSET));
    }

    static {
        DEFAULT_CHARSET = StandardCharsets.UTF_8;
    }
}
