package imageswarehouse.view;

import imageswarehouse.controller.GetDataSource;
import imageswarehouse.model.dao.CategoryDao;
import imageswarehouse.model.entity.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewSelectCategory {
    //Поле CheckBox(флажок)
    private static CheckBox checkBox;
    //Поле список выбранных категорий
    static List<Category> selectedCategories;
    //Поле Stage(окно)
    private static Stage stage;
    //Поле текстовое поле категория
    private static TextField newCategory;
    //Поле список всех категорий из БД
    private static List<Category> categoryListFindAll;
    //Поле список флажков
    private static ObservableList<CheckBox> checkBoxList;
    //Поле FlowPane
    private static FlowPane flowPane;

    /**
     * Метод создает модальное окно выбора категорий
     * @return возвращает список выбранных категорий
     */
    public static List<Category> ViewSelectCategory() {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        flowPane = addFlowPane(10);

        getCheckBoxList();

        Button buttonOk = addButtonOk();

        newCategory = new TextField();
        newCategory.setAlignment(Pos.TOP_LEFT);

        Button buttonAddCat = addButtonAddCat();

        flowPane.getChildren().addAll(checkBoxList);
        ScrollPane scrollPane = new ScrollPane(flowPane);
        scrollPane.setPrefViewportWidth(750);
        scrollPane.setPrefViewportHeight(600);

        FlowPane flowPaneNewCat = addFlowPane(20);
        flowPaneNewCat.getChildren().addAll(newCategory, buttonAddCat);

        GridPane gridPane = addGridPane();
        gridPane.add(scrollPane, 1, 1);
        gridPane.add(buttonOk, 1, 2);
        gridPane.add(flowPaneNewCat, 1, 3);
        stage.setScene(new Scene(gridPane, 800, 500));
        stage.showAndWait();
        return selectedCategories;
    }

    /**
     * Метод создает список флажков(checkBox)
     */
    private static void getCheckBoxList(){
        CategoryDao categoryDao = new CategoryDao(new GetDataSource().getDataSource());
        categoryListFindAll = categoryDao.findAll();
        checkBoxList = FXCollections.observableArrayList();
        if (categoryListFindAll.size() != 0){
            for (Category cat: categoryListFindAll){
                checkBoxList.add(addCheckBox(cat.getName()));
            }
        }
    }

    /**
     * Метод создает FlowPane
     * @return возвращает FlowPane
     */
    private static FlowPane addFlowPane(int i){
        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        flowPane.setPadding(new Insets(i));
        return flowPane;
    }

    /**
     * Метод создает кнопку ОК(закрывает окно)
     * @return возвращает Button
     */
    private static Button addButtonOk(){
        Button buttonOk = new Button("OK");
        buttonOk.setScaleX(1);
        buttonOk.setPadding(new Insets(10, 20, 10, 20));
        buttonOk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                selectedCategories = new ArrayList<>();
                for (CheckBox checkBox: checkBoxList){
                    if (checkBox.isSelected()){
                        selectedCategories.add(new Category(checkBox.getText()));
                    }
                }
                if (selectedCategories.isEmpty()){
                    new ViewInf("Не выбрано ни одной категории");
                }
                stage.close();
            }
        });
        return buttonOk;
    }

    /**
     * Метод создает кнопку "Добавить категорию"(добавляет флажок новой категории, в БД добавляет только при сохранении картинки
     * @return возвращает Button
     */
    private static Button addButtonAddCat(){
        Button buttonAddCat = new Button("Добавить категорию");
        buttonAddCat.setScaleX(1);
        buttonAddCat.setAlignment(Pos.TOP_LEFT);
        buttonAddCat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String strTextField = newCategory.getText();
                if (strTextField != null && !strTextField.trim().equals("")){
                    Category c = new Category(strTextField);
                    if (!categoryListFindAll.contains(c)){
                        categoryListFindAll.add(c);
                        CheckBox newCheckBox = addCheckBox(c.getName());
                        checkBoxList.add(newCheckBox);
                        flowPane.getChildren().addAll(newCheckBox);
                    }
                }
            }
        });
        return buttonAddCat;
    }

    /**
     * Метод создает флажок(текст из параметра)
     * @return возвращает CheckBox
     */
    private static CheckBox addCheckBox(String textTitle){
        checkBox = new CheckBox(textTitle);
        checkBox.setPadding(new Insets(10));
        return checkBox;
    }

    /**
     * Метод создает GridPane
     * @return возвращает GridPane
     */
    private static GridPane addGridPane(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(20));
        return gridPane;
    }
}
