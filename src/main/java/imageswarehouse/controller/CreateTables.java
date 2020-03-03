package imageswarehouse.controller;

import imageswarehouse.view.ViewInf;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class CreateTables {
    private JdbcTemplate template;
    //language=SQL
    private final String SQL_CREATE_TABLE = "create table imgwar_images (id serial primary key not null,\n" +
            "  title varchar(50) NOT NULL, sizeimg integer,\n" +
            "  pathimg varchar(500) not null, bytearr bytea not null,\n" +
            "  dateimg varchar(10));" +
            "create table imgwar_categories (id serial primary key not null,\n" +
            "    name varchar(100) not null, imageid integer not null REFERENCES imgwar_images(id));";

    public CreateTables(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    public void createT(){
        try {
            int i = template.update(SQL_CREATE_TABLE);
            if (i != 0){
                new ViewInf("Таблицы созданы");
            }
        } catch (BadSqlGrammarException e){
            new ViewInf("Таблицы уже созданы");
        }

    }
}
