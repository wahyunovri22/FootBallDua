package com.sc.semicolon.footballdua.Helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by cis on 30/07/2018.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.LAGA_ID to TEXT + UNIQUE,
                Favorite.TEAM_HOME to TEXT,
                Favorite.TEAM_AWAY to TEXT,
                Favorite.SCORE_HOME to TEXT,
                Favorite.SCORE_AWAY to TEXT,
                Favorite.FHOME to TEXT,
                Favorite.FAWAY to TEXT,
                Favorite.GOALHOME to TEXT,
                Favorite.GOALAWAY to TEXT,
                Favorite.SHOTHOME to TEXT,
                Favorite.SHOTAWAY to TEXT,
                Favorite.KIPERHOME to TEXT,
                Favorite.KIPER_AWAY to TEXT,
                Favorite.DEFENDHOME to TEXT,
                Favorite.DEFEND_AWAY to TEXT,
                Favorite.MIDLEHOME to TEXT,
                Favorite.MIDLE_AWAY to TEXT,
                Favorite.FORWARDHOME to TEXT,
                Favorite.FORWARDAWAY to TEXT,
                Favorite.IDHOME to TEXT,
                Favorite.IDAWAY to TEXT,
                Favorite.TANGGAL to TEXT,
                Favorite.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)