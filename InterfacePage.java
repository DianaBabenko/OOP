import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.List;

class InterfacePage {
	private InterfacePage interfacePage;
	private DataForTheMonth dataForTheMonth;
	private ElectricityAccounting electricityAccounting = new ElectricityAccounting();

	private JFrame frame;
	private JPanel panel;

	private JComboBox comboMonth;
	private JComboBox comboYear;
	private JComboBox comboFirstYear;
	private JComboBox comboLastYear;
	private JComboBox comboFirstMonth;
	private JComboBox comboLastMonth;

	private JTextField writeResultOfPreviousCounter;
	private JTextField writeResultOfCurrentCounter;
	private JTextField writePriceInCurrentMonth;
	
	private JTextArea textArea;
	private JScrollPane scrollPane;

	private JButton btnAddMonth;
	private JButton btnShowCurrentMonth;
	//private JButton btnShowNumberCostEnergy;
	private JButton btnClear;
	private JButton btnAdditionalInfo;

	private JLabel infoLabel;
	//private JLabel infoCalc;
	//private JLabel infoPeriod;

	private int monthIndex = 0;
	private int yearIndex = 0;
	private int indexFirstYear = 0;

	private String year;
	private String month;
	
	String currentMonth;

	String[] listOfMonth = {
		"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" 
	};

	String[] listOfYear = {
		"2017", "2018", "2019"
	};

	InterfacePage() {
		this.frame = new JFrame();
		this.panel = new JPanel();

		this.comboMonth = new JComboBox();
		this.comboYear = new JComboBox();

		this.writeResultOfPreviousCounter = new JTextField();
		this.writeResultOfCurrentCounter = new JTextField();
		this.writePriceInCurrentMonth = new JTextField();

		this.btnAddMonth = new JButton("Add month data");
		this.btnShowCurrentMonth = new JButton("Show current month");
		this.btnAdditionalInfo = new JButton("Info for period");
		this.btnClear = new JButton("Reset");

		this.infoLabel = new JLabel("");
		this.buildPanel();
	}

	private void buildPanel() {
		this.frame.setSize(920, 250);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLayout(new BorderLayout());

		this.writeResultOfPreviousCounter.setPreferredSize(new Dimension(50, 30));
		this.writeResultOfCurrentCounter.setPreferredSize(new Dimension(50, 30));
		this.writePriceInCurrentMonth.setPreferredSize(new Dimension(50, 30));

		this.panel.add(new JLabel("Month: "));

		this.comboMonth = new JComboBox(listOfMonth);
		this.panel.add(this.comboMonth);

		this.panel.add(new JLabel("Year: "));

		this.comboYear = new JComboBox(listOfYear);
		this.panel.add(this.comboYear);

		this.panel.add(new JLabel("Result of previous counter: "));
		this.panel.add(this.writeResultOfPreviousCounter);
		this.panel.add(new JLabel("Result of current counter: "));
		this.panel.add(this.writeResultOfCurrentCounter);
		this.panel.add(new JLabel("Price: "));
		this.panel.add(this.writePriceInCurrentMonth);
		
		this.panel.add(this.btnAddMonth);
		this.panel.add(this.btnShowCurrentMonth);
		this.panel.add(this.btnAdditionalInfo);
		this.panel.add(this.btnClear);

		this.panel.add(this.infoLabel);

        this.frame.setContentPane(this.panel);
        this.frame.setVisible(true);
        
        this.btnAddMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	kindOfMonth();
            	kindOfYear();
	    		createNewData();
            }
        });
        
        this.btnShowCurrentMonth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	infoLabel.setText(currentMonth);
            }
        });
         
        this.btnAdditionalInfo.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                getInfoForPeriod();
            }
        }); 

        this.btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	deleteInfo();
            }
        });
	}
	
	private void getInfoForPeriod() {
		JFrame frame1 = new JFrame("Information for period");
		JPanel panel1 = new JPanel();
		JButton btnShowInfo = new JButton("Show period");
		JLabel infCostAndUsedEnergy = new JLabel("");

		frame1.setSize(600, 250);
		frame1.setLayout(new BorderLayout());
		
		comboFirstYear = new JComboBox(listOfYear);
		comboLastYear = new JComboBox(listOfYear);
		comboFirstMonth = new JComboBox(Months.MONTHS);
		comboLastMonth = new JComboBox(Months.MONTHS);
		
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(500, 150));

		scrollPane = new JScrollPane(textArea);

		panel1.add(comboFirstYear);
		panel1.add(comboFirstMonth);
		panel1.add(comboLastYear);
		panel1.add(comboLastMonth);
		panel1.add(btnShowInfo);
		panel1.add(infCostAndUsedEnergy);
		panel1.add(scrollPane);

		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
        frame1.setContentPane(panel1);
        frame1.setVisible(true);
        
        btnShowInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int firstYear = Integer.parseInt((String)comboFirstYear.getSelectedItem());
            	int lastYear = Integer.parseInt((String)comboLastYear.getSelectedItem());
            	String firstMonth = (String)comboFirstMonth.getSelectedItem();
            	String lastMonth = (String)comboLastMonth.getSelectedItem();
            	List<DataForTheMonth> dataForPeriod = electricityAccounting.getElectricityDataForPeriod(firstYear, firstMonth, lastYear, lastMonth);
            	textArea.setText("");
            	for (DataForTheMonth dataForTheMonth : dataForPeriod) {
            		textArea.append(dataForTheMonth.toString() + "\n");
            	}

				ElectricityAccounting electricityAccounting = new ElectricityAccounting(dataForPeriod);
				electricityAccounting.getUsedEnergy();
				electricityAccounting.getCost();
            	infCostAndUsedEnergy.setText("Used energy: " + electricityAccounting.getTotalNumberOfEnergy() + "  " + "Cost: " + electricityAccounting.getTotalCostOfEnergy());
            }
        });	
	}

	private void kindOfMonth() {
		monthIndex = this.comboMonth.getSelectedIndex();
		month = listOfMonth[monthIndex];
	}

	private void kindOfYear() {
		yearIndex = this.comboYear.getSelectedIndex();
		year = listOfYear[yearIndex];
	}

	private void setData() {
		this.dataForTheMonth.setMonth(this.month);
		this.dataForTheMonth.setYear(Integer.parseInt(this.year));
		try {
			this.dataForTheMonth.setResultOfCounters(Integer.parseInt(this.writeResultOfPreviousCounter.getText()), Integer.parseInt(this.writeResultOfCurrentCounter.getText()));
			this.dataForTheMonth.setPrice(new BigDecimal(this.writePriceInCurrentMonth.getText()));
			dataForTheMonth.calcUsedEnergy();
	    	dataForTheMonth.calcCostOfEnergy();
            electricityAccounting.addMonthData(dataForTheMonth);
            currentMonth = dataForTheMonth.toString();
            infoLabel.setText("");
		} catch(IllegalArgumentException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	private void deleteInfo() {
		this.writeResultOfPreviousCounter.setText("");
		this.writeResultOfCurrentCounter.setText("");
		this.writePriceInCurrentMonth.setText("");
		this.comboMonth.setSelectedIndex(0);
		this.comboYear.setSelectedIndex(0);
	}

	public void createNewData() {
		this.dataForTheMonth = new DataForTheMonth(this.month, Integer.parseInt(this.year));
	    setData();
	}
}
//****