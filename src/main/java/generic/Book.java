package generic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class Book {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="isbn")
	private String ISBN;
	@Column(name="price")
	private String price;
	@Column(name="author_code")
	private String authorCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAuthorCode() {
		return authorCode;
	}

	public void setAuthorCode(String autorCode) {
		this.authorCode = autorCode;
	}

}
