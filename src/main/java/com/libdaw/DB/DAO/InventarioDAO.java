package com.libdaw.DB.DAO;

import com.libdaw.DB.DBConnector;
import com.libdaw.models.Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventarioDAO {
    public ArrayList<Inventario> GetAllInventarios() throws SQLException {
        ArrayList<Inventario> listaInventario = new ArrayList<>();

        String query = "SELECT * FROM inventarios";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            Inventario itemInventario = new Inventario();
            itemInventario.setIdInventario(rs.getInt("idInventario"));
            itemInventario.setFecha(rs.getString("fecha"));
            itemInventario.setIdLibro(rs.getInt("idLibro"));
            itemInventario.setTitulo(rs.getString("titulo"));
            itemInventario.setEntrada(rs.getInt("entrada"));
            itemInventario.setSalida(rs.getInt("salida"));
            itemInventario.setStock(rs.getInt("stock"));

            listaInventario.add(itemInventario);
        }
        return listaInventario;
    }

    //
    public Inventario GetInventarioById(int id) throws SQLException {
        Inventario itemInventario = null;
        String query = "SELECT idInventario, fecha, idLibro, titulo, entrada, salida, stock FROM inventarios WHERE idInventario = ?";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            itemInventario = new Inventario();
            itemInventario.setIdInventario(rs.getInt("idInventario"));
            itemInventario.setFecha(rs.getString("fecha"));
            itemInventario.setIdLibro(rs.getInt("idLibro"));
            itemInventario.setTitulo(rs.getString("titulo"));
            itemInventario.setEntrada(rs.getInt("entrada"));
            itemInventario.setSalida(rs.getInt("salida"));
            itemInventario.setStock(rs.getInt("stock"));
        }
        return itemInventario;
    }

    //
    public boolean AddInventario(Inventario inventario) throws SQLException {
        String query = "INSERT INTO inventarios (fecha, idLibro, titulo, entrada, salida, stock) VALUES (?, ?, ?, ?, ?, ?)";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, inventario.getFecha());
        ps.setInt(2, inventario.getIdLibro());
        ps.setString(3, inventario.getTitulo());
        ps.setInt(4, inventario.getEntrada());
        ps.setInt(5, inventario.getSalida());
        ps.setInt(6, inventario.getStock());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    //
    public boolean UpdateInventario(Inventario inventario) throws SQLException {
        String query = "UPDATE inventarios SET fecha = ?, idLibro = ?, titulo = ?, entrada = ?, salida = ?, stock = ? WHERE idInventario = ?";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);

        ps.setString(1, inventario.getFecha());
        ps.setInt(2, inventario.getIdLibro());
        ps.setString(3, inventario.getTitulo());
        ps.setInt(4, inventario.getEntrada());
        ps.setInt(5, inventario.getSalida());
        ps.setInt(6, inventario.getStock());
        ps.setInt(7, inventario.getIdInventario());

        int rows = ps.executeUpdate();
        return rows > 0;
    }

    //
    public boolean DeleteInventario(int id) throws SQLException {
        String query = "DELETE FROM inventarios WHERE idLibro = ?";
        String query2 = "DELETE FROM inventarios WHERE idInventario = ?";

        Connection conn = DBConnector.GetInstance().GetConnection();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);

        ps.executeUpdate();

        PreparedStatement ps2 = conn.prepareStatement(query2);
        ps2.setInt(1, id);
        int rows = ps2.executeUpdate();
        return rows > 0;
    }

}
