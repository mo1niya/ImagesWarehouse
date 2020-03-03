package imageswarehouse.controller;

import imageswarehouse.model.dao.CategoryDao;
import imageswarehouse.model.dao.MyImagesDao;
import imageswarehouse.model.entity.Category;
import imageswarehouse.model.entity.MyImage;
import imageswarehouse.view.ViewSelect;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.common.ImageMetadata;
import org.apache.commons.imaging.formats.jpeg.JpegImageMetadata;
import org.apache.commons.imaging.formats.tiff.TiffField;
import org.apache.commons.imaging.formats.tiff.constants.ExifTagConstants;

import javax.imageio.ImageIO;
import javax.sql.DataSource;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class LoadFile {
    private MyImagesDao myImagesDao;
    DataSource dataSource;
    public LoadFile() {
        dataSource = new GetDataSource().getDataSource();
        myImagesDao = new MyImagesDao(dataSource);
    }

    public MyImage saveMyImage(File f, List<Category> categoryList){
        MyImage myImage = new MyImage(f.getName(), (int) (f.length()/1024), getBytArr(f),
                f.getAbsolutePath(), getDateImg(f), categoryList);
        List<MyImage> myImageList = myImagesDao.findByObg(myImage);
        int lastId;
        MyImage myImageReturn = new MyImage();
        if (myImageList.size() == 0){
            lastId = myImagesDao.save(myImage);
            if (lastId != 0){
                if (categoryList.size() != 0) {
                    CategoryDao categoryDao = new CategoryDao(dataSource);
                    for (Category category : categoryList) {
                        category.setImageId(lastId);
                        categoryDao.save(category);
                    }
                }
                myImageReturn =  myImage;
            }
        } else {
            MyImage imageDB = myImageList.get(0);
            if (categoryList != null && categoryList.size() != 0){
                CategoryDao categoryDao = new CategoryDao(dataSource);
                for(Category category: categoryList){
                    category.setImageId(imageDB.getId());
                    List<Category> list = categoryDao.findByObg(category);
                    if (list == null || list.size() == 0){
                        categoryDao.save(category);
                    }
                }
            }
            myImageReturn = imageDB;
        }
        new ViewSelect().view(myImage, myImageList.get(0));
        return myImageReturn;
    }

    private String getDateImg(File f){
        String dateImg = "";
        try {
            ImageMetadata metadata = Imaging.getMetadata(f);
            JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
            TiffField field = jpegMetadata.findEXIFValue(ExifTagConstants.EXIF_TAG_DATE_TIME_DIGITIZED);
            dateImg = field.getValueDescription().substring(1, 11);
        } catch (Exception e){
            e.printStackTrace();
        }
        return dateImg;
    }
    private byte[] getBytArr(File f){
        byte byteArr[] = {};
        try {
            BufferedImage originalImage = ImageIO.read(f);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(originalImage,"jpg", baos);
            baos.flush();
            byteArr = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArr;
    }
}
