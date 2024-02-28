package sk.lukaspatrnciak.eshopweb.itemcart.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCartRepository extends JpaRepository<ItemCart, Long> {

    ItemCart findItemCartById(Long id);
}