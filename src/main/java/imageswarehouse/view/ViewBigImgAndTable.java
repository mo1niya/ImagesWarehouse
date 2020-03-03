package imageswarehouse.view;

import imageswarehouse.App;
import imageswarehouse.model.entity.MyImage;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewBigImgAndTable{
    //Поле Stage(новое окно)
    private Stage stage;
    //Поле FlowPane(контейнер FlowPane)
    private FlowPane flowPaneBigImg;
    //Поле List(список выводимых фото)
    private List<MyImage> myImages;
    //Поле double(высота окна)
    private double hightStage;
    //Поле double(ширина окна)
    private double wStage;

    /**
     * Конструктор - создание нового объекта
     * @see ViewBigImgAndTable#ViewBigImgAndTable(MyImage, List<MyImage>)
     */
    public ViewBigImgAndTable(MyImage myImage, List<MyImage> myImageList) {
        myImages = myImageList;
        stage = addStage();
        hightStage = stage.getHeight();

        FlowPane flowMain = addMainFlowPane();

        FlowPane flowPane = new FlowPane(10, 10);
        flowPane.setAlignment(Pos.TOP_LEFT);

        flowPaneBigImg = new FlowPane();
        wStage = stage.getWidth();
        flowPaneBigImg.getChildren().add(addBigImg(myImage, hightStage, wStage));

        List<ImageView> imageViews = addListImageView();

        flowPane.getChildren().addAll(imageViews);

        flowMain.getChildren().addAll(flowPaneBigImg, addScrollPane(flowPane));
        StackPane stackPane = new ViewFrame(flowMain, myImage.getCategory().get(0).getName()).getStackPane();
        stackPane.prefWidthProperty().bind(App.javaFXC.widthProperty());
        stage.setScene(new Scene(stackPane));

    }
    /**
     * Метод создает новое окно
     * @return возвращает Stage
     */
    private Stage addStage(){
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMaximized(true);
        stage.show();
        return stage;
    }
    /**
     * Метод создает основной контейнер
     * @return возвращает FlowPane
     */
    private FlowPane addMainFlowPane(){
        FlowPane flowMain = new FlowPane();
        flowMain.setPadding(new Insets(20));
        flowMain.setOrientation(Orientation.VERTICAL);
        return flowMain;
    }
    /**
     * Метод создает список ImageView, выводимых вертикально миниатюр
     * @return возвращает List
     */
    private List<ImageView> addListImageView(){
        List<ImageView> imageViews = new ArrayList<>();
        for (MyImage myI: myImages) {
            Image im = new Image(new File(myI.getPathImg()).toURI().toString());
            double wi = im.getWidth();
            double hi = im.getHeight();
            double newH = hi/(wi/300);
            ImageView imv = new ImageView(im);
            imv.setFitWidth(300);
            imv.setFitHeight(newH);
            imv.setOnMouseClicked(event -> {
                flowPaneBigImg.getChildren().clear();
                flowPaneBigImg.getChildren().add(addBigImg(myI, hightStage, wStage));
            });
            imageViews.add(imv);
        }
        return imageViews;
    }

    private ScrollPane addScrollPane(Pane pane){
        ScrollPane scrollPaneImgs = new ScrollPane(pane);
        scrollPaneImgs.setPrefViewportWidth(300);
        scrollPaneImgs.setPrefViewportHeight(hightStage-200);
        return scrollPaneImgs;
    }

    private FlowPane addBigImg(MyImage myImage, double hightStage, double wStage){
        FlowPane flowPane = new FlowPane(new ViewPicture(myImage, hightStage-200, wStage).getVBox());
        return flowPane;
    }

    public Stage getStage() {
        return stage;
    }
}
