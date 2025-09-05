package vn.codegym.module3final.Controller;

import vn.codegym.module3final.DAO.MatBangDAO;
import vn.codegym.module3final.Model.MatBangChoThue;
import vn.codegym.module3final.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/TComplex")
public class MatBangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatBangDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new MatBangDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "add":
                    showAddForm(req, resp);
                    break;
                case "delete":
                    deleteMatBang(req, resp);
                    break;
                default:
                    listMatBang(req, resp);
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "add":
                    insertMatBang(req, resp);
                    break;
                default:
                    listMatBang(req, resp);
                    break;
            }
        }  catch (Exception e) {
            e.printStackTrace();
            req.getSession().setAttribute("errorMessage", "Lỗi database: " + e.getMessage());
        }

    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("add.jsp");
        dispatcher.forward(request, response);
    }

    private void insertMatBang(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.setCharacterEncoding("UTF-8");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Lấy dữ liệu từ form
        String ma = request.getParameter("ma");
        String loai = request.getParameter("loai");
        String trangThai = request.getParameter("trangthai");
        double dienTich = Double.parseDouble(request.getParameter("dientich"));
        int tang = Integer.parseInt(request.getParameter("tang"));
        long gia = Long.parseLong(request.getParameter("gia"));
        String chitiet = request.getParameter("chitiet");
        LocalDate ngayBD = LocalDate.parse(request.getParameter("ngaybd"));
        LocalDate ngayKT = LocalDate.parse(request.getParameter("ngaykt"));

        MatBangChoThue mb = new MatBangChoThue(ma, dienTich, trangThai, tang, loai, chitiet, gia, ngayBD, ngayKT);

        // Validate server side
        String error = Validator.validate(mb);
        if (error != null) {
            request.setAttribute("errorMessage", error);
            request.setAttribute("matBang", mb); // giữ lại dữ liệu nhập
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        // Check trùng mã
        if (dao.findMatBangById(ma) != null) {
            request.setAttribute("errorMessage", "Mã mặt bằng đã tồn tại.");
            request.getRequestDispatcher("add.jsp").forward(request, response);
            return;
        }

        // Insert DB
        boolean success = dao.addMatBang(mb);
        if (success) {
            response.sendRedirect("TComplex?action=list");
        } else {
            request.setAttribute("errorMessage", "Thêm mới thất bại!");
            request.getRequestDispatcher("add.jsp").forward(request, response);
        }

        request.getRequestDispatcher("add.jsp").forward(request, response);
    }

    private void listMatBang(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<MatBangChoThue> list = dao.selectAllMatBang();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteMatBang(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String maMatBang = request.getParameter("ma");
        dao.deleteMatBang(maMatBang);
        response.sendRedirect("/TComplex?action=list");
    }

}
