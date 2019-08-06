package hr.java.vjezbe.entitet;

/**
 * Su�elje koje nalje�uju sveu�ili�ne obrazovne ustanove
 * @author Filip �ulig
 *
 */
public interface Diplomski extends Visokoskolska{
	/**
	 * Odre�uje dobitnika rektorove nagrade
	 * @return Student Dobitnik rektorove nagrade
	 */
	public Student odrediStudentaZaRektorovuNagradu();
}
