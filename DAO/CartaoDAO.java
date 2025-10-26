package dao;

import model.Cartao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {
    
    public void inserir(Cartao cartao) throws SQLException {
        String sql = "INSERT INTO Cartao (plano, validade, titular) VALUES (?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setInt(1, cartao.getPlano());
            stmt.setDate(2, Date.valueOf(cartao.getValidade()));
            stmt.setString(3, cartao.getTitular());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cartao.setIdCartao(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Cartao cartao) throws SQLException {
        String sql = "UPDATE Cartao SET plano=?, validade=?, titular=? WHERE id_cartao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, cartao.getPlano());
            stmt.setDate(2, Date.valueOf(cartao.getValidade()));
            stmt.setString(3, cartao.getTitular());
            stmt.setInt(4, cartao.getIdCartao());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Cartao WHERE id_cartao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Cartao buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Cartao WHERE id_cartao=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCartao(rs);
                }
            }
        }
        return null;
    }
    
    public List<Cartao> listarTodos() throws SQLException {
        List<Cartao> cartoes = new ArrayList<>();
        String sql = "SELECT * FROM Cartao";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                cartoes.add(mapearCartao(rs));
            }
        }
        return cartoes;
    }
    
    private Cartao mapearCartao(ResultSet rs) throws SQLException {
        Cartao cartao = new Cartao();
        cartao.setIdCartao(rs.getInt("id_cartao"));
        cartao.setPlano(rs.getInt("plano"));
        cartao.setValidade(rs.getDate("validade").toLocalDate());
        cartao.setTitular(rs.getString("titular"));
        return cartao;
    }
}