package sk.lukaspatrnciak.eshopweb.product.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.lukaspatrnciak.eshopweb.exeption.*;
import sk.lukaspatrnciak.eshopweb.product.data.ProductAmount;
import sk.lukaspatrnciak.eshopweb.product.logic.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest body) throws NotFoundException {
        return  new ResponseEntity<>(new ProductResponse(this.productService.createProduct(body)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable("id") Long id) throws NotFoundException{
        return new ProductResponse(this.productService.getById(id));
    }
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable("id") Long id, @RequestBody ProductUpdate body) throws NotFoundException {
        return new ProductResponse(this.productService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
        this.productService.delete(id);
    }

    @GetMapping("/{id}/amount")
    public ProductAmount getAmount(@PathVariable("id") Long id) throws NotFoundException {
        return new ProductAmount(this.productService.getAmount(id));
    }

    @PostMapping("/{id}/amount")
    public ProductAmount addAmount(@PathVariable("id") Long id, @RequestBody ProductAmount body) throws NotFoundException {
        return new ProductAmount(this.productService.addAmount(id, body.getAmount()));
    }
}