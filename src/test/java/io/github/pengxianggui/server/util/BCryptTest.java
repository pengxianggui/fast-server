package io.github.pengxianggui.server.util;

import cn.hutool.crypto.digest.BCrypt;

/**
 * @author pengxg
 * @date 2026/2/22 16:24
 */
public class BCryptTest {

    public static void main(String[] args) {
        String hashedPassword = BCrypt.hashpw("123456", BCrypt.gensalt());
        System.out.println(hashedPassword);
        System.out.println(BCrypt.checkpw("123456", hashedPassword));
        System.out.println(BCrypt.checkpw("123456", "$2a$10$qlGjF1aazBNI8s/SIkY7i.z5elQ9YHX088.kvGTPSlicq1Eogxp/G"));
        System.out.println(BCrypt.checkpw("123456", "$2a$10$uLwJnIpKbWAnfMHq82pj..p7yrCBqSf2haWfJO01q2G8QDBpqJYA."));
    }
}
