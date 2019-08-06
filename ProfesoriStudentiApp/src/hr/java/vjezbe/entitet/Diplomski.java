package hr.java.vjezbe.entitet;

/**
 * Suèelje koje naljeðuju sveuèilišne obrazovne ustanove
 * @author Filip Èulig
 *
 */
public interface Diplomski extends Visokoskolska{
	/**
	 * Odreðuje dobitnika rektorove nagrade
	 * @return Student Dobitnik rektorove nagrade
	 */
	public Student odrediStudentaZaRektorovuNagradu();
}
