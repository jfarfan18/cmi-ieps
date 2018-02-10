package ec.edu.ucuenca.ieps.control.clientes;

import com.google.gson.Gson;
import ec.edu.ucuenca.ieps.modelado.clientes.Cliente;
import ec.edu.ucuenca.ieps.control.util.JsfUtil;
import ec.edu.ucuenca.ieps.control.util.JsfUtil.PersistAction;
import ec.edu.ucuenca.ieps.modelado.clientes.Persona;
import ec.edu.ucuenca.ieps.modelado.clientes.PersonaJuridica;
import ec.edu.ucuenca.ieps.modelado.clientes.PersonaNatural;
import ec.edu.ucuenca.ieps.modelado.clientes.RepresentanteLegal;
import ec.edu.ucuenca.ieps.negocio.clientes.ClienteFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaJuridicaFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.PersonaNaturalFacade;
import ec.edu.ucuenca.ieps.negocio.clientes.RepresentanteLegalFacade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.event.FlowEvent;

@Named("clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    @EJB
    private ec.edu.ucuenca.ieps.negocio.clientes.ClienteFacade ejbFacade;
    @EJB
    private PersonaFacade ejbFacadePersona;
    @EJB
    private PersonaNaturalFacade ejbFacadePersonaNatural;
    @EJB
    private PersonaJuridicaFacade ejbFacadePersonaJuridica;
    @EJB
    private RepresentanteLegalFacade ejbFacadeRepresentanteLegal;
    private List<Cliente> items = null;
    private Cliente selected;
    private Persona persona;
    private PersonaNatural personaNatural;
    private PersonaJuridica personaJuridica;
    
    
    private String identificacionRepresentante;
    private Persona representante;
    private List<RepresentanteLegal> representantes;
    private RepresentanteLegal representanteSeleccionado;

    public ClienteController() {
    }

    public Cliente getSelected() {
        return selected;
    }
    
    public void buscaPersona(){
        Persona p=this.ejbFacadePersona.buscarPorIdentificacion(this.persona.getIdentificacion());
        //PersonaNatural p=this.ejbFacadePersonaNatural.buscarPorIdentificacion(this.persona.getIdentificacion());
        if (p!=null){
            this.persona=p;
            if(p.getCodigoTipoPersona().getCodigo().intValue()==1){
                this.personaNatural=this.ejbFacadePersonaNatural.buscarPorIdentificacion(this.persona.getIdentificacion());
            }
            if(p.getCodigoTipoPersona().getCodigo().intValue()==2){
                this.personaJuridica=this.ejbFacadePersonaJuridica.buscarPorIdentificacion(this.persona.getIdentificacion());
                System.out.println(persona.getCodigo());
                this.representantes = ejbFacadeRepresentanteLegal.buscarPorPersonaJuridicaEliminado(persona.getCodigo(), "N");
                System.out.println(representantes.size());
        
            }
        }
    }
    
    public void agregaRepresentante() {
        if (representante != null) {
            RepresentanteLegal rep = new RepresentanteLegal();
            rep.setCodigoPersonaJuridico(this.personaJuridica);
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
    
    public void quitarRepresentante() {
        this.representantes.remove(this.representanteSeleccionado);
    }
    
    public void buscaReprenentante() {
        representante = this.ejbFacadePersona.buscarPorIdentificacion(this.identificacionRepresentante);
    }

    public String onFlowProcess(FlowEvent event) {
        if(event.getOldStep().equals("basico") && persona.getCodigoTipoPersona().getCodigo().intValue()==1) {
            return "personaNatural";
        }else if(event.getOldStep().equals("basico") && persona.getCodigoTipoPersona().getCodigo().intValue()==2) {
            return "representante";
        }else if(event.getOldStep().equals("representante")) {    
            return "basico";
        }else if(event.getOldStep().equals("personaNatural")&&event.getNewStep().equals("representante")) {            
            return "personaNatural";
        }
        else {
            return event.getNewStep();
        }
    }
    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClienteFacade getFacade() {
        return ejbFacade;
    }

    public Cliente prepareCreate() {
        selected = new Cliente();
        this.persona=new Persona();
        this.personaNatural=new PersonaNatural();
        this.personaJuridica=new PersonaJuridica();
        initializeEmbeddableKey();
        return selected;
    }
    
    
    public void create() {
        this.selected=new Cliente();
        if (this.persona.getCodigo()!=null && this.persona.getCodigo()>0){
            if (this.persona.getCodigoTipoPersona().getCodigo().intValue()==1){
                this.updatePersonaNatural();
            }else if (this.persona.getCodigoTipoPersona().getCodigo().intValue()==2){
                this.updatePersonaJuridica();
            }
        }else{
            if (this.persona.getCodigoTipoPersona().getCodigo().equals(1)){
                System.err.println("Asigna persona");
                this.personaNatural.setPersona(this.persona);
                this.createPersonaNatural();
            }else if (this.persona.getCodigoTipoPersona().getCodigo().intValue()==2){
                this.personaJuridica.setPersona(this.persona);
                this.createPersonaJuridica();
            }
        }
        this.selected.setCodigoPersona(persona);
        this.selected.setFechaIngreso(new Date());
        this.selected.setFechaActualizacion(new Date());
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("Datosguardados"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void createPersonaJuridica() {
        this.personaJuridica.getPersona().setFechaActualizacion(new Date());
        this.ejbFacadePersona.create(this.personaJuridica.getPersona());
        this.personaJuridica.setCodigoPersona(this.personaJuridica.getPersona().getCodigo());
        this.ejbFacadePersonaJuridica.create(personaJuridica);
        for (RepresentanteLegal r : representantes) {
            r.setCodigoPersonaJuridico(this.personaJuridica);
            this.ejbFacadeRepresentanteLegal.create(r);
        }
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void createPersonaNatural() {
        this.personaNatural.getPersona().setFechaActualizacion(new Date());
        this.personaNatural.getPersona().setNombreCompleto(this.personaNatural.getPrimerApellido()+" "+
                                                this.personaNatural.getSegundoApellido()+" "+
                                                this.personaNatural.getPrimerNombre()+" "+
                                                this.personaNatural.getSegundoNombre()
                                                );
        Gson gson = new Gson();
        System.out.println(gson.toJson(this.personaNatural.getPersona()));
        this.ejbFacadePersona.create(this.personaNatural.getPersona());
        this.personaNatural.setCodigoPersona(this.personaNatural.getPersona().getCodigo());
        this.ejbFacadePersonaNatural.create(personaNatural);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
         if (this.persona.getCodigoTipoPersona().getCodigo().intValue()==1){
                this.personaNatural.setPersona(persona);
                this.createPersonaNatural();
            }else if (this.persona.getCodigoTipoPersona().getCodigo().intValue()==2){
                this.createPersonaJuridica();
        }
        
        this.selected.setCodigoPersona(persona);
        this.selected.setFechaIngreso(new Date());
        this.selected.setFechaActualizacion(new Date());
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("ClienteUpdated"));
    }
    
    public void updatePersonaNatural() {
        this.personaNatural.getPersona().setFechaActualizacion(new Date());
        this.personaNatural.getPersona().setNombreCompleto(this.personaNatural.getPrimerApellido()+" "+
                                                this.personaNatural.getSegundoApellido()+" "+
                                                this.personaNatural.getPrimerNombre()+" "+
                                                this.personaNatural.getSegundoNombre()
                                                );
        this.ejbFacadePersona.edit(this.personaNatural.getPersona());
        this.ejbFacadePersonaNatural.edit(personaNatural);
    }
    
    public void updatePersonaJuridica() {
        this.personaJuridica.getPersona().setFechaActualizacion(new Date());

        List<RepresentanteLegal> todos = ejbFacadeRepresentanteLegal.buscarPorPersonaJuridica(this.personaJuridica.getCodigoPersona());
        for (RepresentanteLegal actual : todos) {
            boolean existe = false;
            for (RepresentanteLegal r : representantes) {
                if (actual.getCodigoPersonaRepresentante().getCodigo().intValue() == r.getCodigoPersonaRepresentante().getCodigo().intValue()) {
                    if (actual.getEliminado().equals("S")){
                    actual.setEliminado("N");
                    actual.setFechaActualizacion(new Date());
                    this.ejbFacadeRepresentanteLegal.edit(actual);
                    }                    
                    existe = true;
                    break;
                }
            }
            if (!existe) {
                actual.setEliminado("S");
                actual.setFechaActualizacion(new Date());
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
                r.setCodigoPersonaJuridico(this.personaJuridica);
                this.ejbFacadeRepresentanteLegal.create(r);
            }
        }
        this.ejbFacadePersonaJuridica.edit(personaJuridica);
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("ClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cliente> getItems() {
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
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/propiedadesEtiquetas").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Cliente getCliente(java.util.Date id) {
        return getFacade().find(id);
    }

    public List<Cliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the personaNatural
     */
    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    /**
     * @param personaNatural the personaNatural to set
     */
    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
    }

    /**
     * @return the personaJuridica
     */
    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    /**
     * @param personaJuridica the personaJuridica to set
     */
    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
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

    @FacesConverter(forClass = Cliente.class)
    public static class ClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClienteController controller = (ClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clienteController");
            return controller.getCliente(getKey(value));
        }

        java.util.Date getKey(String value) {
            java.util.Date key;
            key = java.sql.Date.valueOf(value);
            return key;
        }

        String getStringKey(java.util.Date value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cliente) {
                Cliente o = (Cliente) object;
                return getStringKey(o.getFechaIngreso());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cliente.class.getName()});
                return null;
            }
        }

    }

}
