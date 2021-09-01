/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.unicauca.commandrestaurant.domain;

import co.edu.unicauca.commandrestaurant.access.IFoodRepository;
import co.edu.unicauca.commandrestaurant.access.RepositoryFactory;
import co.edu.unicauca.commandrestaurant.domain.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ASUS
 */
public class DeleteByIdCommand extends Command{

    private Food food;
    
    private Food foodPrevious;
    
    private FoodService service;

    public DeleteByIdCommand(Food food) {
        this.food = food;
        IFoodRepository repo = RepositoryFactory.getInstance().getRepository("default");
        service = new FoodService(repo);
        this.hasUndo = true;
    }
    
    @Override
    public void execute() {
        Logger logger= LoggerFactory.getLogger(FindAllCommand.class); 
        logger.info("Comando eliminar por id ejecutado. Se eliminó la comida " + food.toString());
        this.setFoodPrevious(food);
        service.delete(food.getId());
    }

    @Override
    public void undo() {
        Logger logger= LoggerFactory.getLogger(FindAllCommand.class); 
        logger.info("Comando eliminar desecho. Se grabó la comida " + foodPrevious.toString());
        service.create(foodPrevious);
    }

    public Food getFoodPrevious() {
        return foodPrevious;
    }

    public void setFoodPrevious(Food previousComponent) {
        this.foodPrevious = previousComponent;
    }
}
