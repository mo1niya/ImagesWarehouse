package imageswarehouse.model.entity;

import java.util.Objects;

public class Category {
    private Integer id;
    private String name;
    private int imageId;

    public Category() {
    }
    public Category(String name) {
        this.name = name;
    }
    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    public Category(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public Category(Integer id, String name, int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return imageId == category.imageId &&
                Objects.equals(id, category.id) &&
                name.equals(category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imageId);
    }
}
