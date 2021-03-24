package foo.study.url.repository;

import foo.study.url.domain.Url;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private static Map<Long, Url> store = new HashMap<>();

    private static final Repository instance = new Repository();

    public static Repository getInstance() {
        return instance;
    }

    private Repository() { }

    public Url save(long id, Url url) {
        url.setId(id);
        store.put(id, url);
        return url;
    }

    public Url findById(long id) {
        return store.get(id);
    }

    public List<Url> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
