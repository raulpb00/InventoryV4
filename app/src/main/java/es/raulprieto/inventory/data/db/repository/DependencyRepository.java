package es.raulprieto.inventory.data.db.repository;

import java.util.ArrayList;
import java.util.Collection;

import es.raulprieto.inventory.data.db.model.Dependency;

public class DependencyRepository {
    private static DependencyRepository instance;
    private Collection<Dependency> list;

    private DependencyRepository(){
        list = new ArrayList<>();
        initialize();
    }
    public static DependencyRepository getInstance(){
        if (instance == null)
            instance = new DependencyRepository();

        return instance;
    }

    private void initialize() {
        add(new Dependency( "1º Ciclo Formativo", "1CFGS", "1º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "2º Ciclo Formativo", "2CFGS", "2º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "3º Ciclo Formativo", "3CFGS", "3º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "4º Ciclo Formativo", "4CFGS", "4º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "5º Ciclo Formativo", "5CFGS", "5º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "6º Ciclo Formativo", "6CFGS", "6º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "7º Ciclo Formativo", "7CFGS", "7º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "8º Ciclo Formativo", "8CFGS", "8º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "9º Ciclo Formativo", "9CFGS", "9º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "10º Ciclo Formativo", "10CFGS", "10º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "11º Ciclo Formativo", "11CFGS", "11º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "12º Ciclo Formativo", "12CFGS", "12º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));
        add(new Dependency( "13º Ciclo Formativo", "13CFGS", "13º Desarrollo de Aplicaciones Multiplataforma", "unsplash.it/32/32"));

    }

    private void add(Dependency dependency){
        list.add(dependency);
    }

    public Collection<Dependency> getAll() {
        return list;
    }
}
