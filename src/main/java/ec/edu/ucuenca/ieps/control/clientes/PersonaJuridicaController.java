package ec.edu.ucuenca.ieps.control.clientes;

import ec.edu.ucuenca.ieps.modelado.clientes.PersonaJuridica;
import ec.edu.ucuenca.ieps.control.clientes.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.clientes.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.modelado.clientes.Persona;
import ec.edu.ucuenca.ieps.modelado.clientes.RepresentanteLegal;
import ec.edu.ucuenca.ieps.modelado.clientes.TipoPersona;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaJuridicaFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.RepresentanteLegalFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.TipoPersonaFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("personaJuridicaController")
@SessionScoped
public class PersonaJuridicaController implements Serializable {

    @EJB
    private ec.edu.ucuenca.ieps.negocio.clientes.PersonaJuridicaFacade ejbFacade;

    @EJB
    private PersonaFacade ejbFacadePersona;

    @EJB
    private TipoPersonaFacade ejbFacadeTiPersona;

    @EJB
    private RepresentanteLegalFacade ejbFacadeRepresentanteLegal;

    private List<PersonaJuridica> items = null;
    private PersonaJuridica selected;
    private String identificacionRepresentante;
    private Persona representante;
    private List<RepresentanteLegal> representantes;
    private RepresentanteLegal representanteSeleccionado;

    public PersonaJuridicaController() {
    }

    public PersonaJuridica getSelected() {
        return selected;
    }

    public void setSelected(PersonaJuridica selected) {
        this.selected = selected;
    }

    public void buscaPersona() {
        PersonaJuridica p = this.ejbFacade.buscarPorIdentificacion(this.selected.getPersona().getIdentificacion());
        if (p != null) {
            this.selected = p;
        }
    }

    public void buscaReprenentante() {
        representante = this.ejbFacadePersona.buscarPorIdentificacion(this.identificacionRepresentante);
    }

    public void quitarRepresentante() {
        this.representantes.remove(this.representanteSeleccionado);
    }

    protected void setEmbeddableKeys() {
    }

    @PostConstruct
    public void init() {
        System.out.println("ec.edu.ucuenca.ieps.control.clientes.PersonaJuridicaController.init()");
    }

    protected void initializeEmbeddableKey() {
    }

    private PersonaJuridicaFacade getFacade() {
        return ejbFacade;
    }

    public PersonaJuridica prepareCreate() {
        selected = new PersonaJuridica();
        this.identificacionRepresentante = "";
        selected.setPersona(new Persona());
        TipoPersona tipo = this.ejbFacadeTiPersona.find(2);
        selected.getPersona().setCodigoTipoPersona(tipo);
        this.representantes = new ArrayList<>();
        initializeEmbeddableKey();
        return selected;
    }

    public PersonaJuridica prepareEdit() {
        if (selected != null) {
            this.identificacionRepresentante = "";
            this.representantes = ejbFacadeRepresentanteLegal.buscarPorPersonaJuridicaEliminado(selected.getCodigoPersona(), "N");
            System.out.println(selected.getCodigoPersona());
            System.out.println(representantes.size());

        }
        return selected;
    }

    public void agregaRepresentante() {
        if (representante != null) {
            RepresentanteLegal rep = new RepresentanteLegal();
            rep.setCodigoPersonaJuridico(selected);
            rep.setCodigoPersonaRepresentante(representante);
            rep.setEliminado("N");
            rep.setFechaActualizacion(new Date());
            this.representantes.add(rep);
            this.representante = new Persona();
            this.identificacionRepresentante = "";
        } else {
            JsfUtil.addErrorMessage("Ingrese los datos del representante");
        }

    }

