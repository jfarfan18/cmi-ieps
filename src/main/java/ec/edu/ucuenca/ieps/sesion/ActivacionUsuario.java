/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.sesion;


/**
 *
 * @author vicastoc
 */
public class ActivacionUsuario {

    // -------------------------------------------------------------------------
    // PROPIEDAD SET
    public static void setCodigoOpcion(Long codigoOpcion) {
        Sesion.setVariable("codigoOpcion", codigoOpcion);
    }

    public static void setCodigoRol(String codigoRol) {
        Sesion.setVariable("codigoRol", codigoRol);
    }

    public static void setCodigoIfip(Long codigoIfip) {
        Sesion.setVariable("codigoIfip", codigoIfip);
    }

    public static void setControlador(String controlador) {
        Sesion.setVariable("controlador", controlador);
    }

    public static void setCodigoUsuario(Long codigoUsuario) {
        Sesion.setVariable("codigoUsuario", codigoUsuario);
    }

//    public static void setUsuario(Usuario usuario) {
//        Sesion.setVariable("usuario", usuario);
//    }

    public static void setCodigoAccesoSistema(Long codigoAccesoSistema) {
        Sesion.setVariable("codigoAccesoSistemaa", codigoAccesoSistema);
    }

    public static void setCodigoIfipAgencia(Long codigoIfipAgencia) {
        Sesion.setVariable("codigoIfipAgencia", codigoIfipAgencia);
    }

    public static void setSinPermisosOpcion(String sinPermisosOpcion) {
        Sesion.setVariable("sinPermisosOpcion", sinPermisosOpcion);
    }

    public static void setCodigoComputador(Long codigoComputador) {
        Sesion.setVariable("codigoComputador", codigoComputador);
    }

    public static void setCambiarContrasena(boolean cambiarContrasena) {
        Sesion.setVariable("cambiarContrasena", cambiarContrasena);
    }

    public static void setCodigoPeriodo(String codigoPeriodo) {
        Sesion.setVariable("codigoPeriodo", codigoPeriodo);
    }

    public static void setCodigoTipoSistemaOperativo(Long codigoTipoSistemaOperativo) {
        Sesion.setVariable("codigoTipoSistemaOperativo", codigoTipoSistemaOperativo);
    }

    public static void setRutaImpresora(String rutaImpresora) {
        Sesion.setVariable("rutaImpresora", rutaImpresora);
    }

    public static void setTipoSOServidorAppl(String tipoSOServidorAppl) {
        Sesion.setVariable("tipoSOServidorAppl", tipoSOServidorAppl);
    }

    public static void setRutaImpresionArchivos(String rutaImpresionArchivos) {
        Sesion.setVariable("rutaImpresionArchivos", rutaImpresionArchivos);
    }

    public static void setRutaSubidaArchivos(String rutaSubidaArchivos) {
        Sesion.setVariable("rutaSubidaArchivos", rutaSubidaArchivos);
    }
    
     public static void setTipoSOServidor(String tipoSOServidor) {
        Sesion.setVariable("tipoSOServidor", tipoSOServidor);
    }

    // -------------------------------------------------------------------------
    // PROPIEDAD GET
//    public static Usuario getUsuario() {
//        return (Usuario) Sesion.getVariable("usuario");
//    }

    /**
     * @return the codigoUsuario
     */
    public static Long getCodigoUsuario() {
        return (Sesion.getVariable("codigoUsuario") != null) ? Long.parseLong(Sesion.getVariable("codigoUsuario").toString()) : null;
    }

    public static Long getCodigoIfip() {
        return (Sesion.getVariable("codigoIfip") != null) ? Long.parseLong(Sesion.getVariable("codigoIfip").toString()) : null;
    }

    public static Long getCodigoOpcion() {
        return (Sesion.getVariable("codigoOpcion") != null) ? Long.parseLong(Sesion.getVariable("codigoOpcion").toString()) : null;
    }

    public static String getCodigoRol() {

        return (Sesion.getVariable("codigoRol") != null) ? Sesion.getVariable("codigoRol").toString() : null;
    }

    public static String getControlador() {
        return (Sesion.getVariable("controlador") != null) ? Sesion.getVariable("controlador").toString() : null;
    }

    public static Long getCodigoAccesoSistema() {
        return (Sesion.getVariable("codigoAccesoSistemaa") != null) ? Long.parseLong(Sesion.getVariable("codigoAccesoSistemaa").toString()) : null;
    }

    public static Long getCodigoIfipAgencia() {
        return (Sesion.getVariable("codigoIfipAgencia") != null) ? Long.parseLong(Sesion.getVariable("codigoIfipAgencia").toString()) : null;
    }

    public static String getSinPermisosOpcion() {
        return (Sesion.getVariable("sinPermisosOpcion") != null) ? Sesion.getVariable("sinPermisosOpcion").toString() : null;
    }

    public static Long getCodigoComputador() {
        return (Sesion.getVariable("codigoComputador") != null) ? Long.parseLong(Sesion.getVariable("codigoComputador").toString()) : null;
    }

    public static boolean isCambiarContrasena() {
        return (Sesion.getVariable("cambiarContrasena") != null) ? Boolean.parseBoolean(Sesion.getVariable("cambiarContrasena").toString()) : false;
    }

    public static String getCodigoPeriodo() {
        return (Sesion.getVariable("codigoPeriodo") != null) ? Sesion.getVariable("codigoPeriodo").toString() : null;
    }

    public static String getRutaImpresora() {
        return (Sesion.getVariable("rutaImpresora") != null) ? Sesion.getVariable("rutaImpresora").toString() : null;
    }

    public static Long getCodigoTipoSistemaOperativo() {
        return (Sesion.getVariable("codigoTipoSistemaOperativo") != null) ? Long.parseLong(Sesion.getVariable("codigoTipoSistemaOperativo").toString()) : null;
    }

    public static String getTipoSOServidorAppl() {
        return (Sesion.getVariable("tipoSOServidorAppl") != null) ? Sesion.getVariable("tipoSOServidorAppl").toString() : null;
    }

    public static String getRutaImpresionArchivos() {
        return (Sesion.getVariable("rutaImpresionArchivos") != null) ? Sesion.getVariable("rutaImpresionArchivos").toString() : null;
    }

    public static String getRutaSubidaArchivos() {
        return (Sesion.getVariable("rutaSubidaArchivos") != null) ? Sesion.getVariable("rutaSubidaArchivos").toString() : null;
    }
    
     public static String getTipoSOServidor() {
        return (Sesion.getVariable("tipoSOServidor") != null) ? Sesion.getVariable("tipoSOServidor").toString() : null;
    }
     
    
    
}
