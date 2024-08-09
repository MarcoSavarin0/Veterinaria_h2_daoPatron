package dao;

import java.util.List;

public interface Idao<T>{
    T guardar(T t);
    T buscarXID(Integer id);
    List<T> buscarTodos();

    
}
