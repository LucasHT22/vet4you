package controller;

import dao.PagamentoDAO;
import model.Pagamento;
import java.sql.SQLException;
import java.util.List;

public class PagamentoController {
    private PagamentoDAO pagamentoDAO;
    
    public PagamentoController() {
        this.pagamentoDAO = new PagamentoDAO();
    }
    
    public boolean registrarPagamento(Pagamento pagamento) {
        try {
            if (pagamento.getIdPaciente() == null || pagamento.getValor() == null) {
                System.out.println("Paciente e Valor são obrigatórios!");
                return false;
            }
            
            if (pagamento.getStatus() == null) {
                pagamento.setStatus('P'); // P = Pendente
            }
            
            pagamentoDAO.inserir(pagamento);
            System.out.println("Pagamento registrado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao registrar pagamento: " + e.getMessage());
            return false;
        }
    }
    
    public List<Pagamento> listarPagamentos() {
        try {
            return pagamentoDAO.listarTodos();
        } catch (SQLException e) {
            System.err.println("Erro ao listar pagamentos: " + e.getMessage());
            return null;
        }
    }
    
    public List<Pagamento> listarPagamentosPorPaciente(Integer idPaciente) {
        try {
            return pagamentoDAO.buscarPorPaciente(idPaciente);
        } catch (SQLException e) {
            System.err.println("Erro ao listar pagamentos do paciente: " + e.getMessage());
            return null;
        }
    }
    
    public boolean processarPagamento(Integer idPagamento) {
        try {
            Pagamento pagamento = pagamentoDAO.buscarPorId(idPagamento);
            if (pagamento != null) {
                pagamento.setStatus('C'); // C = Confirmado
                pagamentoDAO.atualizar(pagamento);
                System.out.println("Pagamento processado com sucesso!");
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("Erro ao processar pagamento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean atualizarPagamento(Pagamento pagamento) {
        try {
            pagamentoDAO.atualizar(pagamento);
            System.out.println("Pagamento atualizado com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pagamento: " + e.getMessage());
            return false;
        }
    }
    
    public boolean removerPagamento(Integer id) {
        try {
            pagamentoDAO.deletar(id);
            System.out.println("Pagamento removido com sucesso!");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao remover pagamento: " + e.getMessage());
            return false;
        }
    }
    
    public Pagamento buscarPagamentoPorId(Integer id) {
        try {
            return pagamentoDAO.buscarPorId(id);
        } catch (SQLException e) {
            System.err.println("Erro ao buscar pagamento: " + e.getMessage());
            return null;
        }
    }
}