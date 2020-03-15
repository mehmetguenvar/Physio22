package com.example.mehme.physio22.Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mehme.physio22.Database.daos.KategorieDao;
import com.example.mehme.physio22.Database.daos.KundenDatenDao;
import com.example.mehme.physio22.Database.daos.UebungDao;
import com.example.mehme.physio22.Database.daos.UebungFreigabeDao;
import com.example.mehme.physio22.Database.entities.Kategorie;
import com.example.mehme.physio22.Database.entities.KundenDaten;
import com.example.mehme.physio22.Database.entities.Uebung;
import com.example.mehme.physio22.Database.entities.UebungFreigabe;
import com.example.mehme.physio22.dtos.KategorieDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Kategorie.class, KundenDaten.class, Uebung.class, UebungFreigabe.class},version = 1,exportSchema = true)
public abstract class PhysioRoomDatabase extends RoomDatabase {

    public abstract KategorieDao kategorieDao();
    public abstract KundenDatenDao kundenDatenDao();
    public abstract UebungDao uebungDao();
    public abstract UebungFreigabeDao uebungFreigabeDao();

    private static volatile PhysioRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PhysioRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhysioRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhysioRoomDatabase.class, "phsyio_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
