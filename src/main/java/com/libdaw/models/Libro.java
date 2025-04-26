package com.libdaw.models;

public class Libro {
    private int idLibro;
    private String titulo;
    private String autor;
    private String publicacion;
    private String idioma;
    private int paginas;
    private String categoria;
    private double precio;
    private String isbn;

    public Libro(int idLibro, String titulo, String autor, String publicacion, String idioma, int paginas, String categoria, double precio, String isbn) {
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.publicacion = publicacion;
        this.idioma = idioma;
        this.paginas = paginas;
        this.categoria = categoria;
        this.precio = precio;
        this.isbn = isbn;
    }

    public Libro() {
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getPaginas() {
        return paginas;
    }

    public void setPaginas(int paginas) {
        this.paginas = paginas;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
