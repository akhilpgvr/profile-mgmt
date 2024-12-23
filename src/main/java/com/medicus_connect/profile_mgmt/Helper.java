package com.medicus_connect.profile_mgmt;

import org.springframework.beans.BeanUtils;

public class Helper {

    public static <S, T> T copyProperties(S source, T destination) {
        BeanUtils.copyProperties(source, destination);
        return destination;
    }
}
