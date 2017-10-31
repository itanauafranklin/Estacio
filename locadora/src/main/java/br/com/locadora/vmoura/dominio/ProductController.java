package br.com.locadora.vmoura.dominio;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "productController")
public class ProductController {
	
	@Autowired
    private ProductRepository productRepository;

    private Product product = new Product("Itanauã", new BigDecimal("1"));
    
    public Product getProduct() {
        return product;
    }
    
    public String save() {
        productRepository.save(product);
        product = new Product();
        return "/product-list.xhtml?faces-redirect=true";
    }

}
