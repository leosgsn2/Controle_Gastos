package model;

public class Gasto {

    private int id;
    private String descricao;
    private double valor;
    private String categoria;
    private int idUsuario;

    public Gasto(int id, String descricao, double valor, String categoria, int idUsuario) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
    }

    public int getId() { return id; }
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getCategoria() { return categoria; }
    public int getIdUsuario() { return idUsuario; }
}