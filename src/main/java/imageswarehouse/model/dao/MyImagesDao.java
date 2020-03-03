package imageswarehouse.model.dao;

import imageswarehouse.model.entity.Category;
import imageswarehouse.model.entity.MyImage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyImagesDao implements CrudDao<MyImage> {
    private JdbcTemplate template;
    private Map<Integer, MyImage> imageHashMap = new HashMap<>();
    //language=SQL
    private final String SQL_SELECT_ALL = "Select * from imgwar_images";
    //language=SQL
    private final String SQL_SELECT_BY_TITLE = "Select * from imgwar_images where title=?";
    //language=SQL
    private final String SQL_SELECT_OBJ = "select imgwar_images.*, imgwar_categories.name as category from imgwar_images " +
            "left join imgwar_categories\n" +
            "on imgwar_images.id = imgwar_categories.imageid where imgwar_images.bytearr = ?";
    //language=SQL
    private final String SQL_SELECT_BY_CATEGORY = "select imgwar_images.*, imgwar_categories.name as category from imgwar_images\n" +
            "left join imgwar_categories on imgwar_images.id = imgwar_categories.imageid  where imgwar_categories.name=?";
    //language=SQL
    private final String SQL_INSERT_IMG = "insert into imgwar_images (title, sizeimg, bytearr, pathimg, dateimg) " +
            "values (?, ?, ?, ?, ?) returning id";
    //language=SQL
    private final String SQL_INSERT_CAT = "insert into imgwar_categories (name) values (?, (SELECT LAST_INSERT_ID()))";

    private RowMapper<MyImage> myImageRowMapper
            = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        Integer sizeimg = resultSet.getInt("sizeimg");
        byte[] byteArr = resultSet.getBytes("bytearr");
        String pathimg = resultSet.getString("pathimg");
        String dateImg = resultSet.getString("dateimg");
        MyImage myImage = new MyImage(id, title, sizeimg, byteArr, pathimg, dateImg);
        return myImage;
    };

    private RowMapper<MyImage> myImageCategoryRowMapper
            = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");

        if (!imageHashMap.containsKey(id)){
            String title = resultSet.getString("title");
            Integer sizeimg = resultSet.getInt("sizeimg");
            byte[] byteArr = resultSet.getBytes("bytearr");
            String pathimg = resultSet.getString("pathimg");
            String dateImg = resultSet.getString("dateimg");
            MyImage myImage = new MyImage(id, title, sizeimg, byteArr, pathimg, dateImg, new ArrayList<>());
            imageHashMap.put(id, myImage);
        }
        Category cat = new Category(resultSet.getString("category"), id);
        imageHashMap.get(id).getCategory().add(cat);

        return imageHashMap.get(id);
    };

    public MyImagesDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<MyImage> find(String name) {
        return template.query(SQL_SELECT_BY_TITLE, myImageRowMapper, name);
    }
    public List<MyImage> findByCategory(String category) {
        List<MyImage> images;
        try {
            images = template.query(SQL_SELECT_BY_CATEGORY, myImageCategoryRowMapper, category);
        } catch (Exception e){
            images = new ArrayList<>();
        }
        return images;
    }
    @Override
    public List<MyImage> findByObg(MyImage image) {
        return template.query(SQL_SELECT_OBJ, myImageCategoryRowMapper, image.getByteArr());
    }


    @Override
    public int save(MyImage model) {
        int ret;
        Object[] params = new Object[] { model.getTitle(), model.getSizeImg(), model.getByteArr(),
                model.getPathImg(), model.getDateImg() };
        ret = template.queryForObject(SQL_INSERT_IMG, params, Integer.class);
        return ret;
    }

    @Override
    public void update(MyImage model) {

    }

    @Override
    public void delete(Integer Id) {

    }

    @Override
    public List<MyImage> findAll() {
        return template.query(SQL_SELECT_ALL, myImageRowMapper);
    }
}