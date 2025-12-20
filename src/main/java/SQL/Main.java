package SQL;
import SQL.dto.Animal;
import SQL.factory.AnimalTableFactory;
import SQL.service.AnimalService;
import SQL.utils.ConnectionManager;

import java.util.List;

public class Main {

    static AnimalService animalService;

    public static void main(String[] args){
        animalService = new AnimalService(new AnimalTableFactory().getProd());
        List<Animal> animals = animalService.findAll();

        ConnectionManager.getInstance().close();
        System.out.println(animals);
    }
    //       Main (Клиент)
    //              |
    //      AnimalTableFactory
    //        /              \
    //     getProd()       getMock()
    //        |                |
    //    AnimalTable     MockAnimalTable
    //   (реальная БД)    (тестовые данные)
    //        |
    //    AbsTable
    //        |
    //ConnectionManager
    //        |
    //   База данных
}
