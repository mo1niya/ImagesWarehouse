package imageswarehouse.view;

import imageswarehouse.App;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.GRAY;
import static javafx.scene.paint.Color.GREEN;

public class ViewBackground{
    private Group root;
    public Group getGroup() {
        return root;
    }

    public ViewBackground() {
        Text textTitle = new Text(40, 50, "Images Warehouse");
        textTitle.setFill(GREEN);
        textTitle.setFont(Font.font(20));
        root = new Group();
        root.getChildren().addAll(textTitle, drowLine(0, 20, 800, 20, GRAY),
                drowLine(0, 30, 600, 30, GRAY),
                drowLine(20, 0, 20, 500, GRAY),
                drowLine(30, 0, 30, 300, GRAY));
    }

    private Line drowLine(double startX, double startY, double endX, double endY, Color color){
        Line line = new Line(startX, startY, endX, endY);
        line.setFill(color);
        return line;
    }
}
