package com.timezone;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeZoneConversion {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Instant nowUTc = Instant.now();
		System.out.println(nowUTc);
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);
		ZoneId zd = ZoneId.systemDefault();
		System.out.println(zd.getId());
//		zd.of("Asia/Shanghai");
		ZoneId xh = ZoneId.of("Asia/Shanghai");
//		Set<String> zoneset = ZoneId.getAvailableZoneIds();
//		for(String s:zoneset) {
//			System.out.println(s);
//		}
		LocalDateTime cdt = LocalDateTime.now(xh);
		System.out.println(cdt.toString());
		DateTimeFormatter fd = DateTimeFormatter.ofPattern("HH:mm:ss");
		String input = "17:24";
//		LocalDateTime.
//		System.out.println(LocalDateTime.parse(input));
//		ZonedDateTime  zdt =  LocalDateTime.parse(input, fd).atZone(xh);
//		System.out.println(zdt);
//		System.out.println(cdt.format(fd));
//		System.out.println(ldt.atZone(xh));
//		System.out.println();
//		String d = convertBetweenTwoTimeZone(new Date(), "Asia/Calcutta", "Asia/Shanghai");
//		System.out.println(LocalDateTime.);
		String s[] = input.split(":");
		int hour = Integer.valueOf(s[0]);
		int min = Integer.valueOf(s[1]);
		LocalDateTime dd = LocalDateTime.of(YearMonth.now().getYear(),YearMonth.now().getMonth(),LocalDate.now().getDayOfMonth(),hour,min);
		System.out.println(dd.format(fd));
		Instant im = dd.atZone(ZoneId.of("Asia/Calcutta")).toInstant();
		System.out.println(im);
		convertBetweenTwoTimeZone(im, "Asia/Shanghai", "America/New_York");
	}

//	public static String convertTimeToZone(ZoneId targetId, String timetochange) {
//		try {
////			
//			String s[] = timetochange.split(":");
//			int hour = Integer.valueOf(s[0]);
//			int min = Integer.valueOf(s[1]);
//			LocalDateTime dd = LocalDateTime.of(YearMonth.now().getYear(),YearMonth.now().getMonth(),LocalDate.now().getDayOfMonth(),hour,min);
//			ZonedDateTime dt =  dd.atZone(targetId).toInstant();
//			System.out.println(dt.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//			
//		}catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return null;
//	}
	 public static String convertBetweenTwoTimeZone(Instant date, String fromTimeZone, String toTimeZone) {
	        ZoneId fromTimeZoneId = ZoneId.of(fromTimeZone);
	        ZoneId toTimeZoneId = ZoneId.of(toTimeZone);

	        ZonedDateTime fromZonedDateTime =
	                ZonedDateTime.ofInstant(date, ZoneId.systemDefault()).withZoneSameLocal(fromTimeZoneId);

	        ZonedDateTime toZonedDateTime = fromZonedDateTime
	                .withZoneSameInstant(toTimeZoneId)
	                .withZoneSameLocal(ZoneId.systemDefault())
	                ;

	        System.out.println(toZonedDateTime.format(DateTimeFormatter.ofPattern("HH:mm")));
//	        return Date.from(toZonedDateTime.toInstant());
	        return null;
	    }
	
}
