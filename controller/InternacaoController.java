package controller;

import dao.InternacaoDAO;
import model.Internacao;
import java.sql.SQLException;
import java.util.List;

public class InternacaoController {
    private InternacaoDAO internacaoDAO;
    
    public InternacaoController() {
        this.internacaoDAO = new InternacaoDAO();
    }
    
    public boolean registrarInternacao(Internacao internacao) {
        try {
            if (internacao.getIdPaciente() == null || internacao.getIdVeterinario() == null) {
                System.out.println("Paciente e Veterinário são obrigatórios!");
                return false;
            }
            
            internacaoDAO.inserir(internacao);
            System.out.println("Internação registrada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar internação: " + e.getMessage());
            return false;
        }
    }
    
    public List<Internacao> listarInternacoes() {
        try {
            return internacaoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar internações: " + e.getMessage());
            return null;
        }
    }
    
    public boolean atualizarInternacao(Internacao internacao) {
        try {
            internacaoDAO.atualizar(internacao);
            System.out.println("Internação atualizada com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar internação: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerInternacao(Integer id) {
        try {
            internacaoDAO.deletar(id);
            System.out.println("Internação removida com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover internação: " + e.getMessage());
            return false;
        }
    }
    
    public Internacao buscarInternacaoPorId(Integer id) {
        try {
            return internacaoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar internação: " + e.getMessage());
            return null;
        }
    }
}