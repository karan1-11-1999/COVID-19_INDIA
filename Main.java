package application;

import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
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
import javafx.scene.shape.Box;
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
		Image cbImage = new Image("file:///Users/johnney/eclipse-workspace/Covid19/images/countryButton.jpg");
		ImageView cbImgView = new ImageView(cbImage);
		cbImgView.setFitWidth(300);
		cbImgView.setFitHeight(100);
		countryButton.setGraphic(cbImgView);
		//on clicking the button
		
		HBox stateHB = new HBox();
		Button stateButton = new Button();
		stateHB.getChildren().add(stateButton);
		
		//Image Set for State
		Image sbImage = new Image("file:///Users/johnney/eclipse-workspace/Covid19/images/stateButton.jpg");
		ImageView sbImgView = new ImageView(sbImage);
		sbImgView.setFitWidth(300);
		sbImgView.setFitHeight(100);
		stateButton.setGraphic(sbImgView);
		
		HBox ageHB = new HBox();
		Button ageButton = new Button();
		ageHB.getChildren().add(ageButton);
		//Image Set for Age
		Image abImage = new Image("file:///Users/johnney/eclipse-workspace/Covid19/images/ageButton.jpg");
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
       	Image bottomImage = new Image("file:///Users/johnney/eclipse-workspace/Covid19/images/bottom.jpg");
      	ImageView bottomImgView = new ImageView(bottomImage);
   		bottomImgView.setFitWidth(954);
   		bottomImgView.setFitHeight(100);
		root.setBottom(bottomImgView);
		
        //Image set for right
		Image rightImage = new Image("file:///Users/johnney/eclipse-workspace/Covid19/images/rightBackground.jpg");
      	ImageView rightImgView = new ImageView(rightImage);
   		rightImgView.setFitWidth(120);
   		rightImgView.setFitHeight(500);
		root.setRight(rightImgView);
		
		

		//center
		root.setCenter(drawCountryGraph());
		
		
		
		
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		// Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        	
        
        //creating country scene
        Stage stateStage = new Stage();
        
        BorderPane countryRoot = new BorderPane();
        
        countryRoot.setCenter(drawStateGraph());
        
        Scene stateScene = new Scene(countryRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        stateStage.setTitle("State scene");
        stateStage.setScene(stateScene);
        stateStage.show();
        
        
        //creating gender scene
        Stage genderStage = new Stage();
        
        BorderPane genderRoot = new BorderPane();
        genderRoot.setCenter(drawPieChart());
        
        Scene genderScene = new Scene(genderRoot, WINDOW_WIDTH, WINDOW_HEIGHT);
        
        genderStage.setTitle("Gender Scene");
        genderStage.setScene(genderScene);
        genderStage.show();
        
        //creating age scene
        Stage ageStage = new Stage();
        BorderPane ageRoot = new BorderPane();
        BarChart<String, Number> temp = drawBarGraph(); 
        
        Scene ageScene = new Scene(temp, WINDOW_WIDTH, WINDOW_HEIGHT);
        ageStage.setScene(ageScene);
        ageStage.show();
        
	}
	
	private BarChart drawBarGraph() {
		
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(); 
        
        //need to be declared at top of class
        final String range1 = "0-19";
        final String range2 = "20-30";
        final String range3 = "30-40";
        final String range4 = "50-65";
        final String range5 = "75+";
        
		final BarChart<String,Number> barChart = 
	            new BarChart<String,Number>(xAxis,yAxis);
		
	        barChart.setTitle("Covid-19 Data by Age");
	        xAxis.setLabel("Age");       
	        yAxis.setLabel("No. of Cases");
	        
	        XYChart.Series series1 = new XYChart.Series();
	        series1.setName("Age");       
	        series1.getData().add(new XYChart.Data(range1, 500));
	        series1.getData().add(new XYChart.Data(range2, 500));
	        series1.getData().add(new XYChart.Data(range3, 500));
	        series1.getData().add(new XYChart.Data(range4, 500));
	        series1.getData().add(new XYChart.Data(range5, 500));
	        
	        barChart.getData().addAll(series1);
	        
		
		return barChart;
	}
	private PieChart drawPieChart() {
		ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data("Male", 40),
                new PieChart.Data("Female", 40),
                new PieChart.Data("Awaiting Data", 20));
		
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Covid Data by Gender");
        
        return chart;
	}
	
	private LineChart drawStateGraph() {
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Date");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No of positive cases");
		
		LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("Cases by State");
		
		
		XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> data1 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> data2 = new XYChart.Series<String, Number>();
		XYChart.Series<String, Number> data3 = new XYChart.Series<String, Number>();
		
		//data.getData().add(new XYChart.Data<String,Number>("DATE STRING WITH TRUNCATE",CASES));
		//DUMMY DATA!!!
		data.getData().add(new XYChart.Data<String,Number>("12/4",207));
		data.getData().add(new XYChart.Data<String,Number>("13/4",342));
		data.getData().add(new XYChart.Data<String,Number>("14/4",750));
		data.getData().add(new XYChart.Data<String,Number>("15/4",750));
		data.getData().add(new XYChart.Data<String,Number>("16/4",750));
		data.getData().add(new XYChart.Data<String,Number>("17/4",750));
		data.getData().add(new XYChart.Data<String,Number>("18/4",750));
		data.getData().add(new XYChart.Data<String,Number>("19/4",750));
		data.getData().add(new XYChart.Data<String,Number>("20/4",750));
		data.getData().add(new XYChart.Data<String,Number>("21/4",750));
		data.getData().add(new XYChart.Data<String,Number>("22/4",750));
		data.getData().add(new XYChart.Data<String,Number>("23/4",750));
		data.getData().add(new XYChart.Data<String,Number>("24/4",750));
		data.getData().add(new XYChart.Data<String,Number>("25/4",750));

		data1.getData().add(new XYChart.Data<String,Number>("12/4",0));
		data1.getData().add(new XYChart.Data<String,Number>("13/4",10));
		data1.getData().add(new XYChart.Data<String,Number>("14/4",20));
		data1.getData().add(new XYChart.Data<String,Number>("15/4",30));
		data1.getData().add(new XYChart.Data<String,Number>("16/4",70));
		data1.getData().add(new XYChart.Data<String,Number>("17/4",100));
		data1.getData().add(new XYChart.Data<String,Number>("18/4",50));
		data1.getData().add(new XYChart.Data<String,Number>("19/4",50));
		data1.getData().add(new XYChart.Data<String,Number>("20/4",200));
		data1.getData().add(new XYChart.Data<String,Number>("21/4",400));
		data1.getData().add(new XYChart.Data<String,Number>("22/4",500));
		data1.getData().add(new XYChart.Data<String,Number>("23/4",650));
		data1.getData().add(new XYChart.Data<String,Number>("24/4",430));
		data1.getData().add(new XYChart.Data<String,Number>("25/4",90));
		
		data2.getData().add(new XYChart.Data<String,Number>("12/4",207));
		data2.getData().add(new XYChart.Data<String,Number>("13/4",342));
		data2.getData().add(new XYChart.Data<String,Number>("14/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("15/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("16/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("17/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("18/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("19/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("20/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("21/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("22/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("23/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("24/4",750));
		data2.getData().add(new XYChart.Data<String,Number>("25/4",750));
		
		data3.getData().add(new XYChart.Data<String,Number>("12/4",207));
		data3.getData().add(new XYChart.Data<String,Number>("13/4",342));
		data3.getData().add(new XYChart.Data<String,Number>("14/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("15/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("16/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("17/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("18/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("19/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("20/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("21/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("22/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("23/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("24/4",750));
		data3.getData().add(new XYChart.Data<String,Number>("25/4",750));
		
		data.setName("Region 1");
		data1.setName("Region 2");
		data2.setName("Region 3");
		data3.setName("Region 4");
		
		
		lc.getData().add(data);
		lc.getData().add(data1);
		lc.getData().add(data2);
		lc.getData().add(data3);
		return lc;
		
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
		
		
		ObservableList<String> ob = FXCollections.observableArrayList();
		//https://www.youtube.com/watch?v=LyeQxISSqJs
		
		CategoryAxis xAxis = new CategoryAxis();
		xAxis.setLabel("Date");
		
		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("No of positive cases");
		
		LineChart<String, Number> lc = new LineChart<String, Number>(xAxis, yAxis);
		lc.setTitle("India Cases");
		
		
		XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();
		
		//data.getData().add(new XYChart.Data<String,Number>("DATE STRING WITH TRUNCATE",CASES));
		//DUMMY DATA!!!
		data.getData().add(new XYChart.Data<String,Number>("12/4",207));
		data.getData().add(new XYChart.Data<String,Number>("13/4",342));
		data.getData().add(new XYChart.Data<String,Number>("14/4",750));
		data.getData().add(new XYChart.Data<String,Number>("15/4",750));
		data.getData().add(new XYChart.Data<String,Number>("16/4",750));
		data.getData().add(new XYChart.Data<String,Number>("17/4",750));
		data.getData().add(new XYChart.Data<String,Number>("18/4",750));
		data.getData().add(new XYChart.Data<String,Number>("19/4",750));
		data.getData().add(new XYChart.Data<String,Number>("20/4",750));
		data.getData().add(new XYChart.Data<String,Number>("21/4",750));
		data.getData().add(new XYChart.Data<String,Number>("22/4",750));
		data.getData().add(new XYChart.Data<String,Number>("23/4",750));
		data.getData().add(new XYChart.Data<String,Number>("24/4",750));
		data.getData().add(new XYChart.Data<String,Number>("25/4",750));
				
		data.setName("Confirmed");
		
		lc.getData().add(data);
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