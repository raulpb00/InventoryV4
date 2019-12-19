package es.raulprieto.inventory.data.db.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import es.raulprieto.inventory.data.db.model.Dependency;

public class DependencyRepository {
    private static DependencyRepository instance;
    private ArrayList<Dependency> list;

    private DependencyRepository() {
        list = new ArrayList<>();
//        initialize();
        //Debugging
        add(new Dependency("1º Ciclo Formativo", "1CFGS", "1º Desarrollo de Aplicaciones Multiplataforma", "2020", "unsplash.it/32/32"));
        add(new Dependency("2º Ciclo Formativo", "2CFGS", "2º Desarrollo de Aplicaciones Multiplataforma", "2020", "unsplash.it/32/32"));
    }

    public static DependencyRepository getInstance() {
        if (instance == null)
            instance = new DependencyRepository();

        return instance;
    }

    private void initialize() {
        add(new Dependency("1º Ciclo Formativo", "1CFGS", "1º Desarrollo de Aplicaciones Multiplataforma", "2020", "unsplash.it/32/32"));
        add(new Dependency("2º Ciclo Formativo", "2CFGS", "2º Desarrollo de Aplicaciones Multiplataforma", "2020", "unsplash.it/32/32"));
        add(new Dependency("3º Ciclo Formativo", "3CFGS", "3º Desarrollo de Aplicaciones Multiplataforma", "2018", "unsplash.it/32/32"));
        add(new Dependency("4º Ciclo Formativo", "4CFGS", "4º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("5º Ciclo Formativo", "5CFGS", "5º Desarrollo de Aplicaciones Multiplataforma", "2018", "unsplash.it/32/32"));
        add(new Dependency("6º Ciclo Formativo", "6CFGS", "6º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("7º Ciclo Formativo", "7CFGS", "7º Desarrollo de Aplicaciones Multiplataforma", "2018", "unsplash.it/32/32"));
        add(new Dependency("8º Ciclo Formativo", "8CFGS", "8º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("9º Ciclo Formativo", "9CFGS", "9º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("10º Ciclo Formativo", "10CFGS", "10º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("11º Ciclo Formativo", "11CFGS", "11º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("12º Ciclo Formativo", "12CFGS", "12º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        add(new Dependency("13º Ciclo Formativo", "13CFGS", "13º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));

    }

    /**
     * RN4.
     * Adds the dependency if the shortname does not exists already
     *
     * @param dependency to add
     * @return boolean
     */
    public boolean add(Dependency dependency) {
        if (!exists(dependency)) {
            list.add(dependency);
            return true;
        } else
            return false;
    }

    //TODO si luego usamos sort, esto no será necesario. Sólo será necesario en Adapter. Volver a cambiar a Collections la declaración de la lista.
    public boolean undo(Dependency dependency, int position) {
        if (!list.contains(dependency)) {
            list.add(position, dependency);
            return true;
        } else
            return false;
    }

    public boolean edit(Dependency dependency) {
        try {
            for (Dependency dependencyIt : list) {
                if (dependencyIt.getShortName().equals(dependency.getShortName())) {
                    dependencyIt.setName(dependency.getName());
                    dependencyIt.setDescription(dependency.getDescription());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Dependency dependency) {
        Iterator<Dependency> dependencyIterator = list.iterator();
        while (dependencyIterator.hasNext()) {
            if (dependencyIterator.next().equals(dependency)) {
                dependencyIterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Search a Dependency by its shortname as it's an unique key for Dependency Model
     *
     * @param dependency to check its shortname
     * @return ¿Already exists?
     */
    private boolean exists(Dependency dependency) {
        boolean exists = false;

        for (Dependency dependencyIt : list)
            if (dependencyIt.getShortName().equals(dependency.getShortName()))
                exists = true;

        return exists;
    }

    public Collection<Dependency> getAll() {
        return list;
    }
}
