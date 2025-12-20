package SQL.factory;

import SQL.dao.AnimalTable;
import SQL.dao.IAnimalTable;
import SQL.dao.mock.MockAnimalTable;

public class AnimalTableFactory {
    public IAnimalTable getProd() {
        return new AnimalTable();
    }

    public IAnimalTable getMock(){
        return new MockAnimalTable();
    }
}
