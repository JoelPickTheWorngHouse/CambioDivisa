package dad.cambiodivisa;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

	TextField a1;
	TextField a2;
	ComboBox<Divisa> selector1;
	ComboBox<Divisa> selector2;
	Button revisar;
	double resultado;
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		a1=new TextField();
		a1.setPrefColumnCount(4);
		
		selector1=new ComboBox<Divisa>();
		selector1.getItems().addAll(
				new Divisa("Euro", 1.0),
				 new Divisa("Libra", 0.8873),
				 new Divisa("Dolar", 1.2007),
				 new Divisa("Yen", 133.59)
				);
		selector1.getSelectionModel().selectFirst();
		
		a2=new TextField();
		a2.setPrefColumnCount(4);
		a2.setEditable(false);
		
		selector2=new ComboBox<Divisa>();
		selector2.getItems().addAll(
				new Divisa("Euro", 1.0),
				 new Divisa("Libra", 0.8873),
				 new Divisa("Dolar", 1.2007),
				 new Divisa("Yen", 133.59)
				);
		selector2.getSelectionModel().select(3);
		
		revisar =new Button("Cambiar");
		revisar.setOnAction(e ->CambioDeDivisa(e));
		
		
		VBox root = new VBox(3, 
				new HBox(3,a1, selector1), 
				new HBox(3,a2, selector2),
				revisar
			);
		root.setFillWidth(false);
		root.setAlignment(Pos.CENTER);

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Cambio Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}


	private void CambioDeDivisa(ActionEvent e) {

		try {
		double origen=Double.parseDouble((a1.getText()));
		double tasadestino=(selector2.getValue().getTasa());
		double divisor=(selector1.getValue().getTasa());
		
		resultado=(origen*tasadestino)/divisor;
		
		}catch(Exception e1) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Cambio Divisa");
			alert.setHeaderText("Error de cambio");
			alert.setContentText("Ha introducido un valor numérico incorrecto, por favor no deje huecos vacío o introduzca texto");
			alert.show();
		}
		String cadena = Double.toString(resultado);
		a2.setText(cadena);
	}


}
