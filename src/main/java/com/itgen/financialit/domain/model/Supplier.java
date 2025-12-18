package com.itgen.financialit.domain.model;

public class Supplier {
    private Long id;
    private String name;
    private String document;
    private String email;

    public Supplier(String name, String document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
    }

    

    public Supplier(Long id) {
        this.id = id;
    }


    public Supplier(){}



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
