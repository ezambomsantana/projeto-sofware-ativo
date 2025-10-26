package br.insper.cotacao.stocks.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movimentacao {
    @JsonProperty("cpf_comprador")
    private String cpfComprador;

    @JsonProperty("cpf_vendedor")
    private String cpfVendedor;

    @JsonProperty("ticker")
    private String ticker;

    @JsonProperty("quantidade")
    private int quantidade;

    @JsonProperty("valor_movimentacao")
    private double valorMovimentacao;

    public String getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public String getCpfVendedor() {
        return cpfVendedor;
    }

    public void setCpfVendedor(String cpfVendedor) {
        this.cpfVendedor = cpfVendedor;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorMovimentacao() {
        return valorMovimentacao;
    }

    public void setValorMovimentacao(double valorMovimentacao) {
        this.valorMovimentacao = valorMovimentacao;
    }
}