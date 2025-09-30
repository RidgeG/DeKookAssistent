package com.ridge.geervliet.novi.dekookassistent.model;


import jakarta.persistence.*;

@Entity
@Table(name = "uploads")
public class Upload {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String url;
    private String publicId;
    private String resourceType;
    private String format;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String recourceType) {
        this.resourceType = recourceType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
