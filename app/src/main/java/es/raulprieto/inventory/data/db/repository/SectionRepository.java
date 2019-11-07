package es.raulprieto.inventory.data.db.repository;

import java.util.ArrayList;
import java.util.Collection;

import es.raulprieto.inventory.data.db.model.Section;

public class SectionRepository {

    private Collection<Section> list;
    private static SectionRepository repository;

    /**
     * It is initialized in the following block all the class
     * static properties without having to do it inside a static
     * method.
     * It is avoided to check if it's null
     */
    static {
        repository = new SectionRepository();
    }

    private SectionRepository() {
        list = new ArrayList<Section>();
        initialize();
    }

    private void initialize() {
        add(new Section("1ª Fila 1ºCFGS", "1F1CFGS","1CFGS", "1ª Fila de 1ºCFGS", "unsplash.it/32/32"));
        add(new Section("2ª Fila 1ºCFGS", "2F1CFGS","1CFGS", "2ª Fila de 1ºCFGS", "unsplash.it/32/32"));
        add(new Section("3ª Fila 1ºCFGS", "3F1CFGS", "1CFGS","3ª Fila de 1ºCFGS", "unsplash.it/32/32"));
        add(new Section("4ª Fila 1ºCFGS", "4F1CFGS","1CFGS", "4ª Fila de 1ºCFGS", "unsplash.it/32/32"));
        add(new Section("5ª Fila 1ºCFGS", "5F1CFGS","1CFGS", "5ª Fila de 1ºCFGS", "unsplash.it/32/32"));
        add(new Section("6ª Fila 1ºCFGS", "6F1CFGS","1CFGS", "6ª Fila de 1ºCFGS", "unsplash.it/32/32"));
        add(new Section("1ª Fila 2ºCFGS", "1F2CFGS","2CFGS", "1ª Fila de 2ºCFGS", "unsplash.it/32/32"));
        add(new Section("2ª Fila 2ºCFGS", "2F2CFGS","2CFGS", "2ª Fila de 2ºCFGS", "unsplash.it/32/32"));
        add(new Section("3ª Fila 2ºCFGS", "3F2CFGS","2CFGS", "3ª Fila de 2ºCFGS", "unsplash.it/32/32"));
        add(new Section("4ª Fila 2ºCFGS", "4F2CFGS","2CFGS", "4ª Fila de 2ºCFGS", "unsplash.it/32/32"));
        add(new Section("5ª Fila 2ºCFGS", "5F2CFGS","2CFGS", "5ª Fila de 2ºCFGS", "unsplash.it/32/32"));
        add(new Section("6ª Fila 2ºCFGS", "6F2CFGS","2CFGS", "6ª Fila de 2ºCFGS", "unsplash.it/32/32"));
        add(new Section("7ª Fila 2ºCFGS", "7F2CFGS","2CFGS", "7ª Fila de 2ºCFGS", "unsplash.it/32/32"));

    }

    public void add(Section section) {
        list.add(section);
    }
}
