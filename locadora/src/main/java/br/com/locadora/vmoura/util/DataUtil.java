package br.com.locadora.vmoura.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	
	public static Date formataData(String data) {
        if (data == null || data.equals("")) {
        	return null;
        }
        Date date = null;
		try {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = formatter.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return date;
    }

}
