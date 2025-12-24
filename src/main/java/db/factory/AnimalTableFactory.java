package db.factory;

import db.dao.AnimalsTable;
import db.dao.IAnimalTable;
import db.dao.mock.MockAnimalTable;

public class AnimalTableFactory {
    public IAnimalTable getProd() {
        return new AnimalsTable();
    }

    public IAnimalTable getMock() {
        return new MockAnimalTable();
    }

    // Метод для выбора реализации в зависимости от параметра
    public IAnimalTable getTable(boolean useMock) {
        return useMock ? getMock() : getProd();
    }
}