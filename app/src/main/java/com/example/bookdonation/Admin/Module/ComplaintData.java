package com.example.bookdonation.Admin.Module;

public class ComplaintData {

    public ComplaintData(){

    }

    private String UniqueKey,NameofStudent,MobileNumber,Email,City,NameofBook,BookGenre,BookImage;

    public ComplaintData(String uniqueKey, String nameofStudent, String mobileNumber, String email, String city, String nameofBook, String bookGenre, String bookImage) {
        UniqueKey = uniqueKey;
        NameofStudent = nameofStudent;
        MobileNumber = mobileNumber;
        Email = email;
        City = city;
        NameofBook = nameofBook;
        BookGenre = bookGenre;
        BookImage = bookImage;
    }

    public String getUniqueKey() {
        return UniqueKey;
    }

    public void setUniqueKey(String uniqueKey) {
        UniqueKey = uniqueKey;
    }

    public String getNameofStudent() {
        return NameofStudent;
    }

    public void setNameofStudent(String nameofStudent) {
        NameofStudent = nameofStudent;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getNameofBook() {
        return NameofBook;
    }

    public void setNameofBook(String nameofBook) {
        NameofBook = nameofBook;
    }

    public String getBookGenre() {
        return BookGenre;
    }

    public void setBookGenre(String bookGenre) {
        BookGenre = bookGenre;
    }

    public String getBookImage() {
        return BookImage;
    }

    public void setBookImage(String bookImage) {
        BookImage = bookImage;
    }
}
