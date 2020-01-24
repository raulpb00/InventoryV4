package es.raulprieto.inventory.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.raulprieto.inventory.data.dao.DependencyDao;
import es.raulprieto.inventory.data.db.model.Dependency;

@Database(entities = {Dependency.class}, version = 1)
public abstract class InventoryDatabase extends RoomDatabase {

    public abstract DependencyDao dependencyDao();

    private static volatile InventoryDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    /**
     * GOOGLE SOLUTION: generates a problem as the context is needed in order to obtain the database
     *
     * @param context app
     * @return instance of the database
     */
    static InventoryDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * OUR SOLUTION: separating creating from obtaining
     *  Creator method
     * @param context app
     */
    public static void createDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (InventoryDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InventoryDatabase.class, "word_database")
                            .build();
                }
            }
        }
    }

    /**
     *  Getter method
     * @return instance of the database
     */
    public static InventoryDatabase getDatabase() {
        return INSTANCE;
    }
}
