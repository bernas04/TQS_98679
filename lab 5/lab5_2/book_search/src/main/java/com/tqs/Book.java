package com.tqs;


import java.util.Date;
 
public class Book {
	private final String title;
	private String category;
	private String author="";
	private Date published;
    private int price;

    public Book(String title, int price){
        this.title=title;
        this.price=price;
    }
 

    public Book(String title, String author, Date published) {
        this.title = title;
        this.author = author;
        this.published = published;
    }

    public Book(String category, String title ,String author, Date published) {
        this.category = category;
        this.title = title;
        this.author = author;
        this.published = published;
    }


    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getTitle() {
        return this.title;
    }


    public String getAuthor() {
        return this.author;
    }


    public Date getPublished() {
        return this.published;
    }

    public String getCategory() {
        return this.category;
    }


    @Override
    public String toString() {
        return getTitle() +
            " / " + getAuthor() +
            " / " + getPublished();
    }

	
}
