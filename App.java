import function.Function;
import function.FunctionVector;
import function.Identity;
import function.elementary.ExponentialFunction;
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

        Function f = new ExponentialFunction().compose(FunctionVector.scale(new Identity().squared(), -1));
        System.out.println(FunctionVector.scale(new Identity().squared(), -1).substitute("x", true));
        canvas.addFunction(f, -5, 5, Color.DARKCYAN, 1);

        stage.setTitle(f.toString());
        stage.setScene(new Scene(layout));
        stage.show();
        canvas.drawAxes();
        canvas.drawCurves();
    }

}
