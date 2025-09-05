package vn.codegym.module3final.DAO;

import vn.codegym.module3final.Model.MatBangChoThue;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatBangDAO {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/TComplex?useSSL=false&serverTimezone=UTC";;
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String ADD_MAT_BANG = "INSERT INTO MatBang (MaMatBang, LoaiMatBang, TrangThai, DienTich, Tang, Gia, NgayBatDau, NgayKetThuc, GhiChu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_MAT_BANG_BY_ID = "SELECT * FROM MatBang WHERE MaMatBang = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM matbang ORDER BY dientich ASC";

    public MatBangDAO() {}

    public Connection getConnection() throws SQLException{
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn =DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public boolean addMatBang(MatBangChoThue maMatBang) {
        MatBangChoThue matBang = null;
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(ADD_MAT_BANG)) {
            stmt.setString(1, maMatBang.getMaMatBang());
            stmt.setString(2, maMatBang.getLoaiVanPhong());
            stmt.setString(3, maMatBang.getTrangThai());
            stmt.setDouble(4, maMatBang.getDienTich());
            stmt.setInt(5, maMatBang.getTang());
            stmt.setLong(6, maMatBang.getGiaChoThue());
            stmt.setDate(7, Date.valueOf(matBang.getNgayBatDau()));
            stmt.setDate(8, Date.valueOf(maMatBang.getNgayKetThuc()));
            stmt.setString(9, maMatBang.getMoTaChiTiet());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public MatBangChoThue findMatBangById(String maMatBang) {
        MatBangChoThue mb = null;
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_MAT_BANG_BY_ID)) {
            ps.setString(1, maMatBang);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                mb = new MatBangChoThue(
                        rs.getString("MaMatBang"),
                        rs.getDouble("DienTich"),
                        rs.getString("TrangThai"),
                        rs.getInt("Tang"),
                        rs.getString("LoaiMatBang"),
                        rs.getString("GhiChu"),
                        rs.getLong("Gia"),
                        rs.getDate("NgayBatDau").toLocalDate(),
                        rs.getDate("NgayKetThuc").toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mb;
    }

    public List<MatBangChoThue> selectAllMatBang() throws SQLException {
        List<MatBangChoThue> list = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MaMatBang");
                double dientich = rs.getDouble("DienTich");
                String trangthai = rs.getString("TrangThai");
                int tang = rs.getInt("Tang");
                String loai = rs.getString("LoaiMatBang");
                String ghichu = rs.getString("GhiChu");
                long gia = rs.getLong("Gia");
                LocalDate ngaybd =rs.getDate("NgayBatDau").toLocalDate();
                LocalDate ngaykt = rs.getDate("NgayKetThuc").toLocalDate();
                list.add(new MatBangChoThue(ma, dientich, trangthai, tang, loai, ghichu, gia, ngaybd, ngaykt));
            }
        }
        return list;
    }
}
