package com.libdaw.controllers;

import com.libdaw.DB.DAO.InventarioDAO;
import com.libdaw.models.Inventario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "InventarioController", urlPatterns = {"/inventario"})
public class InventarioController extends HttpServlet {
    private InventarioDAO inventarioDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        inventarioDAO = new InventarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                editInventario(request, response);
                break;
            case "delete":
                deleteInventario(request, response);
                break;
            case "list":
            default:
                listInventarios(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveInventario(request, response);
        }
    }

    private void listInventarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Inventario> inventarios = null;
        try {
            inventarios = inventarioDAO.GetAllInventarios();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaInventarios", inventarios);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/list-inventario.jsp");
        dispatcher.forward(request, response);
    }

    //
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/form-inventario.jsp");
        dispatcher.forward(request, response);
    }

    private void editInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idInventario = Integer.parseInt(request.getParameter("id"));
        Inventario inventario = null;
        try {
            inventario = inventarioDAO.GetInventarioById(idInventario);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("inventario", inventario);
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/form-inventario.jsp");
        dispatcher.forward(request, response);
    }

    private void saveInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idInventario");
        String fecha = request.getParameter("fecha");
        int idLibro = Integer.parseInt(request.getParameter("idLibro"));
        String titulo = request.getParameter("titulo");
        int entrada = Integer.parseInt(request.getParameter("entrada"));
        int salida = Integer.parseInt(request.getParameter("salida"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        int idInv = (idParam == null || idParam.isEmpty()) ? 0 : Integer.parseInt(idParam);

        Inventario inv = new Inventario();
        inv.setFecha(fecha);
        inv.setIdLibro(idLibro);
        inv.setTitulo(titulo);
        inv.setEntrada(entrada);
        inv.setSalida(salida);
        inv.setStock(stock);

        try{
            if (idInv == 0) {
                inventarioDAO.AddInventario(inv);
            } else {
                inv.setIdInventario(idInv);
                inventarioDAO.UpdateInventario(inv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("inventario?action=list");
    }

    private void deleteInventario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idInventario = Integer.parseInt(request.getParameter("id"));
        try {
            inventarioDAO.DeleteInventario(idInventario);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("inventario?action=list");
    }
}
