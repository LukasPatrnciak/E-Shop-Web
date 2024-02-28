package sk.lukaspatrnciak.eshopweb.cart.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepositoryInterface extends JpaRepository<Cart, Long> {
    Cart findCartById(Long id);

    @Override
    List<Cart> findAll();
}
