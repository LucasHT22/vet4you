package controller;

import dao.ProdutoDAO;
import model.Produto;
import java.sql.SQLException;
import java.util.List;

public class ProdutoController {
    private ProdutoDAO produtoDAO;
    
    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
    }
    
    public boolean cadastrarProduto(Produto produto) {
        try {
            if (produto.getNome() == null || produto.getPreco() == null) {
                System.out.println("Nome e preço são obrigatórios!");
                return false;
            }
            
            produtoDAO.inserir(produto);
            System.out.println("Produto cadastrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar produto: " + e.getMessage());
            return false;
        }
    }
    
    public List<Produto> listarProdutos() {
        try {
            return produtoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarProduto(Produto produto) {
        try {
            produtoDAO.atualizar(produto);
            System.out.println("Produto atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar produto: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerProduto(Integer id) {
        try {
            produtoDAO.deletar(id);
            System.out.println("Produto removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover produto: " + e.getMessage());
            return false;
        }
    }
    
    public Produto buscarProdutoPorId(Integer id) {
        try {
            return produtoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar produto: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarEstoque(Integer idProduto, Integer quantidade) {
        try {
            Produto produto = produtoDAO.buscarPorId(idProduto);
            if (produto != null) {
                produto.setEstoque(quantidade);
                produtoDAO.atualizar(produto);
                System.out.println("Estoque atualizado com sucesso!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque: " + e.getMessage());
            return false;
        }
    }
}