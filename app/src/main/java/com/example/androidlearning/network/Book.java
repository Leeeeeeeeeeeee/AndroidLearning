package com.example.androidlearning.network;

public class Book {
    private String title;
    private String author;
    private String content;//属性名和json属性名一模一样

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Book(String title, String author, String content) {
        this.title = title;
        this.author = author;
        this.content = content;
    }

    //GSON要求一定要有无参构造方法
    public Book() {
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
