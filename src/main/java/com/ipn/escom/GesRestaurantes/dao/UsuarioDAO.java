package com.ipn.escom.GesRestaurantes.dao;

import com.ipn.escom.GesRestaurantes.modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public void addUsuario(Usuario usuario) {
        sessionFactory.getCurrentSession().saveOrUpdate(usuario);

    }

    @SuppressWarnings("unchecked")
    public List<Usuario> getAllUsuarios() {

        return sessionFactory.getCurrentSession().createQuery("from usuarios")
                .list();
    }


    public void deleteUsuario(Integer usuarioId) {
        Usuario usuario = (Usuario) sessionFactory.getCurrentSession().load(
                Usuario.class, usuarioId);
        if (null != usuario) {
            this.sessionFactory.getCurrentSession().delete(usuario);
        }

    }

    public Usuario getUsuario(int usaid) {
        return (Usuario) sessionFactory.getCurrentSession().get(
                Usuario.class, usaid);
    }


    public Usuario updateUsuario(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
        return usuario;
    }

    public Usuario getUserByCURP(String email) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);
        Usuario usuario = (Usuario) criteria.add(Restrictions.eq("email", email))
                .uniqueResult();
        return usuario;
    }

    public Long getCountUsers() {
        return (Long) sessionFactory.getCurrentSession().createQuery("select count(*) from Usuario").uniqueResult();
    }


}
