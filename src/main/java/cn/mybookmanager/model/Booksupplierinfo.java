package cn.mybookmanager.model;

public class Booksupplierinfo {
    private Integer supplierid;

    private String nameofsupplier;

    private Integer supphone;

    public Integer getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(Integer supplierid) {
        this.supplierid = supplierid;
    }

    public String getNameofsupplier() {
        return nameofsupplier;
    }

    public void setNameofsupplier(String nameofsupplier) {
        this.nameofsupplier = nameofsupplier == null ? null : nameofsupplier.trim();
    }

    public Integer getSupphone() {
        return supphone;
    }

    public void setSupphone(Integer supphone) {
        this.supphone = supphone;
    }
}