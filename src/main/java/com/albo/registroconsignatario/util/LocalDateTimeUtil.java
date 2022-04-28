package com.albo.registroconsignatario.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public final class LocalDateTimeUtil {
    
    public static Long localDateTimeToEpochMilliseconds(LocalDateTime fechaTime) {
		Instant instant = fechaTime.atZone(ZoneId.systemDefault()).toInstant();
		return instant.toEpochMilli();
	}
    
}
