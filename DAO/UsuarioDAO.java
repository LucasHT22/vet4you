package dao;

import model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, telefone, email, username, password, isAdmin, cpf, isActive) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getUsername());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, String.valueOf(usuario.getIsAdmin()));
            stmt.setString(7, usuario.getCpf());
            stmt.setString(8, String.valueOf(usuario.getIsActive()));
            
            stmt.executeUpdate();
            
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    usuario.setIdUsuario(rs.getInt(1));
                }
            }
        }
    }
    
    public void atualizar(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET nome=?, telefone=?, email=?, username=?, password=?, isAdmin=?, cpf=?, isActive=? WHERE id_usuario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getTelefone());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getUsername());
            stmt.setString(5, usuario.getPassword());
            stmt.setString(6, String.valueOf(usuario.getIsAdmin()));
            stmt.setString(7, usuario.getCpf());
            stmt.setString(8, String.valueOf(usuario.getIsActive()));
            stmt.setInt(9, usuario.getIdUsuario());
            
            stmt.executeUpdate();
        }
    }
    
    public void deletar(Integer id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id_usuario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public Usuario buscarPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id_usuario=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }
    
    public Usuario buscarPorUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE username=?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        }
        return null;
    }
    
    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                usuarios.add(mapearUsuario(rs));
            }
        }
        return usuarios;
    }
    
    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("id_usuario"));
        usuario.setNome(rs.getString("nome"));
        usuario.setTelefone(rs.getString("telefone"));
        usuario.setEmail(rs.getString("email"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setIsAdmin(rs.getString("isAdmin").charAt(0));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setIsActive(rs.getString("isActive").charAt(0));
        return usuario;
    }
}