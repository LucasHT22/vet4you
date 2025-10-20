package model;

import java.time.LocalDate;

public class Cartao {
    private Integer idCartao;
    private Integer plano;
    private LocalDate validade;
    private String titular;

    public Cartao() {}

    public Cartao(Integer plano, LocalDate validade, String titular) {
        this.plano = plano;
        this.validade = validade;
        this.titular = titular;
    }

    // Getters e Setters
    public Integer getIdCartao() { return idCartao; }
    public void setIdCartao(Integer idCartao) { this.idCartao = idCartao; }
    
    public Integer getPlano() { return plano; }
    public void setPlano(Integer plano) { this.plano = plano; }
    
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
    
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
}