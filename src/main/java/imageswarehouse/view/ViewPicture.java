package imageswarehouse.view;

import imageswarehouse.controller.GetImagesFromDB;
import imageswarehouse.model.entity.Category;
import imageswarehouse.model.entity.MyImage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewPicture {
    //Поле vBox
    private VBox vBox;

    /**
     * Конструктор - создание нового объекта
     * @see ViewPicture#ViewPicture(MyImage myImage, double hightImg, double wStage)
     */
    public ViewPicture(MyImage myImage, double hightImg, double wStage){
        ImageView imageView = new GetImagesFromDB().imgAsImageView(myImage, hightImg);
        List<Category> categories = myImage.getCategory();
        String strCat = new String();
        for (Category c: categories){
            strCat = strCat + c.getName() + " ";
        }
        Text text = new Text(strCat);
        text.setFont(Font.font(20));
        vBox = new VBox(imageView, text);
        vBox.setMinWidth(wStage-450);
    }

    /**
     * Функция получения значения поля {@link ViewPicture#ViewPicture}
     * @return возвращает vBox(котенер с картинкой)
     */
    public VBox getVBox() {
        return vBox;
    }
}
