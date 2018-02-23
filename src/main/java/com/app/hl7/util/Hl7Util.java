package com.app.hl7.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Hl7Util {
    public static void insertDbMessageInQueue(String message){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = sdf.format(new Date());
        List<String> params = new ArrayList<>();
        params.add("1");
        params.add(UUID.randomUUID().toString());
        params.add(message);
        params.add("0");
        params.add(nowDate);
        params.add(UUID.randomUUID().toString());
        try {
            JdbcUtils.update("insert into hl7_in_queue(hl7_source, hl7_source_key, hl7_data, message_state, date_created, uuid) values(?, ?, ?, ?, ?, ?);", params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
