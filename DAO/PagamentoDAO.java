package dao;

import model.Pagamento;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {
    
    public void inserir(Pagamento pagamento) throws SQLException {
        String sql = "INSERT INTO Pagamento (valor, data_pagamento, metodo_pagamento, id_consulta, id_usuario) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setDouble(1, pagamento.getValor());
            stmt.setDate(2, Date.valueOf(pagamento.getDataPagamento()));
            stmt.setString(3, pagamento.getMetodoPagamento());
            
            if (pagamento.getIdConsulta() != null) {
                stmt.setInt(4, pagamento.getIdConsulta());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            
            stmt.setInt(5, pagamento.getIdUsuario());
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    pagamento.setIdPagamento(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Pagamento pagamento) throws SQLException {
        String sql = "UPDATE Pagamento SET valor=?, data_pagamento=?, metodo_pagamento=?, id_consulta=?, id_usuario=? WHERE id_pagamento=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDouble(1, pagamento.getValor());
            stmt.setDate(2, Date.valueOf(pagamento.getDataPagamento()));
            stmt.setString(3, pagamento.getMetodoPagamento());
            
            if (pagamento.getIdConsulta() != null) {
                stmt.setInt(4, pagamento.getIdConsulta());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            
            stmt.setInt(5, pagamento.getIdUsuario());
            stmt.setInt(6, pagamento.getIdPagamento());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Pagamento WHERE id_pagamento=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Pagamento buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Pagamento WHERE id_pagamento=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearPagamento(rs);
                }
            }
        }
        return null;
    }
    
    public List<Pagamento> listarTodos() throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM Pagamento";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                pagamentos.add(mapearPagamento(rs));
            }
        }
        return pagamentos;
    }
    
    public List<Pagamento> buscarPorUsuario(Integer idUsuario) throws SQLException {
        List<Pagamento> pagamentos = new ArrayList<>();
        String sql = "SELECT * FROM Pagamento WHERE id_usuario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pagamentos.add(mapearPagamento(rs));
                }
            }
        }
        return pagamentos;
    }
    
    private Pagamento mapearPagamento(ResultSet rs) throws SQLException {
        Pagamento pagamento = new Pagamento();
        pagamento.setIdPagamento(rs.getInt("id_pagamento"));
        pagamento.setValor(rs.getDouble("valor"));
        pagamento.setDataPagamento(rs.getDate("data_pagamento").toLocalDate());
        pagamento.setMetodoPagamento(rs.getString("metodo_pagamento"));
        
        Integer idConsulta = rs.getInt("id_consulta");
        if (!rs.wasNull()) {
            pagamento.setIdConsulta(idConsulta);
        }
        
        pagamento.setIdUsuario(rs.getInt("id_usuario"));
        return pagamento;
    }
}