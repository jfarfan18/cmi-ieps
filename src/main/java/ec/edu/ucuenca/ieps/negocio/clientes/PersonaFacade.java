/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.clientes;

import ec.edu.ucuenca.ieps.modelado.clientes.Persona;
import ec.edu.ucuenca.ieps.negocio.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jfarf
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    
    public Persona buscarPorIdentificacion(String identificacion) {
        Query query = em.createNamedQuery(Persona.findByIdentificacion);
        query.setParameter("identificacion", identificacion);   
        if (query.getResultList() == null ||query.getResultList().isEmpty())
            return null;
        else
            return (Persona) query.getResultList().get(0);
    } 
}
