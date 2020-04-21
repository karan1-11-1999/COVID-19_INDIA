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
		
		

		//center
		root.setCenter(drawCountryGraph());
		
		
		
		
        Scene mainScene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
		// Add the stuff and set the primary stage
        primaryStage.setTitle(APP_TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        	
        
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