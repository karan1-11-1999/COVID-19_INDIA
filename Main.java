package application;

import java.util.List;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.chart.BarChart;
import javafx.stage.Stage;

public class Main extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	
	private static JsonParser jp;
	private static Patient[] patients;
	
	private List<String> args;

	private static final int WINDOW_WIDTH = 954;
	private static final int WINDOW_HEIGHT = 710;
	private static final String APP_TITLE = "A team project";
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GridPane topGp = new GridPane();
		
		HBox countryHB = new HBox();
		Button countryButton = new Button();
		countryHB.getChildren().add(countryButton);
		//Image Set for Country
		Image cbImage = new Image("images/countryButton.jpg");
		ImageView cbImgView = new ImageView(cbImage);
		cbImgView.setFitWidth(300);
		cbImgView.setFitHeight(100);
		countryButton.setGraphic(cbImgView);
		//on clicking the button
		
		
		HBox stateHB = new HBox();
		Button stateButton = new Button();
		stateHB.getChildren().add(stateButton);
		//Image Set for State
		Image sbImage = new Image("images/stateButton.jpg");
		ImageView sbImgView = new ImageView(sbImage);
		sbImgView.setFitWidth(300);
		sbImgView.setFitHeight(100);
		stateButton.setGraphic(sbImgView);
		
		
		HBox ageHB = new HBox();
		Button ageButton = new Button();
		ageHB.getChildren().add(ageButton);
		//Image Set for Age
		Image abImage = new Image("images/ageButton.jpg");
		ImageView abImgView = new ImageView(abImage);
		abImgView.setFitWidth(300);
		abImgView.setFitHeight(100);
		ageButton.setGraphic(abImgView);
		
		topGp.add(countryHB,0,0);
		topGp.add(stateHB,1,0);
		topGp.add(ageHB,2,0);

        BorderPane root = new BorderPane();
        root.setTop(topGp);
        //Image set for bottom
       	Image bottomImage = new Image("images/bottom.jpg");
      	ImageView bottomImgView = new ImageView(bottomImage);
   		bottomImgView.setFitWidth(954);
   		bottomImgView.setFitHeight(100);
		root.setBottom(bottomImgView);
        //Image set for right
		Image rightImage = new Image("images/rightBackground.jpg");
      	ImageView rightImgView = new ImageView(rightImage);
   		rightImgView.setFitWidth(120);
   		rightImgView.setFitHeight(500);
		root.setRight(rightImgView);
		
		//root.setLeft();

		//center
		LineChart countrylc = drawCountryGraph();
		root.setCenter(countrylc);
		
		
		
		
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        // adds a stylesheet to change color of graph
        mainScene.getStylesheets().add("application/lcStyle.css");
        // Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        	
        
        //country
        countryButton.setOnAction(e->{
            root.setCenter(drawCountryGraph());
        });
        
        //state
        VBox example = new VBox();
        stateButton.setOnAction(e -> {
            root.setCenter(example);
        });
        
        //age
