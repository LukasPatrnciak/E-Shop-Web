package sk.lukaspatrnciak.eshopweb.shoppinglist.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingList;
import sk.lukaspatrnciak.eshopweb.shoppinglist.data.ShoppingListRepositoryInterface;

@Service
public class ShoppingListService implements ShoppingListServiceInterface {

    @Autowired
    ShoppingListRepositoryInterface shoppingListRepository;

    public ShoppingListService(ShoppingListRepositoryInterface shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    @Override
    public ShoppingList createShoppingList() {
        return shoppingListRepository.save(new ShoppingList());
    }
}
