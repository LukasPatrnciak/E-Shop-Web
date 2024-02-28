package sk.lukaspatrnciak.eshopweb.product.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepositoryInterface extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Product findProductById(Long id);

}