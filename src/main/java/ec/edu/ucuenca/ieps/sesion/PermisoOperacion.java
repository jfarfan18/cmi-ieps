/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ucuenca.ieps.sesion;


public class PermisoOperacion {
    
    
    private boolean consultar;
    private boolean nuevo;
    private boolean editar;
    private boolean eliminar;
    private boolean imprimir;
    private boolean imprimirComprobante;
    private boolean ver;
    private boolean guardar;
    private boolean retencion;
    private boolean contabilizar;
    private boolean exportaExcel;
    private boolean exportaPdf;
    private boolean exportaXml;
    private boolean anular;
    private boolean precancelar;
    private boolean pagare;
    private boolean conceder;
    private boolean entregar;
    private boolean cambiar;
     
    
    public PermisoOperacion()
    {
        // Se colocan en true para indicar que se deben deshabilitar los botones
        consultar = true;
        nuevo = true;
        editar = true;
        eliminar = true;
        imprimir = true;
        imprimirComprobante = true;
        exportaExcel = true;
        exportaPdf = true;
        exportaXml = true;
        ver = true;
        guardar = true;
        retencion = true;
        contabilizar = true;
        this.anular = true;
        this.conceder = true;
        this.pagare = true;
        this.entregar = true;
        this.cambiar = true;
    }

    /**
     * @return the consultar
     */
    public boolean isConsultar() {
        return consultar;
    }

    /**
     * @param consultar the consultar to set
     */
    public void setConsultar(boolean consultar) {
        this.consultar = consultar;
    }

    /**
     * @return the nuevo
     */
    public boolean isNuevo() {
        return nuevo;
    }

    /**
     * @param nuevo the nuevo to set
     */
    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the eliminar
     */
    public boolean isEliminar() {
        return eliminar;
    }

    /**
     * @param eliminar the eliminar to set
     */
    public void setEliminar(boolean eliminar) {
        this.eliminar = eliminar;
    }

    /**
     * @return the imprimir
     */
    public boolean isImprimir() {
        return imprimir;
    }

    /**
     * @param imprimir the imprimir to set
     */
    public void setImprimir(boolean imprimir) {
        this.imprimir = imprimir;
    }

    /**
     * @return the imprimirComprobante
     */
    public boolean isImprimirComprobante() {
        return imprimirComprobante;
    }

    /**
     * @param imprimirComprobante the imprimirComprobante to set
     */
    public void setImprimirComprobante(boolean imprimirComprobante) {
        this.imprimirComprobante = imprimirComprobante;
    }

    /**
     * @return the exportaExcel
     */
    public boolean isExportaExcel() {
        return exportaExcel;
    }

    /**
     * @param exportaExcel the exportaExcel to set
     */
    public void setExportaExcel(boolean exportaExcel) {
        this.exportaExcel = exportaExcel;
    }

    /**
     * @return the exportaPdf
     */
    public boolean isExportaPdf() {
        return exportaPdf;
    }

    /**
     * @param exportaPdf the exportaPdf to set
     */
    public void setExportaPdf(boolean exportaPdf) {
        this.exportaPdf = exportaPdf;
    }

    /**
     * @return the exportaXml
     */
    public boolean isExportaXml() {
        return exportaXml;
    }

    /**
     * @param exportaXml the exportaXml to set
     */
    public void setExportaXml(boolean exportaXml) {
        this.exportaXml = exportaXml;
    }

    /**
     * @return the ver
     */
    public boolean isVer() {
        return ver;
    }

    /**
     * @param ver the ver to set
     */
    public void setVer(boolean ver) {
        this.ver = ver;
    }

    /**
     * @return the guardar
     */
    public boolean isGuardar() {
        return guardar;
    }

    /**
     * @param guardar the guardar to set
     */
    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    /**
     * @return the retencion
     */
    public boolean isRetencion() {
        return retencion;
    }

    /**
     * @param retencion the retencion to set
     */
    public void setRetencion(boolean retencion) {
        this.retencion = retencion;
    }

    /**
     * @return the contabilizar
     */
    public boolean isContabilizar() {
        return contabilizar;
    }

    /**
     * @param contabilizar the contabilizar to set
     */
    public void setContabilizar(boolean contabilizar) {
        this.contabilizar = contabilizar;
    }

    /**
     * @return the anular
     */
    public boolean isAnular() {
        return anular;
    }

    /**
     * @param anular the anular to set
     */
    public void setAnular(boolean anular) {
        this.anular = anular;
    }

    /**
     * @return the precancelar
     */
    public boolean isPrecancelar() {
        return precancelar;
    }

    /**
     * @param precancelar the precancelar to set
     */
    public void setPrecancelar(boolean precancelar) {
        this.precancelar = precancelar;
    }

    /**
     * @return the pagare
     */
    public boolean isPagare() {
        return pagare;
    }

    /**
     * @param pagare the pagare to set
     */
    public void setPagare(boolean pagare) {
        this.pagare = pagare;
    }

    /**
     * @return the conceder
     */
    public boolean isConceder() {
        return conceder;
    }

    /**
     * @param conceder the conceder to set
     */
    public void setConceder(boolean conceder) {
        this.conceder = conceder;
    }

    /**
     * @return the entregar
     */
    public boolean isEntregar() {
        return entregar;
    }

    /**
     * @param entregar the entregar to set
     */
    public void setEntregar(boolean entregar) {
        this.entregar = entregar;
    }

    /**
     * @return the cambiar
     */
    public boolean isCambiar() {
        return cambiar;
    }

    /**
     * @param cambiar the cambiar to set
     */
    public void setCambiar(boolean cambiar) {
        this.cambiar = cambiar;
    }
    
    
    
}
