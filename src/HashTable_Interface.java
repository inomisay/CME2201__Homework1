import java.time.LocalDate;

public interface HashTable_Interface<K, V> {

    void put(String key, String name, LocalDate date, String product);

    void Linear_Probing(int hash, String key, String name, LocalDate date, String product, String add_re);

    void Double_Hashing(int hash, String key, String name, LocalDate date, String product, String add_re);

    void remove(String key);

    void check_Capacity();

    void resize();

    void display();

    void display2();

    V get(String key);

}
