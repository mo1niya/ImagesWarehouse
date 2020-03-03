package imageswarehouse;

import imageswarehouse.controller.GetDataSource;
import imageswarehouse.model.dao.MyImagesDao;
import imageswarehouse.view.ViewFrame;
import imageswarehouse.view.ViewTablePictures;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class App extends Application {
    public static Stage javaFXC;
    public static double stageHight;
    public static double stageWidth;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        javaFXC = primaryStage;
        primaryStage.setTitle("Images warehouse");
        primaryStage.setMaximized(true);
        primaryStage.show();
        stageHight = primaryStage.getHeight();
        stageWidth = primaryStage.getWidth();
        Pagination pagination = new ViewTablePictures(new MyImagesDao
                (new GetDataSource().getDataSource()).findByCategory("Наташа")).getPagination();
        FlowPane flowPane = new FlowPane(pagination);
        primaryStage.setScene(new Scene(new ViewFrame(flowPane, "Наташа").getStackPane()));

    }

// Отображает картинку
//            BufferedImage buff_img = ImageIO.read(new ByteArrayInputStream(imageInByte));
//            Image im= SwingFXUtils.toFXImage(buff_img, null);
}
