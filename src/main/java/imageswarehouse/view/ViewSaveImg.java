package imageswarehouse.view;

import imageswarehouse.controller.GetDataSource;
import imageswarehouse.controller.LoadFile;
import imageswarehouse.controller.LoadFiles;
import imageswarehouse.model.dao.MyImagesDao;
import imageswarehouse.model.entity.Category;
import imageswarehouse.model.entity.MyImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewSaveImg {
    //Поле Stage(окно)
    private Stage stage;
    //Поле File(загружаемый файл)
    private File fileLoad;
    //Поле File(директория для загрузки)
    private File directoryLoad;
    //Поле список категорий для загружаемого фото
    private List<Category> listCatOne = new ArrayList<>();
    //Поле список категорий для загружаемых фото
    private List<Category> listCatAll = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта
     * @see ViewSaveImg#ViewSaveImg()
     */
    public ViewSaveImg() {
        stage = new Stage();
        stage.setTitle("Загрузка изображений");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMaximized(true);
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));

        GridPane gridPaneSaveOne = gridPaneSaveOne();

        GridPane gridPaneSaveAll = gridPaneSaveAll();

        Text textOne = new Text("Загрузка одного фото. Можно выбрать только один файл.");
        textOne.setFont(Font.font(24));
        Text textAll = new Text("Массовая загрузка фото. Можно выбрать папку. Будут загружены файлы любой вложенности.");
        textAll.setFont(Font.font(24));
        vBox.getChildren().addAll(textOne, gridPaneSaveOne, textAll, gridPaneSaveAll);
        stage.setScene(new Scene(vBox));
        stage.show();
    }

    /**
     * Метод возвращает контейнер с элементами:
     * текстовое поле, в которое записывается путь к файлу;
     * кнопка "Выбрать категорию";
     * текстовое поле, в которое записывается список категорий;
     * кнопка "Загрузить одно фото";
     * кнопка ОК(загрузка в БД)
     * @return возвращает GridPane
     */
    private GridPane gridPaneSaveOne(){
        GridPane gridPane = new GridPane();

        TextField textFieldSaveOne = new TextField();
        textFieldSaveOne.setPrefWidth(300);

        TextField textFieldCatOne = new TextField();
        textFieldCatOne.setPrefWidth(300);

        Button buttonSaveOne = new Button("Загрузить одно фото");
        buttonSaveOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fileLoad = new ViewOpenFile().getFile();
                if (fileLoad != null){
                    textFieldSaveOne.setText(fileLoad.getPath());
                }
            }
        });

        Button buttonCat = new Button("Выбрать категорию");
        buttonCat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCatOne = ViewSelectCategory.ViewSelectCategory();
                String str = new String();
                if (listCatOne != null){
                    for (Category c: listCatOne){
                        str = str + c.getName() + " ";
                    }
                }
                textFieldCatOne.setText(str);
            }
        });

        Button buttonSaveOneOk = new Button("OK");
        buttonSaveOneOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (fileLoad != null) {
                    MyImage loadedImg = new LoadFile().saveMyImage(fileLoad, listCatOne);
                    if (loadedImg != null){
                        String catLoadedFile = "";
                        if (listCatOne.size() != 0){
                            catLoadedFile = listCatOne.get(0).getName();
                        }
                        new ViewBigImgAndTable(loadedImg,
                                new MyImagesDao(new GetDataSource().getDataSource()).findByCategory(catLoadedFile));
                        stage.close();
                    } else {
                        new ViewInf("Файл уже существует");
                    }
                    textFieldSaveOne.clear();
                    textFieldCatOne.clear();
                } else {
                    new ViewInf("Файл не выбран!");
                }
            }
        });

        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.add(buttonSaveOne, 1, 1);
        gridPane.add(textFieldSaveOne, 2, 1);
        gridPane.add(buttonCat, 1, 2);
        gridPane.add(textFieldCatOne, 2, 2);
        gridPane.add(buttonSaveOneOk, 1, 3);
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        return gridPane;
    }

    /**
     * Метод возвращает контейнер с элементами:
     * текстовое поле, в которое записывается каталог;
     * кнопка "Выбрать категорию";
     * текстовое поле, в которое записывается список категорий;
     * кнопка "Загрузить много фото";
     * кнопка ОК(загрузка в БД)
     * @return возвращает GridPane
     */
    private GridPane gridPaneSaveAll(){
        GridPane gridPane = new GridPane();

        TextField textFieldSaveAll = new TextField();
        textFieldSaveAll.setPrefWidth(300);

        TextField textFieldCatAll = new TextField();
        textFieldCatAll.setPrefWidth(300);

        Button buttonSaveAll = new Button("Загрузить много фото");
        buttonSaveAll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                directoryLoad = new ViewOpenDirectiry().getFile();
                if (directoryLoad != null){
                    textFieldSaveAll.setText(directoryLoad.getPath());
                }
            }
        });

        Button buttonCat = new Button("Выбрать категорию");
        buttonCat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                listCatAll = ViewSelectCategory.ViewSelectCategory();
                String str = new String();
                if (!listCatAll.isEmpty()){
                    for (Category c: listCatAll){
                        str = str + c.getName() + " ";
                    }
                }
                textFieldCatAll.setText(str);
            }
        });
        Button buttonSaveAllOk = new Button("OK");
        buttonSaveAllOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (directoryLoad != null) {
                    new LoadFiles().saveFile(directoryLoad, listCatAll);
                    textFieldCatAll.clear();
                    textFieldSaveAll.clear();
                } else {
                    new ViewInf("Папка не выбрана!");
                }
            }
        });

        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(20);
        gridPane.setHgap(20);
        gridPane.add(buttonSaveAll, 1, 1);
        gridPane.add(textFieldSaveAll, 2, 1);
        gridPane.add(buttonCat, 1, 2);
        gridPane.add(textFieldCatAll, 2, 2);
        gridPane.add(buttonSaveAllOk, 1, 3);
        gridPane.setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        return gridPane;
    }

    /**
     * Функция получения значения поля {@link ViewSaveImg#stage}
     * @return возвращает stage(окно загрузки файлов)
     */
    public Stage getStage() {
        return stage;
    }
}
