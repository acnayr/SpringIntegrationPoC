package com.poc.springintegration;

import java.io.Serializable;

public class Person implements Serializable {

    private String nombre;
    private int edad;

    private String dominio;

    public Person() {
    }

    public String getNombre() {
        return nombre;
    }

    public Person setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public int getEdad() {
        return edad;
    }

    public Person setEdad(int edad) {
        this.edad = edad;
        return this;
    }

    public String getDominio() {
        return dominio;
    }

    public Person setDominio(String dominio) {
        this.dominio = dominio;
        return this;
    }
}
