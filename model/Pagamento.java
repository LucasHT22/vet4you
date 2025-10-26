package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Pagamento {
    private Integer idPagamento;
    private String servico;
    private String formaPagamento;
    private BigDecimal valor;
    private Character status;
    private LocalDate dataPagamento;
    private Integer idPaciente;

    public Pagamento() {}

    public Pagamento(String servico, String formaPagamento, BigDecimal valor, Character status, LocalDate dataPagamento, Integer idPaciente) {
        this.servico = servico;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.status = status;
        this.dataPagamento = dataPagamento;
        this.idPaciente = idPaciente;
    }

    // Getters e Setters
    public Integer getIdPagamento() { return idPagamento; }
    public void setIdPagamento(Integer idPagamento) { this.idPagamento = idPagamento; }
    
    public String getServico() { return servico; }
    public void setServico(String servico) { this.servico = servico; }
    
    public String getFormaPagamento() { return formaPagamento; }
    public void setFormaPagamento(String formaPagamento) { this.formaPagamento = formaPagamento; }
    
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    
    public Character getStatus() { return status; }
    public void setStatus(Character status) { this.status = status; }
    
    public LocalDate getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDate dataPagamento) { this.dataPagamento = dataPagamento; }
    
    public Integer getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Integer idPaciente) { this.idPaciente = idPaciente; }
}