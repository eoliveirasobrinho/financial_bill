package com.itgen.financialit.domain.model;

public class Supplier {
    private Long id;
    private final String name;
    private final String document;
    private final String email;

    public Supplier(String name, String document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }



    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                '}';
    }
}
