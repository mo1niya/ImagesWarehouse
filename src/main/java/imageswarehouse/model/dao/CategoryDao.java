package imageswarehouse.model.dao;

import imageswarehouse.model.entity.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements CrudDao<Category> {
    private JdbcTemplate template;
    //language=SQL
    private final String SQL_SELECT_ALL = "select distinct name from imgwar_categories";
    //language=SQL
    private final String SQL_SELECT_BY_TITLE = "Select * from imgwar_categories where name=?";
    //language=SQL
    private final String SQL_SELECT_OBJ = "Select * from imgwar_categories where name=? and imageid=?";
    //language=SQL
    private final String SQL_SELECT_BY_IMG = "select distinct name from imgwar_categories\n" +
            "    left join imgwar_images on imgwar_categories.imageid = imgwar_images.id\n" +
            "where title=?";
    //language=SQL
    private final String SQL_INSERT = "insert into imgwar_categories (name, imageid) values (?, ?)";

    private RowMapper<Category> myImageRowMapper
            = (ResultSet resultSet, int i) -> {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        Integer imageid = resultSet.getInt("imageid");
        Category category = new Category(id, name, imageid);
        return category;
    };

    private RowMapper<Category> myImageRowMapperOnlyName
            = (ResultSet resultSet, int i) -> {
        String name = resultSet.getString("name");
        Category category = new Category(name);
        return category;
    };

    public CategoryDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Category> find(String name) {
        return template.query(SQL_SELECT_BY_TITLE, myImageRowMapper, name);
    }
    @Override
    public List<Category> findByObg(Category category) {
        return template.query(SQL_SELECT_OBJ, myImageRowMapper, category.getName(), category.getImageId());
    }
    public List<Category> findByImg(String name) {
        return template.query(SQL_SELECT_BY_IMG, myImageRowMapperOnlyName, name);
    }
    @Override
    public List<Category> findAll() {
        List<Category> categories;
        try {
            categories = template.query(SQL_SELECT_ALL, myImageRowMapperOnlyName);
        } catch (Exception e){
            categories = new ArrayList<>();
        }
        return categories;
    }

    @Override
    public int save(Category model) {
        int ret;
        Object[] params = new Object[]{model.getName(), model.getImageId()};
        int[] types = new int[]{Types.VARCHAR, Types.INTEGER};
        ret = template.update(SQL_INSERT, params, types);
        return ret;
    }
    @Override
    public void update(Category model) {

    }
    @Override
    public void delete(Integer Id) {

    }
}
