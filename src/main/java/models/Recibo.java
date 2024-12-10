/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Víctor
 */
public class Recibo {
    private String id;
    private String codigo;
    private String cliente;
    private String momento;
    private String monto;
    private String descripcion;

    public Recibo(String id, String codigo, String cliente, String momento, String monto, String descripcion) {
        this.id = id;
        this.codigo = codigo;
        this.cliente = cliente;
        this.momento = momento;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    public Recibo() {
        id = "";
        codigo = "";
        cliente = "";
        momento = "";
        monto = "";
        descripcion = "";
    }

    public String getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getMomento() {
        return momento;
    }

    public String getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public void setMomento(String momento) {
        this.momento = momento;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
