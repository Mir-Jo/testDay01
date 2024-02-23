package TeamProject1.LibrarySystem;

public class BookInfo {
		String author;
		String publisher;
		int price;
		
	public BookInfo(String author, String publisher, int price) {
		this.author = author;
		this.publisher = publisher;
		this.price = price;
	}
	public String getAuthor() {return author;}
	public void setAuthor(String author) {this.author = author;}
//=====================================================================================
	public String getPublisher() {return publisher;}
	public void setPublisher(String publisher) {this.publisher = publisher;}
//=====================================================================================
	public int getPrice() {return price;}
	public void setPrice(int price) {this.price = price;}
}
