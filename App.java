/**
 * main application class
 */
import function.Function;
import function.elementary.LogarithmicFunction;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    /**
     * Launches the application.
     *
     * @param args the command line arguments
     * @throws Exception if an error occurs during application launch
     */
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    /**
     * This method is called when the application is launched.
     * It sets up the stage, creates a FunctionCanvas, adds a function to the canvas,
     * sets the title of the stage, and displays the stage.
     *
     * @param stage the primary stage for this application
     * @throws Exception if an error occurs during the start of the application
     */
    @Override
    public void start(Stage stage) throws Exception {
        FunctionCanvas canvas = new FunctionCanvas(400, 400);
        StackPane layout = new StackPane(canvas);
        layout.setPadding(new Insets(20));

        Function f = new LogarithmicFunction(2);
        canvas.addFunction(f, 0, 8, Color.DARKCYAN);

        stage.setTitle(f.toString());
        stage.setScene(new Scene(layout));
        stage.show();
        canvas.draw();
    }

}
