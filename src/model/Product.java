package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private float memberreduction;

	private String name;

	private float price;

	private int quantityavailable;

	private String urlimage;

	//bi-directional many-to-one association to Brand
	@ManyToOne
	@JoinColumn(name="idbrand")
	private Brand brand;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="idcategory")
	private Category category;

	//bi-directional many-to-one association to Member
	@ManyToOne
	@JoinColumn(name="idmember")
	private Member member;

	//bi-directional many-to-one association to Productline
	@OneToMany(mappedBy="product")
	private List<Productline> productlines;

	public Product() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getMemberreduction() {
		return this.memberreduction;
	}

	public void setMemberreduction(float memberreduction) {
		this.memberreduction = memberreduction;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantityavailable() {
		return this.quantityavailable;
	}

	public void setQuantityavailable(int quantityavailable) {
		this.quantityavailable = quantityavailable;
	}

	public String getUrlimage() {
		return this.urlimage;
	}

	public void setUrlimage(String urlimage) {
		this.urlimage = urlimage;
	}

	public Brand getBrand() {
		return this.brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public List<Productline> getProductlines() {
		return this.productlines;
	}

	public void setProductlines(List<Productline> productlines) {
		this.productlines = productlines;
	}

	public Productline addProductline(Productline productline) {
		getProductlines().add(productline);
		productline.setProduct(this);

		return productline;
	}

	public Productline removeProductline(Productline productline) {
		getProductlines().remove(productline);
		productline.setProduct(null);

		return productline;
	}

}