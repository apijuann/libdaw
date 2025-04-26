package com.libdaw.models;

public class Inventario {
    private int idInventario;
    private String fecha;
    private int idLibro;
    private String titulo;
    private int entrada;
    private int salida;
    private int stock;

    public Inventario(int idInventario, String fecha, int idLibro, String titulo, int entrada, int salida, int stock) {
        this.idInventario = idInventario;
        this.fecha = fecha;
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.entrada = entrada;
        this.salida = salida;
        this.stock = stock;
    }

    public Inventario() {
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getEntrada() {
        return entrada;
    }

    public void setEntrada(int entrada) {
        this.entrada = entrada;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
