/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojosNDB;

import java.util.Date;

/**
 *
 * @author Daniel
 */
public class ResumenGeneralNBD {
    private Date fecha;
    private String local;
    private String notas;
    private double ventasConNota;
    private double ventasSinNota;
    private double abonoCredito;
    private double gastosDeVenta;
    private int noTiendas;
    private String tiendas;
    private double gastosGenerales;
    private double compras;
    private double totalDelDia;

    public ResumenGeneralNBD(Date fecha, String local, String notas, double ventasConNota, double ventasSinNota, double abonoCredito, double gastosDeVenta, int noTiendas, String tiendas, double gastosGenerales, double compras, double totalDelDia) {
        this.fecha = fecha;
        this.local = local;
        this.notas = notas;
        this.ventasConNota = ventasConNota;
        this.ventasSinNota = ventasSinNota;
        this.abonoCredito = abonoCredito;
        this.gastosDeVenta = gastosDeVenta;
        this.noTiendas = noTiendas;
        this.tiendas = tiendas;
        this.gastosGenerales = gastosGenerales;
        this.compras = compras;
        this.totalDelDia = totalDelDia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public double getVentasConNota() {
        return ventasConNota;
    }

    public void setVentasConNota(double ventasConNota) {
        this.ventasConNota = ventasConNota;
    }

    public double getVentasSinNota() {
        return ventasSinNota;
    }

    public void setVentasSinNota(double ventasSinNota) {
        this.ventasSinNota = ventasSinNota;
    }

    public double getAbonoCredito() {
        return abonoCredito;
    }

    public void setAbonoCredito(double abonoCredito) {
        this.abonoCredito = abonoCredito;
    }

    public double getGastosDeVenta() {
        return gastosDeVenta;
    }

    public void setGastosDeVenta(double gastosDeVenta) {
        this.gastosDeVenta = gastosDeVenta;
    }

    public int getNoTiendas() {
        return noTiendas;
    }

    public void setNoTiendas(int noTiendas) {
        this.noTiendas = noTiendas;
    }

    public String getTiendas() {
        return tiendas;
    }

    public void setTiendas(String tiendas) {
        this.tiendas = tiendas;
    }

    public double getGastosGenerales() {
        return gastosGenerales;
    }

    public void setGastosGenerales(double gastosGenerales) {
        this.gastosGenerales = gastosGenerales;
    }

    public double getCompras() {
        return compras;
    }

    public void setCompras(double compras) {
        this.compras = compras;
    }

    public double getTotalDelDia() {
        return totalDelDia;
    }

    public void setTotalDelDia(double totalDelDia) {
        this.totalDelDia = totalDelDia;
    }
}
