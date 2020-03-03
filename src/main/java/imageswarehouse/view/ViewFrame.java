package imageswarehouse.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewFrame extends Pane{
    //Поле StackPane
    private StackPane stackPane;

    /**
     * Конструктор - создание нового объекта
     * @see ViewFrame#ViewFrame(Object, String)
     */
    public ViewFrame(Object pane, String strCategory){
        stackPane = new StackPane();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new ViewMenu(strCategory).gethBox());
        VBox vLeft = new VBox(new Text("."));
        vLeft.setPadding(new Insets(10, 25, 10, 25));

        borderPane.setLeft(vLeft);
        borderPane.setCenter((Node) pane);
        stackPane.getChildren().addAll(new ViewBackground().getGroup(), borderPane);
        stackPane.setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Функция получения значения поля {@link ViewFrame#ViewFrame}
     * @return возвращает stackPane(контейнер с фоном и меню)
     */
    public StackPane getStackPane() {
        return stackPane;
    }
}
