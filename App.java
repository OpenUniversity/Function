import curve.CurveCanvas;
import curve.EvaluationCurve;
import function.Function;
import function.trigonometric.Sine;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        CurveCanvas canvas = new CurveCanvas(400, 400);
        StackPane layout = new StackPane(canvas);
        Function f = new Sine();
        canvas.addCurve(new EvaluationCurve(f::evaluate, 0, 8));
        layout.setPadding(new Insets(20));

        stage.setTitle(f.toString());
        stage.setScene(new Scene(layout));
        stage.show();
        canvas.drawAxes();
        canvas.drawCurves();
    }

}
