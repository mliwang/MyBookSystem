package cn.mybookmanager.model;

public class Bookinfo implements java.io.Serializable {
    private String bookid;

    private String bookname;

    private String author;

    private Integer edition;

    private String publishunit;

    private String isbn;

    private Integer inventory;

    private String supplier;

    public Bookinfo() {
    }

    public Bookinfo(String bookid, String bookname, String author, Integer edition, String publishunit, String isbn, Integer inventory, String supplier) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.author = author;
        this.edition = edition;
        this.publishunit = publishunit;
        this.isbn = isbn;
        this.inventory = inventory;
        this.supplier = supplier;
    }

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid == null ? null : bookid.trim();
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname == null ? null : bookname.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    public String getPublishunit() {
        return publishunit;
    }

    public void setPublishunit(String publishunit) {
        this.publishunit = publishunit == null ? null : publishunit.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier == null ? null : supplier.trim();
    }
}