//        VBox example2 = new VBox();
        ageButton.setOnAction(e -> {
            root.setCenter(drawAgeGraph());
        });
	}
	
	
	private BarChart drawAgeGraph() {
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Age groups");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No of cases");
		
		BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
		bc.setTitle("How different age groups might be affected (only a reflection of age known cases)");
	
		
        class AgeGroup{
        	String ageSpan;
        	int totalCases;
        	
        	AgeGroup(String span, int cases){
        		ageSpan = span;
        		totalCases = cases;
        	}
        	
        }
        
        AgeGroup age30minus = new AgeGroup("30-",0);
        AgeGroup age31to45 = new AgeGroup("31-45",0);
        AgeGroup age46to60 = new AgeGroup("46-60", 0);
        AgeGroup age61to75 = new AgeGroup("61-75", 0);
        AgeGroup age76plus = new AgeGroup("76+", 0);
		AgeGroup unknown = new AgeGroup("Unknown", 0);
	
		Patient[] patients = jp.getPatients();
		
		//scans thru all patients and record the numbers by age
		for (int i = 0; i < patients.length; i++) {
			int age = patients[i].getAge();
			
			if(age < 0)
				unknown.totalCases++;
			
			else if( age < 31) 
				age30minus.totalCases++;
				
			else if(age < 46 )
				age31to45.totalCases++;
			
			else if(age < 61 )
				age46to60.totalCases++;
			
			else if( age < 76 )
				age61to75.totalCases++;
			
			else 
				age76plus.totalCases++;
			
		}
		
		XYChart.Series<String, Number> dataSeries= new XYChart.Series<String, Number>();
		dataSeries.getData().
		add(new XYChart.Data<String, Number>(age30minus.ageSpan, age30minus.totalCases));
		dataSeries.getData().
		add(new XYChart.Data<String, Number>(age31to45.ageSpan, age31to45.totalCases));
		dataSeries.getData().
		add(new XYChart.Data<String, Number>(age46to60.ageSpan, age46to60.totalCases));
		dataSeries.getData().
		add(new XYChart.Data<String, Number>(age61to75.ageSpan, age61to75.totalCases));
		dataSeries.getData().
		add(new XYChart.Data<String, Number>(age76plus.ageSpan, age76plus.totalCases));
