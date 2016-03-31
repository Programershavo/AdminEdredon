/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojosNDB;

/**
 *
 * @author Daniel
 */
public class VentasOGastosNBD {

    private String tienda;
    private String enero;
    private String febrero;
    private String marzo;
    private String abril;
    private String mayo;
    private String junio;
    private String julio;
    private String agosto;
    private String septiembre;
    private String octubre;
    private String noviembre;
    private String diciembre;
    private String totalAnualPorTienda;

    public VentasOGastosNBD(String tienda, String enero, String febrero, String marzo, String abril, String mayo, String junio, String julio, String agosto, String septiembre, String octubre, String noviembre, String diciembre, String totalAnualPorTienda) {
        this.tienda = tienda;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septiembre = septiembre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diciembre = diciembre;
        this.totalAnualPorTienda = totalAnualPorTienda;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getEnero() {
        return enero;
    }

    public void setEnero(String enero) {
        this.enero = enero;
    }

    public String getFebrero() {
        return febrero;
    }

    public void setFebrero(String febrero) {
        this.febrero = febrero;
    }

    public String getMarzo() {
        return marzo;
    }

    public void setMarzo(String marzo) {
        this.marzo = marzo;
    }

    public String getAbril() {
        return abril;
    }

    public void setAbril(String abril) {
        this.abril = abril;
    }

    public String getMayo() {
        return mayo;
    }

    public void setMayo(String mayo) {
        this.mayo = mayo;
    }

    public String getJunio() {
        return junio;
    }

    public void setJunio(String junio) {
        this.junio = junio;
    }

    public String getJulio() {
        return julio;
    }

    public void setJulio(String julio) {
        this.julio = julio;
    }

    public String getAgosto() {
        return agosto;
    }

    public void setAgosto(String agosto) {
        this.agosto = agosto;
    }

    public String getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(String septiembre) {
        this.septiembre = septiembre;
    }

    public String getOctubre() {
        return octubre;
    }

    public void setOctubre(String octubre) {
        this.octubre = octubre;
    }

    public String getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(String noviembre) {
        this.noviembre = noviembre;
    }

    public String getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(String diciembre) {
        this.diciembre = diciembre;
    }

    public String getTotalAnualPorTienda() {
        return totalAnualPorTienda;
    }

    public void setTotalAnualPorTienda(String totalAnualPorTienda) {
        this.totalAnualPorTienda = totalAnualPorTienda;
    }

}
