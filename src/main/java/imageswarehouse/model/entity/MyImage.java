package imageswarehouse.model.entity;

import java.util.List;


public class MyImage {
    private Integer id;

    private String title;
    private int sizeImg;
    private byte[] byteArr;
    private String pathImg;
    private String dateImg;
    private List<Category> category;

    public MyImage(Integer id, String title, int sizeImg, byte[] byteArr, String pathImg, String dateImg, List<Category> category) {
        this.id = id;
        this.title = title;
        this.sizeImg = sizeImg;
        this.byteArr = byteArr;
        this.pathImg = pathImg;
        this.dateImg = dateImg;
        this.category = category;
    }
    public MyImage(String title, int sizeImg, byte[] byteArr, String pathImg, String dateImg, List<Category> category) {
        this.title = title;
        this.sizeImg = sizeImg;
        this.byteArr = byteArr;
        this.pathImg = pathImg;
        this.dateImg = dateImg;
        this.category = category;
    }
    public MyImage(String title, int sizeImg, byte[] byteArr, String pathImg, String dateImg) {
        this.title = title;
        this.sizeImg = sizeImg;
        this.byteArr = byteArr;
        this.pathImg = pathImg;
        this.dateImg = dateImg;
    }
    public MyImage(Integer id, String title, int sizeImg, byte[] byteArr, String pathImg, String dateImg) {
        this.id = id;
        this.title = title;
        this.sizeImg = sizeImg;
        this.byteArr = byteArr;
        this.pathImg = pathImg;
        this.dateImg = dateImg;
    }
    public MyImage() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getSizeImg() {
        return sizeImg;
    }
    public void setSizeImg(int sizeImg) {
        this.sizeImg = sizeImg;
    }
    public byte[] getByteArr() {
        return byteArr;
    }
    public void setByteArr(byte[] byteArr) {
        this.byteArr = byteArr;
    }
    public String getPathImg() {
        return pathImg;
    }
    public void setPathImg(String pathImg) {
        this.pathImg = pathImg;
    }
    public String getDateImg() {
        return dateImg;
    }
    public void setDateImg(String dateImg) {
        this.dateImg = dateImg;
    }
    public List<Category> getCategory() {
        return category;
    }
    public void setCategory(List<Category> category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Image{" + "id='" + id +
                " ,title='" + title + '\'' +
                ", sizeImg=" + sizeImg +
                ", pathImg=" + pathImg +
                ", dateImg=" + dateImg +
                ", category=" + category.toString() +
                '}';
    }
}
