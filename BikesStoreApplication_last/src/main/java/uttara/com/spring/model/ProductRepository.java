package uttara.com.spring.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uttara.com.spring.jpa.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, String>{

}
