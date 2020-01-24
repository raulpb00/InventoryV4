package es.raulprieto.inventory.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.raulprieto.inventory.data.db.model.Dependency;

@Dao
public interface DependencyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Long insert (Dependency dependency);

    @Delete
    void delete (Dependency dependency);

    /*@Query("DELETE FROM DEPENDENCY")
    void deleteAll();*/

    @Update
    void update(Dependency dependency);

    @Query("SELECT * FROM dependency ORDER BY name ASC")
    List<Dependency> getAll();

    @Query("SELECT * FROM dependency WHERE shortname=:shortName")
    Dependency findByShortName(String shortName);

    @Query("SELECT count(*) FROM DEPENDENCY")
    int getCount();
}
