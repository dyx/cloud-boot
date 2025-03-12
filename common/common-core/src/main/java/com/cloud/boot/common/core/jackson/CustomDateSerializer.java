package com.cloud.boot.common.core.jackson;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author lhd
 */
public class CustomDateSerializer extends DateSerializer {

    @Override
    public void serialize(Date value, JsonGenerator g, SerializerProvider provider) throws IOException {

        if (this._asTimestamp(provider)) {
            g.writeNumber(this._timestamp(value));
            return;
        }

        SimpleDateFormat df = new SimpleDateFormat(CommonConstant.NORMAL_DATETIME_FORMATTER);
        df.setTimeZone(TimeZone.getTimeZone(CommonConstant.TIME_ZONE_ASIA_SHANGHAI));
        g.writeString(df.format(value));
    }
}
