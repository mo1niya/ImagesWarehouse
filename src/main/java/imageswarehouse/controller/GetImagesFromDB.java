package imageswarehouse.controller;

import imageswarehouse.model.dao.MyImagesDao;
import imageswarehouse.model.entity.MyImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class GetImagesFromDB {
    public GetImagesFromDB() {
    }
    public ImageView imgAsImageView(MyImage myImage, double stageHi){
        try {
            Image im = new Image(new File(myImage.getPathImg()).toURI().toString());
            double wi = im.getWidth();
            double hi = im.getHeight();
            double newWi = wi/(hi/stageHi);
            ImageView imv = new ImageView(im);
            imv.setFitWidth(newWi);
            imv.setFitHeight(stageHi);
            return imv;
        } catch (Exception e){
            return new ImageView();
        }
    }

    public List<ImageView> asImageView(){
        List<MyImage> myImages = new MyImagesDao(new GetDataSource().getDataSource()).findAll();
        List<ImageView> fileList = new LinkedList<>();
        for (MyImage myImage: myImages) {
            Image im = new Image(new File(myImage.getPathImg()).toURI().toString());
            double wi = im.getWidth();
            double hi = im.getHeight();
            double newH = hi/(wi/300);
            ImageView imv = new ImageView(im);
            imv.setFitWidth(300);
            imv.setFitHeight(newH);
            imv.setOnMouseClicked(event -> {
//                new ViewBigImgAndTable(myImage).getStage();
            });
            fileList.add(imv);
        }
        return fileList;
    }

    public List<Image> asImageList(){
        List<MyImage> myImages = new MyImagesDao(new GetDataSource().getDataSource()).findAll();
        List<Image> fileList = new LinkedList<>();
        for (MyImage myImage: myImages) {
            Image im = new Image(new File(myImage.getPathImg()).toURI().toString());
            fileList.add(im);
        }
        return fileList;
    }
}
