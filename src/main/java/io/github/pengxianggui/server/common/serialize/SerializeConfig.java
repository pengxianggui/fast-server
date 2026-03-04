package io.github.pengxianggui.server.common.serialize;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * 序列化配置
 *
 * @author pengxg
 * @date 2026/2/25 16:49
 */
@Configuration
public class SerializeConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {

            // 统一处理 LocalDateTime 的序列化与反序列化
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN);
            builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer(formatter));
            builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer(formatter));

            // 处理日期类型 java.util.Date
            // 这行代码会同时影响序列化（输出）和反序列化（输入）
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);

            // 关键：设置时区，否则 Date 类型可能会比实际时间慢 8 小时
            builder.timeZone(TimeZone.getDefault());

            // (可选) 忽略 null 字段，减少网络带宽
            // builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        };
    }

}
