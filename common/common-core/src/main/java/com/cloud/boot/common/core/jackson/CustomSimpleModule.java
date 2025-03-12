package com.cloud.boot.common.core.jackson;

import com.cloud.boot.common.core.constant.CommonConstant;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lhd
 */
public class CustomSimpleModule extends SimpleModule {

	public CustomSimpleModule() {
		super(PackageVersion.VERSION);

		this.addSerializer(Long.class, ToStringSerializer.instance);
		this.addSerializer(Date.class, new CustomDateSerializer());
		this.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(CommonConstant.NORMAL_DATE_FORMATTER)));
		this.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(CommonConstant.NORMAL_TIME_FORMATTER)));
		this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CommonConstant.NORMAL_DATETIME_FORMATTER)));

		this.addDeserializer(Long.class, new ParseLongDeserializer());
		this.addDeserializer(Date.class, new CustomDateDeserializer());
		this.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(CommonConstant.NORMAL_DATE_FORMATTER)));
		this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(CommonConstant.NORMAL_TIME_FORMATTER)));
		this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(CommonConstant.NORMAL_DATETIME_FORMATTER)));
	}

}