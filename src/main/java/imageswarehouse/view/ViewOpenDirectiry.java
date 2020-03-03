package imageswarehouse.view;

import imageswarehouse.App;
import javafx.stage.DirectoryChooser;

import java.io.File;

/**
 * @autor Перепелицина Кристина
 * @version 1.0
 */
public class ViewOpenDirectiry {
    //Поле file(выбранный каталог)
    private File file;

    /**
     * Функция получения значения поля {@link ViewOpenDirectiry#ViewOpenDirectiry}
     * @return возвращает file(выбранный каталог)
     */
    public File getFile() {
        return file;
    }

    /**
     * Конструктор - создание нового объекта
     * @see ViewOpenDirectiry#ViewOpenDirectiry()
     */
    public ViewOpenDirectiry() {
        viewFileChooser();
    }

    /**
     * Метод открывает диалог выбора каталога
     */
    public void viewFileChooser(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open directory");
        file = directoryChooser.showDialog(App.javaFXC);//Указываем текущую сцену CodeNote.mainStage
    }
}
