package com.example.tacos.data;

import com.example.tacos.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
By annotating JdbcIngredientRepository with @Repository,
you declare that it should be automatically discovered by Spring component
scanning and instantiated as a bean in the Spring application context.
When Spring creates the JdbcIngredientRepository bean, it injects it with Jdbc-
Template via the @Autowired annotated construction. The constructor assigns
JdbcTemplate to an instance variable that will be used in other methods to query and
insert into the database.
 */
@Repository
public class JdbcIngredientRepository implements IngredientRepository {

    private JdbcTemplate jdbc;

    @Autowired
    public JdbcIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
        return new Ingredient(
                rs.getString("id"),
                rs.getString("name"),
                Ingredient.Type.valueOf(rs.getString("type")));
    }

    @Override
    public Iterable<Ingredient> findAll() {
        /*
        The query() method accepts the SQL for the query as well as
        an implementation of Spring’s RowMapper for the purpose
        of mapping each row in the result set to an object.
         */
        return jdbc.query("select id, name, type from Ingredient",
                this::mapRowToIngredient);
    }

    @Override
    public Ingredient findOne(String id) {
        /*
        queryForObject() works much like query() except that it returns
        a single object instead of a List of objects.
         */
        return jdbc.queryForObject(
                "select id, name, type from Ingredient where id=?",
                this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        /*
        JdbcTemplate’s update() method can be used for any query that writes
        or updates data in the database
         */
        jdbc.update(
                "insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.getId(),
                ingredient.getName(),
                ingredient.getType().toString());
        return ingredient;
    }
}