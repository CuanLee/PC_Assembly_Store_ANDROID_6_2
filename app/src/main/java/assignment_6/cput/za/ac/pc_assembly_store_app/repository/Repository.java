package assignment_6.cput.za.ac.pc_assembly_store_app.repository;

import java.util.Set;

/**
 * Created by CuanL on 19/04/2016.
 */
public interface Repository<E, ID> {
    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}
