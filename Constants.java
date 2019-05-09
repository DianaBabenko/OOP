import java.util.Arrays;
import java.util.List;

public class Months {
	public static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	
	public String getMonths(int index) {
		return MONTHS[index];
	}
	
	public static int getMonthIndex(String monthName) {
		List<String> months = Arrays.asList(MONTHS);
		return months.indexOf(monthName);
	}
}
