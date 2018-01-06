package ec.edu.ucuenca.ieps.control.principal;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import ec.edu.ucuenca.ieps.control.util.Arbol;
import ec.edu.ucuenca.ieps.control.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.control.util.Validacion;
import ec.edu.ucuenca.ieps.modelado.principal.ComponenteFormla;
import ec.edu.ucuenca.ieps.modelado.principal.DetalleHistorial;
import ec.edu.ucuenca.ieps.modelado.principal.Historial;
import ec.edu.ucuenca.ieps.modelado.principal.Indicador;
import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategico;
import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategicoIndicador;
import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategicoIndicadorPK;
import ec.edu.ucuenca.ieps.modelado.principal.Semaforo;
import ec.edu.ucuenca.ieps.modelado.seguridades.Usuario;
import ec.edu.ucuenca.ieps.negocio.principal.ComponenteformlaFacade;
import ec.edu.ucuenca.ieps.negocio.principal.DetallehistorialFacade;
import ec.edu.ucuenca.ieps.negocio.principal.HistorialFacade;
import ec.edu.ucuenca.ieps.negocio.principal.IndicadorFacade;
import ec.edu.ucuenca.ieps.negocio.principal.ObjetivoestrategicoFacade;
import ec.edu.ucuenca.ieps.negocio.principal.ObjetivoestrategicoindicadorFacade;
import ec.edu.ucuenca.ieps.negocio.principal.SemaforoFacade;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@ManagedBean(name = "objetivoestrategicoController")
@SessionScoped
public class ObjetivoestrategicoController implements Serializable {

    @EJB
    private ObjetivoestrategicoindicadorFacade ejbFacade1;
    @EJB
    private ObjetivoestrategicoFacade ejbFacade;
    @EJB
    private SemaforoFacade ejbFacadeSemaforo;

    public ObjetivoestrategicoindicadorFacade getEjbFacade1() {
        return ejbFacade1;
    }

    public void setEjbFacade1(ObjetivoestrategicoindicadorFacade ejbFacade1) {
        this.ejbFacade1 = ejbFacade1;
    }
    @EJB
    private IndicadorFacade ejbFacadeIndicador;
    @EJB
    private ObjetivoestrategicoindicadorFacade ejbFacadeObjEstInd;
    @EJB
    private HistorialFacade ejbFacadeHistorial;
    @EJB
    private ComponenteformlaFacade ejbFacadeComp;
    @EJB
    private DetallehistorialFacade ejbFacadeDeta;
    private List<ObjetivoEstrategico> items = null;
    private List<ObjetivoEstrategicoIndicador> items2 = null;
    private ObjetivoEstrategico selected;
    private ObjetivoEstrategicoIndicador metaSeleccionada;
    private ObjetivoEstrategicoIndicador nuevoObjetivoEstrategicoIndicador;
    private Semaforo verde;
    private Semaforo naranja;
    private Semaforo rojo;
    int idProvisional;
    private List<Historial> histrorial;
    private Historial nuevoHistorial;
    private double nuevoVolorIndocador;
    private Date fecha;
    private LineChartModel dateModel;
    private List<String> noms = null;
    Arbol arbol;
    private List<DetalleHistorial> itemsdetalle;
    
    public List<String> getNoms() {
        return noms;
    }

    public void setNoms(List<String> noms) {
        this.noms = noms;
    }

    public ObjetivoestrategicoController() {
    }

