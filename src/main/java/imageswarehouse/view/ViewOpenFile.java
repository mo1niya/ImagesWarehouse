package imageswarehouse.view;

import imageswarehouse.App;
import imageswarehouse.model.entity.MyImage;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewOpenFile {
    //Поле file(выбранный файл)
    private File file;

    /**
     * Функция получения значения поля {@link ViewOpenFile#ViewOpenFile}
     * @return возвращает file(выбранный файл)
     */
    public File getFile() {
        return file;
    }

    /**
     * Конструктор - создание нового объекта
     * @see ViewOpenFile#ViewOpenFile()
     */
    public ViewOpenFile() {
        viewFileChooser();
    }

    /**
     * Метод открывает диалог выбора файла
     */
    public void viewFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"));
        file = fileChooser.showOpenDialog(App.javaFXC);//Указываем текущую сцену CodeNote.mainStage
    }
}
