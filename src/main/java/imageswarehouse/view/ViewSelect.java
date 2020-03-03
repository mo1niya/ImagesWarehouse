package imageswarehouse.view;

import imageswarehouse.App;
import imageswarehouse.controller.GetImagesFromDB;
import imageswarehouse.model.entity.Category;
import imageswarehouse.model.entity.MyImage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewSelect {
    //Поле Stage
    private Stage stage;
    /**
     * Конструктор - создание нового объекта
     * @see ViewSelect#ViewSelect()
     */
    public ViewSelect() {
    }

    /**
     * Метод создает новое окно, показывает дубликат загружаемого фото
     */
    public void view(MyImage myImageLoad, MyImage myImageBD){
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMaximized(true);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setVgap(10);
        gridPane.setHgap(30);
        gridPane.add(textTitle("Фото из БД"),0, 0);
        gridPane.add(textTitle("Новое фото"),1, 0);
        Image img = new Image(new File(myImageLoad.getPathImg()).toURI().toString());
        double stageHight = getImgHight(img.getHeight(), img.getWidth());
        gridPane.add(new GetImagesFromDB().imgAsImageView(myImageBD, stageHight),0, 1);
        gridPane.add(new GetImagesFromDB().imgAsImageView(myImageLoad, stageHight),1, 1);
        gridPane.add(getStrCat(myImageBD.getCategory()),0, 2);
        gridPane.add(getStrCat(myImageLoad.getCategory()),1, 2);

        Button button = addButton();
        gridPane.add(button, 3, 0);

        stage.setScene(new Scene(gridPane));
        stage.showAndWait();
    }

    /**
     * Метод возвращает нужную высоту фото
     * @return возвращает double
     */
    private double getImgHight(double hi, double wi){
        double newHi = App.stageHight-150;
        if (wi > hi){
            newHi = hi/(wi/((App.stageWidth-200)/2));
        }
        return newHi;
    }

    /**
     * Метод создает Text
     * @return возвращает Text
     */
    private Text textTitle(String str){
        Text text = new Text(str);
        text.setFont(new Font(24));
        return text;
    }

    /**
     * Метод создает перечень категорий
     * @return возвращает Text
     */
    private Text getStrCat(List<Category> list){
        String str = "";
        if (list.size() != 0){
            for (Category c: list){
                str = str + c.getName() + " ";
            }
        }
        Text text = new Text(str);
        text.setFont(new Font(22));
        return text;
    }

    /**
     * Метод создает кнопку ОК(закрывает окно)
     * @return возвращает Button
     */
    private Button addButton(){
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        button.setPadding(new Insets(10));
        return button;
    }
}
