package imageswarehouse.view;

import imageswarehouse.App;
import imageswarehouse.model.entity.MyImage;
import javafx.geometry.Insets;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewTablePictures {
    //Поле Pagination
    private Pagination p;

    /**
     * Конструктор - создание нового объекта
     * @see ViewTablePictures#ViewTablePictures(List<MyImage>)
     */
    public ViewTablePictures(List<MyImage> fileList) {
        p = new Pagination(fileList.size()/15+1);
        p.setPageFactory(n -> addImgFlow(fileList, p.getCurrentPageIndex()));
    }

    /**
     * Функция получения значения поля {@link ViewTablePictures#p}
     * @return возвращает Pagination(разбиение на страницы)
     */
    public Pagination getPagination() {
        return p;
    }

    /**
     * Метод возвращает таблицу с картинками
     * @return возвращает ScrollPane
     */
    private ScrollPane addImgFlow(List<MyImage> list, int currentPage){
        int firstElement = (currentPage*16 == 0? 0: currentPage*16-1);
        int listSize = list.size();
        int secondElement = (firstElement+16 > listSize? listSize: firstElement+16);
        List<MyImage> tmpList = new ArrayList<>(list.subList(firstElement, secondElement));
        FlowPane flowPane = newFlowPain();
        List<ImageView> imageViewList = new ArrayList<>();
        for (MyImage myImage: tmpList){
            Image im = new Image(new File(myImage.getPathImg()).toURI().toString());
            double wi = im.getWidth();
            double hi = im.getHeight();
            double newH = hi/(wi/300);
            ImageView imv = new ImageView(im);
            imv.setFitWidth(300);
            imv.setFitHeight(newH);
            imv.setOnMouseClicked(event -> {
                new ViewBigImgAndTable(myImage, list).getStage();
            });
            imageViewList.add(imv);
        }
        flowPane.getChildren().addAll(imageViewList);
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setPrefViewportHeight(App.stageHight-220);
        scrollPane.setPrefViewportWidth(App.stageWidth-90);
        return scrollPane;
    }
    /**
     * Метод создает FlowPane
     * @return возвращает FlowPane
     */
    private FlowPane newFlowPain(){
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(10));
        flowPane.setVgap(20);
        flowPane.setHgap(20);
        flowPane.prefWidthProperty().bind(App.javaFXC.widthProperty());
        flowPane.prefHeightProperty().bind(App.javaFXC.heightProperty());
        return flowPane;
    }
}