    public void create() {
        selected.getPersona().setFechaActualizacion(new Date());
        this.ejbFacadePersona.create(selected.getPersona());
        selected.setCodigoPersona(selected.getPersona().getCodigo());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle1").getString("PersonaJuridicaCreated"));
        for (RepresentanteLegal r : representantes) {
            r.setCodigoPersonaJuridico(selected);
            this.ejbFacadeRepresentanteLegal.create(r);
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        selected.getPersona().setFechaActualizacion(new Date());

        List<RepresentanteLegal> todos = ejbFacadeRepresentanteLegal.buscarPorPersonaJuridica(selected.getCodigoPersona());
        for (RepresentanteLegal actual : todos) {
            boolean existe = false;
            for (RepresentanteLegal r : representantes) {
                System.out.println(actual.getCodigoPersonaRepresentante().getCodigo().intValue());
                System.out.println(r.getCodigoPersonaRepresentante().getCodigo().intValue());
                System.out.println(actual.getCodigoPersonaRepresentante().getCodigo().intValue() == r.getCodigoPersonaRepresentante().getCodigo().intValue());
                System.out.println( actual.getEliminado());
                if (actual.getCodigoPersonaRepresentante().getCodigo().intValue() == r.getCodigoPersonaRepresentante().getCodigo().intValue()) {
                    if (actual.getEliminado().equals("S")){
                    actual.setEliminado("N");
                    actual.setFechaActualizacion(new Date());
                    System.out.println("Actualiza 1");
                    this.ejbFacadeRepresentanteLegal.edit(actual);
                    }                    
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                actual.setEliminado("S");
                actual.setFechaActualizacion(new Date());
                System.out.println("Actualiza 2");
                this.ejbFacadeRepresentanteLegal.edit(actual);
            }
        }

        for (RepresentanteLegal r : representantes) {
            boolean existe = false;
            for (RepresentanteLegal actual : todos) {
                if (actual.getCodigoPersonaRepresentante().getCodigo().intValue() == r.getCodigoPersonaRepresentante().getCodigo().intValue()) {
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                r.setEliminado("N");
                r.setCodigoPersonaJuridico(selected);
                System.out.println("Crea");
                this.ejbFacadeRepresentanteLegal.create(r);
            }
        }
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("Datosguardados"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle1").getString("PersonaJuridicaDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<PersonaJuridica> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle1").getString("PersistenceErrorOccured"));
            }
        }
    }

    public PersonaJuridica getPersonaJuridica(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<PersonaJuridica> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<PersonaJuridica> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the identificacionRepresentante
     */
    public String getIdentificacionRepresentante() {
        return identificacionRepresentante;
    }

    /**
     * @param identificacionRepresentante the identificacionRepresentante to set
     */
    public void setIdentificacionRepresentante(String identificacionRepresentante) {
        this.identificacionRepresentante = identificacionRepresentante;
    }

    /**
     * @return the representante
     */
    public Persona getRepresentante() {
        return representante;
    }

    /**
     * @param representante the representante to set
     */
    public void setRepresentante(Persona representante) {
        this.representante = representante;
    }

    /**
     * @return the representantes
     */
    public List<RepresentanteLegal> getRepresentantes() {
        return representantes;
    }

    /**
     * @param representantes the representantes to set
     */
    public void setRepresentantes(List<RepresentanteLegal> representantes) {
        this.representantes = representantes;
    }

    /**
     * @return the representanteSeleccionado
     */
    public RepresentanteLegal getRepresentanteSeleccionado() {
        return representanteSeleccionado;
    }

    /**
     * @param representanteSeleccionado the representanteSeleccionado to set
     */
    public void setRepresentanteSeleccionado(RepresentanteLegal representanteSeleccionado) {
        this.representanteSeleccionado = representanteSeleccionado;
    }

    @FacesConverter(forClass = PersonaJuridica.class)
    public static class PersonaJuridicaControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PersonaJuridicaController controller = (PersonaJuridicaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "personaJuridicaController");
            return controller.getPersonaJuridica(getKey(value));
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
            if (object instanceof PersonaJuridica) {
                PersonaJuridica o = (PersonaJuridica) object;
                return getStringKey(o.getCodigoPersona());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), PersonaJuridica.class.getName()});
                return null;
            }
        }

    }

}
