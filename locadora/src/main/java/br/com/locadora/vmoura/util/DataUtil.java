package br.com.locadora.vmoura.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DataUtil {
	
	public static LocalDate converterDateParaLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static int diasEntreDatas(Date data1, Date data2) {
		return (int) ChronoUnit.DAYS.between(
				converterDateParaLocalDate(data1), 
				converterDateParaLocalDate(data2));
	}
	
	public static Date dataAtualMeiaNoite() {
		return Date.from(LocalDate.now(ZoneId.of("GMT-03:00"))
				.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

}
