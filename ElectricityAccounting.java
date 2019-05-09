import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ElectricityAccounting {
	List<DataForTheMonth> allData = new ArrayList<>();
		
	Integer totalNumberOfEnergy = 0;
	BigDecimal totalCostOfEnergy;

	public ElectricityAccounting() {
	}

	public ElectricityAccounting(List<DataForTheMonth> dataForMonths){
		this.allData = dataForMonths;
	}

	public Integer getTotalNumberOfEnergy() {
		return this.totalNumberOfEnergy;
	}

	public void setTotalNumberOfEnergy(Integer totalNumberOfEnergy) {
		this.totalNumberOfEnergy = totalNumberOfEnergy;
	}

	public BigDecimal getTotalCostOfEnergy() {
		return this.totalCostOfEnergy;
	}

	public void setTotalCostOfEnergy(BigDecimal totalCostOfEnergy) {
		this.totalCostOfEnergy = totalCostOfEnergy;
	}

	public void addMonthData(DataForTheMonth data) {
		allData.add(data);
		getUsedEnergy();
		getCost();
		System.out.println(allData);
	}
	
	public void sortInfo() {
		Collections.sort(allData);
	}
	
	public List<DataForTheMonth> getElectricityDataForPeriod(int firstYear, String firstMonth, int lastYear, String lastMonth) {
		List<DataForTheMonth> periodData = new ArrayList<>(); 
		int firstMonthIndex = Months.getMonthIndex(firstMonth);
		int lastMonthIndex = Months.getMonthIndex(lastMonth);
		for (DataForTheMonth dataForTheMonth: allData) {
			if ((dataForTheMonth.getYear() >= firstYear) && (dataForTheMonth.getYear() <= lastYear)) {
				if ((Months.getMonthIndex(dataForTheMonth.getMonth()) >= firstMonthIndex) && (Months.getMonthIndex(dataForTheMonth.getMonth()) <= lastMonthIndex)) {
					periodData.add(dataForTheMonth);
				}
			}
		}		
		return periodData;
	}

	public Integer getUsedEnergy() { 
		totalNumberOfEnergy = 0;
		for (DataForTheMonth dataForTheMonth: allData) {
			totalNumberOfEnergy += dataForTheMonth.getEnergyPerMonth();
        }
        return totalNumberOfEnergy;
	}

	public BigDecimal getCost() {
		totalCostOfEnergy = new BigDecimal(0);
		for (DataForTheMonth dataForTheMonth: allData) {
			totalCostOfEnergy = totalCostOfEnergy.add(dataForTheMonth.getCostPerMonth());
		}
		return totalCostOfEnergy;
	}

}
//*******