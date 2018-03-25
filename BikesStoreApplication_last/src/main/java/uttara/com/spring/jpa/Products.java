package uttara.com.spring.jpa;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(ignoreUnknown=true, value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Products {

	@Id
    @Column(unique=true, name = "id")
	private String id;
	
    @Column(name = "productname")
	private String productName;
	
    @Column(name = "productline")
	private String productLine;
	
    @Column(name = "productscale")
	private String productScale;
	
    @Column(name = "productvendor")
	private String productVendor;
	
    @Column(name = "productdescription")
	private String productDescription;
	
    @Column(name = "quantityinstock")
	private short quantityInStock;
	
	
    @Column(name = "buyprice")
	private BigDecimal buyPrice;
	
	
    @Column(name = "msrp")
	private BigDecimal msrp;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productname) {
		this.productName = productName;
	}
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getProductScale() {
		return productScale;
	}
	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public short getQuantityInStock() {
		return quantityInStock;
	}
	public void setQuantityInStock(short quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}
	public BigDecimal getMsrp() {
		return msrp;
	}
	public void setMsrp(BigDecimal msrp) {
		this.msrp = msrp;
	}
	
}
