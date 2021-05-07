package com.timezone;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public class TimeZoneConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Instant nowUTc = Instant.now();
		System.out.println("UTC Time : "+nowUTc);
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		ZoneId zd = ZoneId.systemDefault();
		System.out.println("Local Machine TIme :"+zd.getId());
//		zd.of("Asia/Shanghai");
		ZoneId xh = ZoneId.of("Asia/Shanghai");
		LocalDateTime cdt = LocalDateTime.now(xh);
		System.out.println("China Time :"+cdt.toString());
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("HH:mm:ss");
		String input = "18:00";
		Set<String> totalZone = ZoneId.getAvailableZoneIds();
		JSONObject time_zone = new JSONObject();
		time_zone.put("input_time", input);
		JSONArray convertedtime = new JSONArray();
		for (String zone : totalZone) {
			JSONObject jt = new JSONObject();
//			System.out.println(zone);
			ZoneId tmp = ZoneId.of(zone);
			String time = convertTimeToZone(tmp, input);
//			convertedtime.put(new JSONObject("{"+tmp.toString()+":"+time+"}"));
			jt.put(tmp.toString(),String.valueOf(time));
			convertedtime.put(jt);
//			map_of_time.put("TimeZone->"+zone+":Time->"+input, time);
			
		}
		time_zone.put("converted", convertedtime);
		System.out.println(time_zone.toString(4));
//		map_of_time.forEach((key,value)->System.out.println(key+"\t:\t"+value));
//		ZoneId chinaZone = ZoneId.of("Asia/Shanghai");
//		ZoneId usaZone = ZoneId.of("America/New_York");
//		ZoneId indiaZone = ZoneId.of("Asia/Calcutta");
//		convertTimeToZone(indiaZone, input);
	}

	public static String convertTimeToZone(ZoneId targetId, String timetochange) {
		String otpTime = "";
		try {
			DateTimeFormatter fd = DateTimeFormatter.ofPattern("HH:mm");
			String[] dt = timetochange.split(":");
			int hour = Integer.valueOf(dt[0]);
			int minute = Integer.valueOf(dt[1]);
			// construct localdatetime from above time for given timezone
			LocalDateTime givenTime = LocalDateTime.of(YearMonth.now().getYear(),YearMonth.now().getMonth(),LocalDate.now().getDayOfMonth(),hour,minute);
//			System.out.println("Given time :"+givenTime);
			Instant instantTime= givenTime.atZone(targetId).toInstant();
//			System.out.println("Instant time :"+instantTime);
			ZoneId serverZone = ZoneId.systemDefault();		// Server TimeZone
			ZonedDateTime fromZoneDateTime = ZonedDateTime.of(givenTime, targetId).withZoneSameLocal(targetId);	// From given timezone 
			ZonedDateTime toZoneDateTime = fromZoneDateTime.withZoneSameInstant(serverZone);	// to Server timezone
//			System.out.println(toZoneDateTime.format(fd));
			otpTime = toZoneDateTime.format(fd);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return otpTime;
	}
//	 public static String convertBetweenTwoTimeZone(Instant date, String fromTimeZone, String toTimeZone) {
//	        ZoneId fromTimeZoneId = ZoneId.of(fromTimeZone);
//	        ZoneId toTimeZoneId = ZoneId.of(toTimeZone);
//
//	        ZonedDateTime fromZonedDateTime =
//	                ZonedDateTime.ofInstant(date, ZoneId.systemDefault()).withZoneSameLocal(fromTimeZoneId);
//
//	        ZonedDateTime toZonedDateTime = fromZonedDateTime
//	                .withZoneSameInstant(toTimeZoneId)
//	                .withZoneSameLocal(ZoneId.systemDefault());
//	                
//
//	        System.out.println(toZonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
////	        return Date.from(toZonedDateTime.toInstant());
//	        return null;
//	    }
	
}
