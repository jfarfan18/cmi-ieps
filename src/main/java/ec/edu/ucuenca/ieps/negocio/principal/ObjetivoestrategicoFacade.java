/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.ucuenca.ieps.negocio.principal;

import ec.edu.ucuenca.ieps.modelado.principal.ObjetivoEstrategico;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ec.edu.ucuenca.ieps.negocio.AbstractFacade;;


/**
 *
 * @author Farfan
 */
@Stateless
public class ObjetivoestrategicoFacade extends AbstractFacade<ObjetivoEstrategico> {
    @PersistenceContext(unitName = "com.mycompany_cmi_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoestrategicoFacade() {
        super(ObjetivoEstrategico.class);
    }
    
    public ObjetivoEstrategico getSelected(int  idObjetivoEstrategico) {
        List<ObjetivoEstrategico> res;
        Query query = this.em.createNamedQuery(ObjetivoEstrategico.findByIdObjetivoEstrategico);
        query.setParameter("idObjetivoEstrategico",idObjetivoEstrategico);
        res=query.getResultList();
        if (res.size()>0)
            return res.get(0);
        else
            return null;
    }
    

}
