package sk.lukaspatrnciak.eshopweb.product.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.lukaspatrnciak.eshopweb.exeption.*;
import sk.lukaspatrnciak.eshopweb.product.logic.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest body) throws NotFoundException {
        return  new ResponseEntity<>(new ProductResponse(this.productService.createProduct(body)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse getById(@PathVariable("id") Long id) throws NotFoundException{
        return new ProductResponse(this.productService.getById(id));
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductUpdate body) throws NotFoundException {
        return new ProductResponse(this.productService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
        this.productService.delete(id);
    }

    @GetMapping(value = "/{id}/amount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductAmount getAmount(@PathVariable("id") Long id) throws NotFoundException {
        return new ProductAmount(this.productService.getAmount(id));
    }

    @PostMapping(value = "/{id}/amount/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductAmount addAmount(@PathVariable("id") Long id, @RequestBody ProductAmount body) throws NotFoundException {
        return new ProductAmount(this.productService.addAmount(id, body.getAmount()));
    }

    @PostMapping(value = "/{id}/amount/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeAmount(@PathVariable("id") Long id, @RequestBody ProductAmount amount) throws NotFoundException, IllegalOperationException {
        this.productService.removeAmount(id, amount.getAmount());
    }
}