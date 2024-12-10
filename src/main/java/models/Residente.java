/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Víctor
 */
public class Residente {
    private String id;
    private String ci;
    private String nombre;
    private String nombreFamiliar;
    private String telefonoFamiliar;
    private String fechaIngreso;
    private String fechaNacimiento;

    public Residente(String id, String ci, String nombre, String nombreFamiliar, String telefonoFamiliar, String fechaIngreso, String fechaNacimiento) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.nombreFamiliar = nombreFamiliar;
        this.telefonoFamiliar = telefonoFamiliar;
        this.fechaIngreso = fechaIngreso;
        this.fechaNacimiento = fechaNacimiento;
    }

    
    public Residente() {
        id = "";
        ci = "";
        nombre = "";
        nombreFamiliar = "";
        telefonoFamiliar = "";
        fechaIngreso = "";
        fechaNacimiento = "";
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreFamiliar() {
        return nombreFamiliar;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public String getTelefonoFamiliar() {
        return telefonoFamiliar;
    }

    public void setTelefonoFamiliar(String telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    
    
}
