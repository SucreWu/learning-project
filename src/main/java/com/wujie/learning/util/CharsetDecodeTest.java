package com.wujie.learning.util;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @Description: 使用Charset进行编码/解码
 * @Auther: wujie
 * @Date: 2018/12/11 14:09
 * @version: V1.0
 */
public class CharsetDecodeTest {


    private static byte[] encode(String str, String charset) {
        Charset cset = Charset.forName(charset);
        ByteBuffer byteBuffer = cset.encode(str);
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        return bytes;
    }

    private static String decode(byte[] bytes, String charset) {
        Charset cset = Charset.forName(charset);
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        CharBuffer charBuffer = cset.decode(buffer);
        return charBuffer.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "吴";
        byte[] bytes = str.getBytes("UTF-8");
        ByteBuffer bb = ByteBuffer.allocate(3);
        bb.put(bytes, 0, 3);
        bb.flip();
        CharBuffer cb = UTF_8.decode(bb);
        char c = cb.charAt(0);
        System.out.println(c);
        // ------------------------------------------------
        System.out.println(decode(
                encode(str,"UTF-8"), "UTF-8")
        );
    }
}
