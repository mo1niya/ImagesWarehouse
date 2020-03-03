package imageswarehouse.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewInf {
    //Поле Stage(новое окно)
    private Stage stageInf;
    //Поле String(текст сообщения)
    private String infStr;
    /**
     * Конструктор - создание нового объекта
     * @see ViewInf#ViewInf(String)
     */
    public ViewInf(String inf) {
        stageInf = new Stage();
        infStr = inf;
        VBox vBox = addVBox();
        vBox.getChildren().addAll(addText(), addButton());
        stageInf.setScene(new Scene(vBox, 300, 150));
        stageInf.show();
    }
    /**
     * Метод создает кнопку, закрывающую окно
     * @return возвращает Button
     */
    private Button addButton(){
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stageInf.close();
            }
        });
        return button;
    }
    /**
     * Метод создает текст сообщения
     * @return возвращает Text
     */
    private Text addText(){
        Text text = new Text(infStr);
        text.setFont(Font.font(20));
        return text;
    }
    /**
     * Метод создает контейнер VBox
     * @return возвращает VBox
     */
    private VBox addVBox(){
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        return vBox;
    }
}
