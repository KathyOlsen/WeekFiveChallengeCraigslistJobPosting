package com.example.demo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postedDate;

    @NotNull
    @Size(min=4, max=20)
    private String title;

    @NotNull
    @Size(min=5,max=50)
    private String description;

    @NotNull
    @Size(min=3, max=15)
    private String author;

    @NotNull
    @Size(min=3,max=3)
    private int phoneAreaCode;

    @NotNull
    @Size(min=3,max=3)
    private int phonePart1;

    @NotNull
    @Size(min=4,max=4)
    private int phonePart2;

    private String phone;

    public Job() {
    }

    public Job(Date postedDate,
               @NotNull @Size(min = 4, max = 20) String title,
               @NotNull @Size(min = 5, max = 50) String description,
               @NotNull @Size(min = 3, max = 15) String author,
               @NotNull @Size(min = 3, max = 3) int phoneAreaCode,
               @NotNull @Size(min = 3, max = 3) int phonePart1,
               @NotNull @Size(min = 4, max = 4) int phonePart2) {
        this.postedDate = postedDate;
        this.title = title;
        this.description = description;
        this.author = author;
        this.phoneAreaCode = phoneAreaCode;
        this.phonePart1 = phonePart1;
        this.phonePart2 = phonePart2;
        this.phone = phoneAreaCode + "-" + phonePart1 + "-" + phonePart2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPhoneAreaCode() {
        return phoneAreaCode;
    }

    public void setPhoneAreaCode(int phoneAreaCode) {
        this.phoneAreaCode = phoneAreaCode;
    }

    public int getPhonePart1() {
        return phonePart1;
    }

    public void setPhonePart1(int phonePart1) {
        this.phonePart1 = phonePart1;
    }

    public int getPhonePart2() {
        return phonePart2;
    }

    public void setPhonePart2(int phonePart2) {
        this.phonePart2 = phonePart2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