    @PostConstruct
    public void init() {
        histrorial = new ArrayList<>();
        createDateModel();
        ObjetivoEstrategico preparaNuevo = preparaNuevo();
        noms = new ArrayList();
        if (items == null) {
            items = getFacade().findAll();
            getItemsNombre();
        }
        fecha=new Date();
    }
public void generarReporte()
    {
        
//        System.out.println("holissssaaaaawww");
//       items2 = getEjbFacade1().findAll();
//       InformeObjEs inf=new InformeObjEs("Objetivos Estrategicos",items2);
//       inf.generarInforme();
    }
    public void evaluar() {
        itemsdetalle = new ArrayList<DetalleHistorial>();
        DetalleHistorial det;
        for (ComponenteFormla com : metaSeleccionada.getIndicador().getComponenteFormlaList()) {
            det = new DetalleHistorial();
            det.setIdcomponenteformula(com);
            itemsdetalle.add(det);
        }
        System.out.println("cargo");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BSC/faces/objetivoestrategico/Evaluar.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ObjetivoestrategicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void evaluarInd() {
        System.out.println("cargo");
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/BSC/faces/objetivoestrategico/EvaluarInd.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(ObjetivoestrategicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Evaluar() {
        System.out.println("cargo ");

    }

    public void calculaIndicador() {
        boolean calucular = true;
        System.out.println("inicio");
        for (DetalleHistorial d : itemsdetalle) {
            System.out.println("Valor "+d.getValor());
            if (d.getValor() == null)
            calucular = false;
        }
        System.out.println(calucular);
        if (calucular) {
            if (!metaSeleccionada.getIndicador().getFormula().equals("")) {
                String formula = metaSeleccionada.getIndicador().getFormula().replaceAll("\\s", "").trim();
                Validacion cadena = new Validacion(formula);
                if (cadena.Exp_Valida()) {
                    String Exp = formula.trim();
                    //Borra parentesis del inicio y fin si toda la expresion esta entre parentesis
                    if (Exp.charAt(0) == '(' && Exp.charAt(Exp.length() - 1) == ')') {
                        int p = 1, i = 1;
                        while (p != 0) {
                            if (Exp.charAt(i) == '(') {
                                p++;
                            }
                            if (Exp.charAt(i) == ')') {
                                p--;
                            }
                            i++;
                        }
                        if (i == Exp.length()) {
                            Exp = Exp.substring(1, Exp.length() - 1);
                        }
                    }
                    arbol = new Arbol(Exp);
                    arbol.Formar_Arbol();
                    arbol.reemplazarValores(itemsdetalle);
                    List term = arbol.listarTerminos();
                    nuevoObjetivoEstrategicoIndicador.getIndicador().setComponenteFormlaList(new ArrayList());
                    nuevoVolorIndocador= Double.parseDouble(arbol.Calcular());
                    for (int i = 0; i < term.size(); i++) {
                        System.out.println("term " + term.get(i));
                        
                    }
                }
            }
        }
    }

    public void obtieneComponentes() {
        if (nuevoObjetivoEstrategicoIndicador.getIndicador().getFormula() != null) {
            if (!nuevoObjetivoEstrategicoIndicador.getIndicador().getFormula().equals("")) {
                String formula = nuevoObjetivoEstrategicoIndicador.getIndicador().getFormula().replaceAll("\\s", "").trim();
                Validacion cadena = new Validacion(formula);
                if (cadena.Exp_Valida()) {
                    String Exp = formula.trim();
                    //Borra parentesis del inicio y fin si toda la expresion esta entre parentesis
                    if (Exp.charAt(0) == '(' && Exp.charAt(Exp.length() - 1) == ')') {
                        int p = 1, i = 1;
                        while (p != 0) {
                            if (Exp.charAt(i) == '(') {
                                p++;
                            }
                            if (Exp.charAt(i) == ')') {
                                p--;
                            }
                            i++;
                        }
                        if (i == Exp.length()) {
                            Exp = Exp.substring(1, Exp.length() - 1);
                        }
                    }
                    arbol = new Arbol(Exp);
                    arbol.Formar_Arbol();
                    List term = arbol.listarTerminos();
                    nuevoObjetivoEstrategicoIndicador.getIndicador().setComponenteFormlaList(new ArrayList());
                    for (int i = 0; i < term.size(); i++) {
                        System.out.println("term " + term.get(i));
                        ComponenteFormla c = new ComponenteFormla();
                        c.setDescripcion((String) term.get(i));
                        c.setIdindicador(nuevoObjetivoEstrategicoIndicador.getIndicador());
                        nuevoObjetivoEstrategicoIndicador.getIndicador().getComponenteFormlaList().add(c);
                    }
                }
            }
        }
    }

    public void getItemsNombre() {
        for (int i = 0; i < items.size(); i++) {
            noms.add(items.get(i).getNombre());
        }
    }

    public ObjetivoEstrategico preparaEdicion() {
        idProvisional = 0;
        setRojo(new Semaforo());
        getRojo().setColor('r');
        getRojo().setIdSemaforo(0);
        setNaranja(new Semaforo());
        getNaranja().setColor('n');
        getNaranja().setIdSemaforo(0);
        setVerde(new Semaforo());
        getVerde().setColor('v');
        getVerde().setIdSemaforo(0);
        nuevoObjetivoEstrategicoIndicador = new ObjetivoEstrategicoIndicador();
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategico(selected);
        nuevoObjetivoEstrategicoIndicador.setIndicador(new Indicador());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdUsuarioResponsable(new Usuario());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdIndicador(0);
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategicoIndicadorPK(new ObjetivoEstrategicoIndicadorPK(0, idProvisional));
        System.out.println("META " + nuevoObjetivoEstrategicoIndicador.getMeta());
        //System.out.println("Indicadores " + selected.getObjetivoEstrategicoIndicadorList().size());
        return selected;
    }

    public void preparaHistorial() {
        nuevoHistorial = new Historial();
    }

    public void guardarHistorial() {
        nuevoHistorial = new Historial();
        nuevoHistorial.setValor(new BigDecimal(nuevoVolorIndocador));
        nuevoHistorial.setIdIndicador(metaSeleccionada.getIndicador());
        nuevoHistorial.setFechaMedicion(fecha);
        ejbFacadeHistorial.create(nuevoHistorial);
        for (int i=0;i<itemsdetalle.size();i++){
            itemsdetalle.get(i).setIdhistorial(nuevoHistorial);
            itemsdetalle.get(i).setIddetallehistorial(0);
            ejbFacadeDeta.create(itemsdetalle.get(i));
        }
        metaSeleccionada.getIndicador().setHistorialList(histrorial);
        ejbFacadeIndicador.edit(metaSeleccionada.getIndicador());
        ejbFacadeObjEstInd.edit(metaSeleccionada);
        ejbFacade.edit(metaSeleccionada.getObjetivoEstrategico());
        nuevoVolorIndocador = 0;
    }

    public ObjetivoEstrategico preparaNuevo() {
        idProvisional = 0;
        setRojo(new Semaforo());
        getRojo().setColor('r');
        getRojo().setIdSemaforo(0);
        setNaranja(new Semaforo());
        getNaranja().setColor('n');
        getNaranja().setIdSemaforo(0);
        setVerde(new Semaforo());
        getVerde().setColor('v');
        getVerde().setIdSemaforo(0);
        nuevoObjetivoEstrategicoIndicador = new ObjetivoEstrategicoIndicador();
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategico(selected);
        nuevoObjetivoEstrategicoIndicador.setIndicador(new Indicador());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdUsuarioResponsable(new Usuario());
        //System.out.println(nuevoObjetivoEstrategicoIndicador.getIndicador().getIdPersonaResponsable());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdIndicador(0);
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategicoIndicadorPK(new ObjetivoEstrategicoIndicadorPK(0, idProvisional));
        return this.prepareCreate();
    }

    public void agregarIndicador() {
        List semaforos = new ArrayList<Semaforo>();
        semaforos.add(getVerde());
        semaforos.add(getNaranja());
        semaforos.add(getRojo());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setSemaforoList(semaforos);
        this.selected.getObjetivoEstrategicoIndicadorList().add(nuevoObjetivoEstrategicoIndicador);
        nuevoObjetivoEstrategicoIndicador = new ObjetivoEstrategicoIndicador();
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategico(selected);
        nuevoObjetivoEstrategicoIndicador.setIndicador(new Indicador());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdUsuarioResponsable(new Usuario());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdIndicador(0);
        idProvisional++;
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategicoIndicadorPK(new ObjetivoEstrategicoIndicadorPK(0, idProvisional));
        setRojo(new Semaforo());
        getRojo().setColor('r');
        getRojo().setIdSemaforo(0);
        setNaranja(new Semaforo());
        getNaranja().setColor('n');
        getNaranja().setIdSemaforo(0);
        setVerde(new Semaforo());
        getVerde().setColor('v');
        getVerde().setIdSemaforo(0);
    }

    public void agregarIndicadorEdicion() {
        List semaforos = new ArrayList<Semaforo>();
        semaforos.add(getVerde());
        semaforos.add(getNaranja());
        semaforos.add(getRojo());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setSemaforoList(semaforos);
        nuevoObjetivoEstrategicoIndicador.getIndicador().setSemaforoList(null);
        List<ComponenteFormla> componentes = nuevoObjetivoEstrategicoIndicador.getIndicador().getComponenteFormlaList();
        nuevoObjetivoEstrategicoIndicador.getIndicador().setComponenteFormlaList(null);
        ejbFacadeIndicador.create(nuevoObjetivoEstrategicoIndicador.getIndicador());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setComponenteFormlaList(componentes);
        for (ComponenteFormla c : componentes) {
            c.setIdcomponenteformla(0);
            c.setIdindicador(nuevoObjetivoEstrategicoIndicador.getIndicador());
            ejbFacadeComp.create(c);
        }
        ejbFacadeIndicador.edit(nuevoObjetivoEstrategicoIndicador.getIndicador());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setSemaforoList(semaforos);
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategicoIndicadorPK(new ObjetivoEstrategicoIndicadorPK(
                selected.getIdObjetivoEstrategico(),
                nuevoObjetivoEstrategicoIndicador.getIndicador().getIdIndicador()));
        for (int i = 0; i < semaforos.size(); i++) {
            Semaforo s = (Semaforo) semaforos.get(i);
            s.setIdIndicador(nuevoObjetivoEstrategicoIndicador.getIndicador());
            System.out.println("Gusrdo semanforo");
            ejbFacadeSemaforo.create(s);
        }
        this.ejbFacadeObjEstInd.create(nuevoObjetivoEstrategicoIndicador);
        this.selected.getObjetivoEstrategicoIndicadorList().add(nuevoObjetivoEstrategicoIndicador);
        nuevoObjetivoEstrategicoIndicador = new ObjetivoEstrategicoIndicador();
        nuevoObjetivoEstrategicoIndicador.setObjetivoEstrategico(selected);
        nuevoObjetivoEstrategicoIndicador.setIndicador(new Indicador());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdUsuarioResponsable(new Usuario());
        nuevoObjetivoEstrategicoIndicador.getIndicador().setIdIndicador(0);
        setRojo(new Semaforo());
        getRojo().setColor('r');
        getRojo().setIdSemaforo(0);
        setNaranja(new Semaforo());
        getNaranja().setColor('n');
        getNaranja().setIdSemaforo(0);
        setVerde(new Semaforo());
        getVerde().setColor('v');
        getVerde().setIdSemaforo(0);
        ejbFacade.edit(selected);
    }

    public void verDetalleObjEst() {
        for (Semaforo s : nuevoObjetivoEstrategicoIndicador.getIndicador().getSemaforoList()) {
            if (s.getColor() == 'v') {
                verde = s;
            }
            if (s.getColor() == 'n') {
                naranja = s;
            }
            if (s.getColor() == 'r') {
                rojo = s;
            }
        }
    }

    public void eliminarIndicador() {
        if (metaSeleccionada != null) {
            System.out.println("META " + metaSeleccionada);
            selected.getObjetivoEstrategicoIndicadorList().remove(this.metaSeleccionada);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seleccione un item de la lista"));
        }
    }

    public void eliminarIndicadorEdicion() {
        if (metaSeleccionada != null) {
            System.out.println("META " + metaSeleccionada);
            System.out.println(metaSeleccionada);
            selected.getObjetivoEstrategicoIndicadorList().remove(this.metaSeleccionada);
            ejbFacadeObjEstInd.remove(metaSeleccionada);
            ejbFacade.edit(selected);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seleccione un item de la lista"));
        }
    }

    public void editarIndicador() {
        if (metaSeleccionada != null) {
            System.out.println("META " + metaSeleccionada);
            selected.getObjetivoEstrategicoIndicadorList().remove(this.metaSeleccionada);
            nuevoObjetivoEstrategicoIndicador = metaSeleccionada;
            for (Semaforo s : metaSeleccionada.getIndicador().getSemaforoList()) {
                if (s.getColor() == 'v') {
                    verde = s;
                }
                if (s.getColor() == 'n') {
                    naranja = s;
                }
                if (s.getColor() == 'r') {
                    rojo = s;
                }
            }
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seleccione un item de la lista"));
        }
    }

    public void editarIndicadorEdicion() {
        if (metaSeleccionada != null) {
            System.out.println("META " + metaSeleccionada);
            selected.getObjetivoEstrategicoIndicadorList().remove(this.metaSeleccionada);
            nuevoObjetivoEstrategicoIndicador = metaSeleccionada;
            System.out.println(metaSeleccionada);
            System.err.println("cuenta " + metaSeleccionada.getIndicador().getSemaforoList().size());
            for (Semaforo s : metaSeleccionada.getIndicador().getSemaforoList()) {
                if (s.getColor() == 'v') {
                    verde = s;
                }
                if (s.getColor() == 'n') {
                    naranja = s;
                }
                if (s.getColor() == 'r') {
                    rojo = s;
                }
            }
            ejbFacadeObjEstInd.remove(metaSeleccionada);
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage("Seleccione un item de la lista"));
        }
    }

    private void createDateModel() {
        System.out.println("INICIA HISTORIAL");
        dateModel = new LineChartModel();
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("Historial");

        for (int i = 0; i < histrorial.size(); i++) {
            String fecha = String.valueOf(histrorial.get(i).getFechaMedicion().getYear() + 1900) + "-"
                    + String.valueOf(histrorial.get(i).getFechaMedicion().getMonth() + 1) + "-"
                    + String.valueOf(histrorial.get(i).getFechaMedicion().getDate());
            series1.set(fecha, histrorial.get(i).getValor());
        }

        dateModel.addSeries(series1);

        dateModel.setTitle("HISTORIAL");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("VALOR");
        DateAxis axis = new DateAxis("FECHA");
        axis.setTickAngle(-50);
        String fecha = String.valueOf(new Date().getYear() + 1900) + "-"
                + String.valueOf(new Date().getMonth() + 1) + "-"
                + String.valueOf(new Date().getDate());
        System.out.println("FECHA LIMITE " + fecha);
        axis.setMax(fecha);
        axis.setTickFormat("%#d %b, %y");

        dateModel.getAxes().put(AxisType.X, axis);
    }

    public ObjetivoEstrategico getSelected() {
        return selected;
    }

    public void setSelected(ObjetivoEstrategico selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ObjetivoestrategicoFacade getFacade() {
        return ejbFacade;
    }

    public ObjetivoEstrategico prepareCreate() {
        selected = new ObjetivoEstrategico();
        initializeEmbeddableKey();
        selected.setObjetivoEstrategicoIndicadorList(new ArrayList<ObjetivoEstrategicoIndicador>());
        return selected;
    }

    public void create() {
        List<ObjetivoEstrategicoIndicador> objEstIndList = selected.getObjetivoEstrategicoIndicadorList();
        selected.setObjetivoEstrategicoIndicadorList(null);
        ejbFacade.create(selected);
        for (ObjetivoEstrategicoIndicador objEstInd : objEstIndList) {
            objEstInd.getIndicador().setIdIndicador(new Integer(3));
            objEstInd.getIndicador().setObjetivoEstrategicoIndicadorList(null);
            List<Semaforo> semList = objEstInd.getIndicador().getSemaforoList();
            objEstInd.getIndicador().setSemaforoList(null);
            List<ComponenteFormla> componentes = objEstInd.getIndicador().getComponenteFormlaList();
            objEstInd.getIndicador().setComponenteFormlaList(null);
            ejbFacadeIndicador.create(objEstInd.getIndicador());
            objEstInd.getIndicador().setComponenteFormlaList(componentes);
            ejbFacadeIndicador.edit(objEstInd.getIndicador());
            objEstInd.setObjetivoEstrategicoIndicadorPK(new ObjetivoEstrategicoIndicadorPK(selected.getIdObjetivoEstrategico(), objEstInd.getIndicador().getIdIndicador()));
            for (Semaforo sem : semList) {
                sem.setIdIndicador(objEstInd.getIndicador());
                ejbFacadeSemaforo.edit(sem);
            }
            objEstInd.getIndicador().setSemaforoList(semList);
            objEstInd.setObjetivoEstrategico(selected);
            ejbFacadeObjEstInd.edit(objEstInd);
        }
        selected.setObjetivoEstrategicoIndicadorList(objEstIndList);
        ejbFacade.edit(selected);
    }

    public void update() {
        persist(PersistAction.UPDATE, "Actualizado");
    }

    public void destroy() {
        persist(PersistAction.DELETE, "Eliminado");
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ObjetivoEstrategico> getItems() {
        items = getFacade().findAll();
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<ObjetivoEstrategico> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ObjetivoEstrategico> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the metaSeleccionada
     */
    public ObjetivoEstrategicoIndicador getMetaSeleccionada() {
        return metaSeleccionada;
    }

    /**
     * @param metaSeleccionada the metaSeleccionada to set
     */
    public void setMetaSeleccionada(ObjetivoEstrategicoIndicador metaSeleccionada) {
        this.metaSeleccionada = metaSeleccionada;
    }

    /**
     * @return the nuevoObjetivoEstrategicoIndicador
     */
    public ObjetivoEstrategicoIndicador getNuevoObjetivoEstrategicoIndicador() {
        return nuevoObjetivoEstrategicoIndicador;
    }

    /**
     * @param nuevoObjetivoEstrategicoIndicador the
     * nuevoObjetivoEstrategicoIndicador to set
     */
    public void setNuevoObjetivoEstrategicoIndicador(ObjetivoEstrategicoIndicador nuevoObjetivoEstrategicoIndicador) {
        this.nuevoObjetivoEstrategicoIndicador = nuevoObjetivoEstrategicoIndicador;
    }

    /**
     * @return the verde
     */
    public Semaforo getVerde() {
        return verde;
    }

    /**
     * @param verde the verde to set
     */
    public void setVerde(Semaforo verde) {
        this.verde = verde;
    }

    /**
     * @return the naranja
     */
    public Semaforo getNaranja() {
        return naranja;
    }

    /**
     * @param naranja the naranja to set
     */
    public void setNaranja(Semaforo naranja) {
        this.naranja = naranja;
    }

    /**
     * @return the rojo
     */
    public Semaforo getRojo() {
        return rojo;
    }

    /**
     * @param rojo the rojo to set
     */
    public void setRojo(Semaforo rojo) {
        this.rojo = rojo;
    }

    /**
     * @return the histrorial
     */
    public List<Historial> getHistrorial() {
        if (metaSeleccionada != null) {
            System.out.println("INDICADOR " + metaSeleccionada.getIndicador().getIdIndicador());
            histrorial = ejbFacadeHistorial.getSemaforosIndicador(metaSeleccionada.getIndicador().getIdIndicador());
            this.createDateModel();
            return histrorial;
        } else {
            return null;
        }
    }

    /**
     * @param histrorial the histrorial to set
     */
    public void setHistrorial(List<Historial> histrorial) {
        this.histrorial = histrorial;
    }

    /**
     * @return the nuevoHistorial
     */
    public Historial getNuevoHistorial() {
        return nuevoHistorial;
    }

    /**
     * @param nuevoHistorial the nuevoHistorial to set
     */
    public void setNuevoHistorial(Historial nuevoHistorial) {
        this.nuevoHistorial = nuevoHistorial;
    }

    /**
     * @return the nuevoVolorIndocador
     */
    public double getNuevoVolorIndocador() {
        return nuevoVolorIndocador;
    }

    /**
     * @param nuevoVolorIndocador the nuevoVolorIndocador to set
     */
    public void setNuevoVolorIndocador(double nuevoVolorIndocador) {
        this.nuevoVolorIndocador = nuevoVolorIndocador;
    }

    /**
     * @return the dateModel
     */
    public LineChartModel getDateModel() {
        return dateModel;
    }

    /**
     * @param dateModel the dateModel to set
     */
    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }

    /**
     * @return the itemsdetalle
     */
    public List<DetalleHistorial> getItemsdetalle() {
        return itemsdetalle;
    }

    /**
     * @param itemsdetalle the itemsdetalle to set
     */
    public void setItemsdetalle(List<DetalleHistorial> itemsdetalle) {
        this.itemsdetalle = itemsdetalle;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @FacesConverter(forClass = ObjetivoEstrategico.class)
    public static class ObjetivoEstrategicoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ObjetivoestrategicoController controller = (ObjetivoestrategicoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "objetivoestrategicoController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ObjetivoEstrategico) {
                ObjetivoEstrategico o = (ObjetivoEstrategico) object;
                return getStringKey(o.getIdObjetivoEstrategico());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ObjetivoEstrategico.class.getName()});
                return null;
            }
        }

    }

}
