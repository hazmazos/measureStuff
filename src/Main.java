import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private Canvas canvas = new Canvas(400, 400);


    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) {

        TextField widthField = new TextField("1000");
        TextField heightField = new TextField("1200");
        Button drawButton = new Button("Draw Window");

        drawButton.setOnAction(e -> drawWindow(widthField, heightField));

        VBox controls = new VBox(10, new Label("Width (mm):"), widthField,
                new Label("Height (mm):"), heightField,
                drawButton);

        controls.setPrefWidth(150);
        controls.setStyle("-fx-padding: 10;");

        HBox root = new HBox(controls, canvas);
        Scene scene = new Scene(root, 600, 420);
        primaryStage.setTitle("Digital Window Measurer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawWindow(TextField widthField, TextField heightField) {

        try {
            double widthMM = Double.parseDouble(widthField.getText());
            double heightMM = Double.parseDouble(heightField.getText());

            // Scale to fit inside canvas
            double scale = 300 / Math.max(widthMM, heightMM);
            double drawWidth = widthMM * scale;
            double drawHeight = heightMM * scale;

            double startX = 50;
            double startY = 50;

            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

            gc.strokeRect(startX, startY, drawWidth, drawHeight);

            gc.strokeText("Width: " + widthMM + " mm", startX, startY - 10);
            gc.strokeText("Height: " + heightMM + " mm", startX + drawWidth + 5, startY + drawHeight / 2);

        }
        catch (NumberFormatException ex) {
            System.out.println("Invalid input: " + ex.getMessage());
        }


    }
}