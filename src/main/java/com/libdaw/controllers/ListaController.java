package com.libdaw.controllers;

import com.libdaw.DB.DAO.LibroDAO;
import com.libdaw.models.Libro;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ListaController", urlPatterns = {"/lista"})
public class ListaController extends HttpServlet {
    private LibroDAO libroDao;

    @Override
    public void init() throws ServletException {
        super.init();
        libroDao = new LibroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list":
            default:
                listLibros(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "save";

        if ("save".equals(action)) {
            saveLibro(request, response);
        }
    }

    private void listLibros(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Libro> libros = null;
        try {
            libros = libroDao.GetAllLibros();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("listaLibros", libros);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/lista-index.jsp");
        dispatcher.forward(request, response);
    }

    //
    private void saveLibro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("idLibro");
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        String publicacion = request.getParameter("publicacion");
        String idioma = request.getParameter("idioma");
        int paginas = Integer.valueOf(request.getParameter("paginas"));
        String categoria = request.getParameter("categoria");
        double precio = Double.parseDouble(request.getParameter("precio"));
        String isbn = request.getParameter("isbn");

        int idLib = (idParam == null || idParam.isEmpty()) ? 0 : Integer.parseInt(idParam);

        Libro lib = new Libro();
        lib.setTitulo(titulo);
        lib.setAutor(autor);
        lib.setPublicacion(publicacion);
        lib.setIdioma(idioma);
        lib.setPaginas(paginas);
        lib.setCategoria(categoria);
        lib.setPrecio(precio);
        lib.setIsbn(isbn);

        try{
            if (idLib == 0) {
                libroDao.AddLibro(lib);
            } else {
                lib.setIdLibro(idLib);
                libroDao.UpdateLibro(lib);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("libros?action=list");
    }

}
