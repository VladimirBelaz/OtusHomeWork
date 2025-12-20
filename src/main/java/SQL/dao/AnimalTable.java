package SQL.dao;

import SQL.dto.Animal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AnimalTable extends  AbsTable implements  IAnimalTable {
    public AnimalTable() {
        super("animal");
        columns.put("id", "bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY");
        columns.put("name", "varchar(15)");
        create();
    }

    public List<Animal> findAll(){
        List<Animal> animals = new ArrayList<>();
        try(ResultSet r = connectionManager.executeQuery("SELECT * FROM animals")) {
            while(r.next()) {
                animals.add(new Animal(r.getInt("Id"),
                        r.getString("Name")));
            }
        } catch (Exception e) {

        }
        return animals;
    }
}

