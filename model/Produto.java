package model;

import java.math.BigDecimal;

public class Produto {
    private Integer idProduto;
    private String nome;
    private String tipo;
    private BigDecimal preco;
    private Integer estoque;

    public Produto() {}

    public Produto(String nome, String tipo, BigDecimal preco, Integer estoque) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.estoque = estoque;
    }

    // Getters e Setters
    public Integer getIdProduto() { return idProduto; }
    public void setIdProduto(Integer idProduto) { this.idProduto = idProduto; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    
    public Integer getEstoque() { return estoque; }
    public void setEstoque(Integer estoque) { this.estoque = estoque; }
}