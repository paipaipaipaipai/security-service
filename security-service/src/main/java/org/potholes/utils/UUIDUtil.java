package org.potholes.utils;

import java.util.UUID;

public class UUIDUtil {

    // 生成UUID
    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
