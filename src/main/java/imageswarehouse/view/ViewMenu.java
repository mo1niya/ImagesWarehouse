package imageswarehouse.view;

import imageswarehouse.App;
import imageswarehouse.controller.CreateTables;
import imageswarehouse.controller.GetDataSource;
import imageswarehouse.model.dao.CategoryDao;
import imageswarehouse.model.dao.MyImagesDao;
import imageswarehouse.model.entity.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewMenu {
    //Поле hBox
    private HBox hBox;
    //Поле stringCategory(выбранная категория)
    private String stringCategory;

    /**
     * Конструктор - создание нового объекта
     * @see ViewMenu#ViewMenu(String strCategory)
     */
    public ViewMenu(String strCategory) {
        stringCategory = strCategory;
        hBox = new HBox();
        Button button = addButtonLoadFoto();

        Text textCat = new Text("Категория:");

        ComboBox<String> comboBox = addComboBox();

        Button buttonCreateTable = addButtonCreateTable();

        hBox.getChildren().addAll(buttonCreateTable, button, textCat, comboBox);
        hBox.setPadding(new Insets(70, 12, 15, 40));
        hBox.setSpacing(10);
    }

    /**
     * Метод создания кнопки "Загрузить фото"
     * @return возвращает button
     */
    private Button addButtonLoadFoto(){
        Button button = new Button("Загрузить фото");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new ViewSaveImg().getStage();
            }
        });
        button.setPrefSize(150, 20);
        return button;
    }
    /**
     * Метод создания ComboBox со списком категорий
     * @return возвращает comboBox
     */
    private ComboBox<String> addComboBox(){
        List<Category> categoryList = new CategoryDao(new GetDataSource().getDataSource()).findAll();
        ObservableList<String> stringCat = FXCollections.observableArrayList();
        for (Category c: categoryList){
            stringCat.add(c.getName());
        }
        ComboBox<String> comboBox = new ComboBox(stringCat);
        comboBox.setValue(stringCategory);
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String strButtonCell = comboBox.getValue();
                Pagination pagination = new ViewTablePictures(new MyImagesDao
                        (new GetDataSource().getDataSource()).findByCategory(strButtonCell)).getPagination();
                FlowPane flowPane = new FlowPane(pagination);
                App.javaFXC.setMaximized(true);
                App.javaFXC.setScene(new Scene(new ViewFrame(flowPane, strButtonCell).getStackPane()));
            }
        });
        return comboBox;
    }
    /**
     * Метод создания кнопки "Создать таблицы"(создает таблицы в БД)
     * @return возвращает button
     */
    private Button addButtonCreateTable(){
        Button buttonCreateTable = new Button("Создать таблицы");
        buttonCreateTable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                new CreateTables(new GetDataSource().getDataSource()).createT();
            }
        });
        return buttonCreateTable;
    }
    /**
     * Функция получения значения поля {@link ViewMenu#hBox}
     * @return возвращает hBox
     */
    public HBox gethBox() {
        return hBox;
    }
}
