import function.Function;
import function.elementary.PowerFunction;
import function.trigonometric.Cosine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FunctionCanvas canvas = new FunctionCanvas(400, 400);
        StackPane layout = new StackPane(canvas);
        layout.setPadding(new Insets(20));

        Function f = PowerFunction.of(1).times(new Cosine());
        canvas.addFunction(f, 0, 9, Color.DARKCYAN, 2);

        stage.setTitle(f.toString());
        stage.setScene(new Scene(layout));
        stage.show();
        canvas.drawAxes();
        canvas.drawCurves();
    }

}
