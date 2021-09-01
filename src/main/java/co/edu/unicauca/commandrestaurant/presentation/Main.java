
package co.edu.unicauca.commandrestaurant.presentation;

import co.edu.unicauca.commandrestaurant.domain.Command;
import co.edu.unicauca.commandrestaurant.domain.CreateCommand;
import co.edu.unicauca.commandrestaurant.domain.DeleteByIdCommand;
import co.edu.unicauca.commandrestaurant.domain.FindAllCommand;
import co.edu.unicauca.commandrestaurant.domain.Food;
import co.edu.unicauca.commandrestaurant.domain.FoodTypeEnum;
import co.edu.unicauca.commandrestaurant.domain.Invoker;

/**
 *
 * @author libardo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Crea el invocador
        Invoker invoker = new Invoker();
        //Crea la comida
        Food food = new Food(5, "Limonada cerezada", FoodTypeEnum.JUGO);
        //Crea el comando crear
        Command cmd = new CreateCommand(food);
        Command delCmd = new DeleteByIdCommand(food);
        
        Command findAllCmd = new FindAllCommand();
        invoker.setCommand(cmd);
        invoker.execute();
        invoker.setCommand(findAllCmd);
        invoker.execute();
        invoker.setCommand(delCmd);
        invoker.execute();
        
        invoker.undo();
        
        invoker.setCommand(findAllCmd);
        invoker.execute();
    }
    
}
