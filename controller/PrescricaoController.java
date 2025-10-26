package controller;

import dao.PrescricaoDAO;
import model.Prescricao;
import java.sql.SQLException;
import java.util.List;

public class PrescricaoController {
    private PrescricaoDAO prescricaoDAO;
    
    public PrescricaoController() {
        this.prescricaoDAO = new PrescricaoDAO();
    }
    
    public boolean registrarPrescricao(Prescricao prescricao) {
        try {
            if (prescricao.getIdConsulta() == null || prescricao.getIdProduto() == null) {
                System.out.println("Consulta e Produto são obrigatórios!");
                return false;
            }
            
            prescricaoDAO.inserir(prescricao);
            System.out.println("Prescrição registrada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar prescrição: " + e.getMessage());
            return false;
        }
    }
    
    public List<Prescricao> listarPrescricoes() {
        try {
            return prescricaoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar prescrições: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarPrescricao(Prescricao prescricao) {
        try {
            prescricaoDAO.atualizar(prescricao);
            System.out.println("Prescrição atualizada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar prescrição: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerPrescricao(Integer id) {
        try {
            prescricaoDAO.deletar(id);
            System.out.println("Prescrição removida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover prescrição: " + e.getMessage());
            return false;
        }
    }
    
    public Prescricao buscarPrescricaoPorId(Integer id) {
        try {
            return prescricaoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar prescrição: " + e.getMessage());
            return null;
        }
    }
}