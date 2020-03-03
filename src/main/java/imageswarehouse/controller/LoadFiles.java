package imageswarehouse.controller;

import imageswarehouse.model.entity.Category;
import imageswarehouse.model.entity.MyImage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LoadFiles {
    private List<MyImage> myImageList = new ArrayList<>();
    private List<Category> catList = new ArrayList<>();
    private LoadFile loadFile;

    public LoadFiles() {
    }

    public void saveFile(File directory, List<Category> categoryList){
        catList = categoryList;
        loadFile = new LoadFile();
        myImageList = treeFiles(directory);
    }

    private List<MyImage> treeFiles(File file){
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                if (f.isFile()) {
                    loadFile.saveMyImage(f, catList);
                }
                treeFiles(f);
            }
        }
        return myImageList;
    }
}