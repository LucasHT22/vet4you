package controller;

import dao.CartaoDAO;
import model.Cartao;
import java.sql.SQLException;
import java.util.List;

public class CartaoController {
    private CartaoDAO cartaoDAO;
    
    public CartaoController() {
        this.cartaoDAO = new CartaoDAO();
    }
    
    public boolean cadastrarCartao(Cartao cartao) {
        try {
            if (cartao.getTitular() == null || cartao.getValidade() == null) {
                System.out.println("Titular e Validade são obrigatórios!");
                return false;
            }
            
            cartaoDAO.inserir(cartao);
            System.out.println("Cartão cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cartão: " + e.getMessage());
            return false;
        }
    }
    
    public List<Cartao> listarCartoes() {
        try {
            return cartaoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar cartões: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarCartao(Cartao cartao) {
        try {
            cartaoDAO.atualizar(cartao);
            System.out.println("Cartão atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cartão: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerCartao(Integer id) {
        try {
            cartaoDAO.deletar(id);
            System.out.println("Cartão removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover cartão: " + e.getMessage());
            return false;
        }
    }
    
    public Cartao buscarCartaoPorId(Integer id) {
        try {
            return cartaoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar cartão: " + e.getMessage());
            return null;
        }
    }
}
            }
            
            if (usuarioDAO.buscarPorUsername(usuario.getUsername()) != null) {
                System.out.println("Username já existe!");
                return false;
            }
            
            usuarioDAO.inserir(usuario);
            System.out.println("Usuário cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerUsuario(Integer id) {
        try {
            usuarioDAO.deletar(id);
            System.out.println("Usuário removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover usuário: " + e.getMessage());
            return false;
        }
    }
    
    public List<Usuario> listarUsuarios() {
        try {
            return usuarioDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar usuários: " + e.getMessage());
            return null;
        }
    }
    
    public Usuario autenticar(String username, String password) {
        try {
            Usuario usuario = usuarioDAO.buscarPorUsername(username);
            
            if (usuario != null && usuario.getPassword().equals(password)) {
                if (usuario.getIsActive() == 'S' || usuario.getIsActive() == '1') {
                    System.out.println("Login realizado com sucesso!");
                    return usuario;
                } else {
                    System.out.println("Usuário inativo!");
                    return null;
                }
            }
            
            System.out.println("Credenciais inválidas!");
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao autenticar: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarUsuario(Usuario usuario) {
        try {
            usuarioDAO.atualizar(usuario);
            System.out.println("Usuário atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
            return false;
        }
    }
    
    public Usuario buscarUsuarioPorId(Integer id) {
        try {
            return usuarioDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar usuário: " + e.getMessage());
            return null;
        }
    }
}