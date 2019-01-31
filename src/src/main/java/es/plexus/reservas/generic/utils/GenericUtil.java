package es.plexus.reservas.generic.utils;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que contiene las utilidades que se utilizaran
 * 
 * @author emilio.panero, jose.regorodriguez
 *
 */
public class GenericUtil {

	/**
	 * Suma los días que se quieran a una fecha dada
	 * 
	 * @param day
	 * @param number
	 * @return Date
	 */
	public static Date addDays(Date day, int number) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(day);
		cal.add(Calendar.DATE, 1);

		return cal.getTime();
	}

	/**
	 * Metodo para saber si una fecha es mayor que otra
	 * 
	 * @param horaInicio
	 * @param horaFin
	 * @return boolean
	 */
	public static boolean startTimeGreaterThanEndTime(Date startTime, Date endTime) {

		return startTime.after(endTime);
	}

	public static Date startOfDay() {

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);

		return cal.getTime();
	}

	/**
	 * Metodo para saber si una reserva esta dentro del horario laboral.
	 * 
	 * @param reservaHoraDesde
	 * @param reservaHoraHasta
	 * @return boolean
	 */
	public static boolean reservaIsInHorarioLaboral(Date reservaHoraDesde, Date reservaHoraHasta) {

		Boolean isInHorarioLaboral = false;

		LocalTime horaInicio = LocalTime.of(8, 0);
		LocalTime horaFin = LocalTime.of(19, 0);

		LocalTime horaDesde = transformarHora(reservaHoraDesde);
		LocalTime horaHasta = transformarHora(reservaHoraHasta);

		if ((!horaInicio.isAfter(horaDesde) && !horaFin.isBefore(horaDesde))
				&& (!horaInicio.isAfter(horaHasta) && !horaFin.isBefore(horaHasta))) {
			isInHorarioLaboral = true;
		}

		return isInHorarioLaboral;
	}

	/**
	 * Metodo para saber si la reserva tiene un tiempo minimo de 15min.
	 * 
	 * @param reservaHoraDesde
	 * @param reservaHoraHasta
	 * @return boolean
	 */
	public static boolean reservaHasMinTime(Date reservaHoraDesde, Date reservaHoraHasta) {
		Boolean hasMimimoTiempo = false;

		LocalTime horaDesde = transformarHora(reservaHoraDesde);
		LocalTime horaHasta = transformarHora(reservaHoraHasta);

		int diferenciaMinutos = (int) ChronoUnit.MINUTES.between(horaDesde, horaHasta);

		if (diferenciaMinutos < 15) {
			hasMimimoTiempo = true;
		}

		return hasMimimoTiempo;
	}

	/**
	 * Metodo para pasar las horas de las reservas de Date a LocalTime
	 * 
	 * @param hora
	 * @return LocalTime
	 */
	private static LocalTime transformarHora(Date hora) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(hora);

		return LocalTime.of(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
	}

}
