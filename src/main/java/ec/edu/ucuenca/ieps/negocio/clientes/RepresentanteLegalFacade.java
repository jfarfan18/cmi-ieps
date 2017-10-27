/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.clientes;

import ec.edu.ucuenca.ieps.modelado.clientes.RepresentanteLegal;
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
public class RepresentanteLegalFacade extends AbstractFacade<RepresentanteLegal> {

    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RepresentanteLegalFacade() {
        super(RepresentanteLegal.class);
    }
    
    public List<RepresentanteLegal> buscarPorPersonaJuridica(Integer codigoRepresentante) {
        Query query = em.createNamedQuery(RepresentanteLegal.findByCodigoPersonaJuridica);
        query.setParameter("codigoRepresentante", codigoRepresentante);  
        return query.getResultList();
    } 
    
    public List<RepresentanteLegal> buscarPorPersonaJuridicaEliminado(Integer codigoRepresentante,String eliminado) {
        Query query = em.createNamedQuery(RepresentanteLegal.findByCodigoPersonaJuridicaEliminado);
        query.setParameter("codigoRepresentante", codigoRepresentante);  
        query.setParameter("eliminado", eliminado);  
        return query.getResultList();
    } 
}
