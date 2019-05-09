import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class DataForTheMonth implements Comparable<DataForTheMonth> {
	String month;
	Integer year;
	Integer resultOfPreviousCounter;
	Integer resultOfCurrentCounter;
	Integer energyPerMonth;
	BigDecimal price;
	BigDecimal costPerMonth;

	public DataForTheMonth(String currentMonth, Integer year) {
		this.month = currentMonth;
		this.year = year;
	}

	public Integer calcUsedEnergy() {
		this.energyPerMonth = this.resultOfCurrentCounter - this.resultOfPreviousCounter;
		return this.energyPerMonth;
	}

	public BigDecimal calcCostOfEnergy() {
		getEnergyPerMonth();
		getPrice();
   		costPerMonth = BigDecimal.valueOf(this.energyPerMonth).multiply(this.price); 
   		return costPerMonth;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String currentMonth) {
		this.month = currentMonth;
	}

	public Integer getYear() {
		return this.year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getResultOfPreviousCounter() {
		return this.resultOfPreviousCounter;
	}

	public void setResultOfCounters(Integer resultOfPreviousCounter, Integer resultOfCurrentCounter) throws IllegalArgumentException {
		if ((resultOfPreviousCounter > resultOfCurrentCounter) || (resultOfPreviousCounter < 0)){
			throw new IllegalArgumentException("Показник лічильника на початок місяця не може бути більшим ніж показник в кінці місяця");
		} else {
			this.resultOfPreviousCounter = resultOfPreviousCounter;
			this.resultOfCurrentCounter = resultOfCurrentCounter;
		}
	}

	public Integer getResultOfCurrentCounter() {
		return this.resultOfCurrentCounter;
	}

	public void setResultOfCurrentCounter(Integer resultOfCurrentCounter) {
		this.resultOfCurrentCounter = resultOfCurrentCounter;
	}

	public Integer getEnergyPerMonth() {
		return this.energyPerMonth;
	}

	public void setEnergyPerMonth(Integer energyPerMonth) {
		this.energyPerMonth = energyPerMonth;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCostPerMonth() {
		return this.costPerMonth;
	}

	public void setCostPerMonth(BigDecimal costPerMonth) {
		this.costPerMonth = costPerMonth;
	}

	public String toString() {
		return String.format("Month: %s, Year: %s, Used energy: %s, Price: %s, Cost: %s", 
			this.getMonth(), this.getYear(), this.getEnergyPerMonth(), this.getPrice(), this.getCostPerMonth());
	}

	@Override
	public int compareTo(DataForTheMonth dataForTheMonth) {
		System.out.println("inside comparable");
		if (this.year.equals(dataForTheMonth.year)) {
			int sizeMonths = Months.MONTHS.length;
			int indexCurrentMonth = 0;
			int indexDataForTheMonth = 0;
			for (int i = 0; i < sizeMonths; i++) {
				if (this.month.equals(Months.MONTHS[i])) {
					indexCurrentMonth = i;
				}
				if (dataForTheMonth.month.equals(Months.MONTHS[i])) {
					indexDataForTheMonth = i;
				}
			}
			if (indexCurrentMonth == indexDataForTheMonth) {
				return 0;
			} else if (indexCurrentMonth < indexDataForTheMonth) {
				return -1;
			} else {
				return 1;
			}
		} else if (this.year < dataForTheMonth.year) {
			return -1;
		} else {
			return 1;
		}
	}
}
//****