//		dataSeries.getData().
//		add(new XYChart.Data<String, Number>(unknown.ageSpan, unknown.totalCases));
		
		
        bc.getData().add(dataSeries);

		return bc;
	}

	private LineChart drawCountryGraph() {
		
		/*
		 * 
		 * 
		 * 
		 * 
		 * IMPORTANT TODO
		 * 
		 * 
		 * 
		 * 
		 */
		
		
		//WE NEED DATA FROM PATIENT CLASS ABOUT
		//EACH DAY'S NUMBER OF CASES 
		
		//WANNA FILL DATE 
		//data.getData().add(new XYChart.Data<String,Number>("DATE STRING",nO Of cAsES ON tHaT dAY));

		//So for that we need a 2D array of DATE and NO OF CASES ON THAT DAY
		// 
		// PLEASE SOME CREATE AND FILL IN THE ARRAY SO I CAN CREATE A GRAPH
		
		
		
		
		//a 2D array that records how many new cases were reported on specific dates 
		
		//1. Define an inner class Date to record info regarding to THAT day
		class Date implements Comparable<Date>{
			String date;
			int Cases;
			int recovered;

			Date(String date, int newCases, int recorved) {
				this.date = date;
				this.Cases = newCases;
				this.recovered = recorved;
			}
			
			@Override
			public int compareTo(Date otherDate) {
			 //get rid of the exceptional case
				if(otherDate.date.equals("?"))
					return -1;
				if(date.equals("?"))
					return 1;
			// the non-empty dates would be of the format "Date/Month/Year"
				String[] ar1 = date.split("/");
				String[] ar2 = otherDate.date.split("/");
				
				int[] arr1 = new int[3];
				int[] arr2 = new int[3];
				for(int i = 0; i<3; i++) {
					arr1[i] = Integer.parseInt(ar1[i]);
					arr2[i] = Integer.parseInt(ar2[i]);
				}
				if(arr1[2] != arr2[2]) 
					return arr1[2] - arr2[2];
			    if(arr1[1] != arr2[1]) 
					return arr1[1] - arr2[1];
			    else
			    	return arr1[0] - arr2[0];
					
				
			}
		}

		//2. create a map to store all the Dates that have occurred and update the map as
		// vetting through Patients
		HashMap<String, Date> record = new HashMap<String, Date>(); //Using HashMap databasew

		JsonParser jp = new JsonParser();
		jp.liveURLToJSONToPatientObj();
		Patient[] patients = jp.getPatients();

		for (int i = 0; i < patients.length; i++) {
			String dateAnnounced = patients[i].getDateAnnounced();
			String statusChangeDate = patients[i].getStatusChangeDate();

			if (record.containsKey(dateAnnounced)) {
				// update info related to this Date
				Date update = record.get(dateAnnounced);
				update.Cases++;
				record.replace(dateAnnounced, update);
			}
			// update the recovered data if the patient has recovered
			if (record.containsKey(statusChangeDate) && patients[i].getCurrentStatus().equals("Recovered")) {
				Date update = record.get(statusChangeDate);
				update.recovered++;
				record.replace(statusChangeDate, update);
			}

			if (!record.containsKey(dateAnnounced)) {
				// otherwise it would have been counted in the previous IF
				if (!record.containsKey(statusChangeDate) && patients[i].getCurrentStatus().equals("Recovered")) {
					record.put(dateAnnounced, new Date(dateAnnounced, 1, 1)); // first patient on that date and has
					// recovered
				} else
					record.put(dateAnnounced, new Date(dateAnnounced, 1, 0)); // first patient of that date and hasn't
				// recovered
			}


			
			
		}
		
		//3. create a sorted ArrayList to store all the Dates that have showed up, remove "?"
		List<Date> dates = new ArrayList<Date>(record.values());
		for(int i = 0; i< dates.size(); i++) {
			if(dates.get(i).date.equals("?"))
				dates.remove(i);
		}
		Collections.sort(dates);
		
		//build the desired 2D array 
		//KARAN PATEL UPDATES (using indi)
		Object[][] countryGraph = new Object[3][dates.size()];
		for(int i=0; i<dates.size(); i++) {
			countryGraph[0][i] = dates.get(i).date.substring(0, dates.get(i).date.length() - 5);; // Date minus the year
			countryGraph[1][i] = dates.get(i).Cases; // Cases TOtal
			countryGraph[2][i] = dates.get(i).recovered; // Recovered
		}
	
		
		//To make the cases and recovered cumulative 
		
		/*int sumCases = 0;
		int sumRecovered = 0;
		for(int i=0; i<dates.size(); i++) {	
			sumCases = (int)countryGraph[1][i] + sumCases;
			countryGraph[1][i] = sumCases;
			sumRecovered = (int)countryGraph[2][i] + sumRecovered;
			countryGraph[2][i] = sumRecovered;
		}*/
		
		
	
		
		ObservableList<String> ob = FXCollections.observableArrayList();
		//https://www.youtube.com/watch?v=LyeQxISSqJs
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Date");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No of positive cases");
		
		LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("India Cases");
		
		
		XYChart.Series<String, Number> dataConfirmed = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> dataRecovered = new XYChart.Series<String, Number>();

		
		for(int i=0; i<countryGraph[0].length; i++) {
		
			dataConfirmed.getData().add(new XYChart.Data<String,Number>((String)countryGraph[0][i],(int)countryGraph[1][i]));
			dataRecovered.getData().add(new XYChart.Data<String,Number>((String)countryGraph[0][i],(int)countryGraph[2][i]));


		
		}
		
				
		dataConfirmed.setName("Confirmed");
		dataRecovered.setName("Recovered");

		
		lc.getData().add(dataConfirmed);
		lc.getData().add(dataRecovered);

		return lc;
		
		
		//Test
		//System.out.println(patients[0].getAge());
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {   
		
		//calling classes and methods
        jp = new JsonParser();
        //Creates a txt file from the live URL and then stores in Patient Object Array
        jp.liveURLToJSONToPatientObj(); 
        patients = jp.getPatients();
        
        //WHITEBOX TESTING
        //
        //
        //patient number - 1 gives array patient index
        //final int studentFromWuhanCase3 = 3;
        //System.out.println(patients[studentFromWuhanCase3-1].getPatientNumber()); //should return 3
        //System.out.println(patients[studentFromWuhanCase3-1].getDetectedDistrict()); //should return Kasaragod
        //
        //
        //WHITEBOX TESTING OVER
        
        //launching the javafx
		launch(args);
		   
	}
}