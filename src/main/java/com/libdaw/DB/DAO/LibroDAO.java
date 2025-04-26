package com.libdaw.DB.DAO;

import com.libdaw.DB.DBConnector;
import com.libdaw.models.Libro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibroDAO {
    public ArrayList<Libro> GetAllLibros() throws SQLException {
        ArrayList<Libro> listaLibros = new ArrayList<>();

        String query = "SELECT * FROM libros";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Libro itemLibro = new Libro();
            itemLibro.setIdLibro(rs.getInt("idLibro"));
            itemLibro.setTitulo(rs.getString("titulo"));
            itemLibro.setAutor(rs.getString("autor"));
            itemLibro.setPublicacion(rs.getString("publicacion"));
            itemLibro.setIdioma(rs.getString("idioma"));
            itemLibro.setPaginas(rs.getInt("paginas"));
            itemLibro.setCategoria(rs.getString("categoria"));
            itemLibro.setPrecio(rs.getDouble("precio"));
            itemLibro.setIsbn(rs.getString("isbn"));

            listaLibros.add(itemLibro);
        }
        return listaLibros;
    }

    //
    public Libro GetLibroById(int id) throws SQLException {
        Libro itemLibro = null;
        String query = "SELECT idLibro, titulo, autor, publicacion, idioma, paginas, categoria, precio, isbn FROM libros WHERE idLibro = ?";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            itemLibro = new Libro();
            itemLibro.setIdLibro(rs.getInt("idLibro"));
            itemLibro.setTitulo(rs.getString("titulo"));
            itemLibro.setAutor(rs.getString("autor"));
            itemLibro.setPublicacion(rs.getString("publicacion"));
            itemLibro.setIdioma(rs.getString("idioma"));
            itemLibro.setPaginas(rs.getInt("paginas"));
            itemLibro.setCategoria(rs.getString("categoria"));
            itemLibro.setPrecio(rs.getDouble("precio"));
            itemLibro.setIsbn(rs.getString("isbn"));
        }
        return itemLibro;
    }

    //
    public boolean AddLibro(Libro libro) throws SQLException {
        String query = "INSERT INTO libros (titulo, autor, publicacion, idioma, paginas, categoria, precio, isbn) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, libro.getTitulo());
        ps.setString(2, libro.getAutor());
        ps.setString(3, libro.getPublicacion());
        ps.setString(4, libro.getIdioma());
        ps.setInt(5, libro.getPaginas());
        ps.setString(6, libro.getCategoria());
        ps.setDouble(7, libro.getPrecio());
        ps.setString(8, libro.getIsbn());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    //
    public boolean UpdateLibro(Libro libro) throws SQLException {
        String query = "UPDATE libros SET titulo=?, autor=?, publicacion=?, idioma=?, paginas=?, categoria=?, precio=?, isbn=? WHERE idLibro=?";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, libro.getTitulo());
        ps.setString(2, libro.getAutor());
        ps.setString(3, libro.getPublicacion());
        ps.setString(4, libro.getIdioma());
        ps.setInt(5, libro.getPaginas());
        ps.setString(6, libro.getCategoria());
        ps.setDouble(7, libro.getPrecio());
        ps.setString(8, libro.getIsbn());
        ps.setInt(9, libro.getIdLibro());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    //
    public boolean DeleteLibro(int id) throws SQLException {
        String query = "DELETE FROM libros WHERE idLibro = ?";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        return rows > 0;
    }

}
