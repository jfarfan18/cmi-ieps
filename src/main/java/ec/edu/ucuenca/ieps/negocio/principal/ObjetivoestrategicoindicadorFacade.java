/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.principal;

import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategicoIndicador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.edu.ucuenca.ieps.negocio.AbstractFacade;

/**
 *
 * @author Farfan
 */
@Stateless
public class ObjetivoestrategicoindicadorFacade extends AbstractFacade<ObjetivoEstrategicoIndicador> {
    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoestrategicoindicadorFacade() {
        super(ObjetivoEstrategicoIndicador.class);
    }
    
    public List<ObjetivoEstrategicoIndicador> getItemsMetas(int  idObjetivoEstrategico) {
        Query query = this.em.createNamedQuery(ObjetivoEstrategicoIndicador.findByIdObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico",idObjetivoEstrategico);
        return query.getResultList();
    }
    
}
