package sk.lukaspatrnciak.eshopweb.shoppinglist.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepositoryInterface extends JpaRepository<ShoppingList, Long> {
}