package com.sc.semicolon.footballdua.Activity

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import com.sc.semicolon.footballdua.Helper.Favorite
import com.sc.semicolon.footballdua.Helper.database
import com.sc.semicolon.footballdua.Model.Prev
import com.sc.semicolon.footballdua.R
import com.sc.semicolon.footballdua.Retrofit.Client
import com.sc.semicolon.footballdua.Retrofit.Service
import kotlinx.android.synthetic.main.activity_detail_prev.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast
import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class DetailFavoritActivity : AppCompatActivity() {

    private var items: MutableList<Prev> = mutableListOf()
    private var menuItem: Menu? = null
    //    private var isFavorite: Boolean = false
    private var nilai:String = "not"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_favorit)

        Awal()
    }

    private fun Awal(){
        favoriteState()

        tv_detail_team_home.text = intent.getStringExtra("teamHome")
        tv_detail_team_away.text = intent.getStringExtra("teamAway")
        tv_detail_score_home.text = intent.getStringExtra("scoreHome")
        tv_detail_score_away.text = intent.getStringExtra("scoreAway")
        tv_formasi_home.text = intent.getStringExtra("formasiHome")
        tv_formasi_away.text = intent.getStringExtra("formasiAway")
        tv_goal_home.text = intent.getStringExtra("goalHome")
        tv_goal_away.text = intent.getStringExtra("goalAway")
        tv_shots_home.text = intent.getStringExtra("shotsHome")
        tv_shots_away.text = intent.getStringExtra("shotsAway" )
        tv_keeper_home.text = intent.getStringExtra("kiperHome")
        tv_keeper_away.text = intent.getStringExtra("kiperAway")
        tv_defend_home.text = intent.getStringExtra("defenderHome")
        tv_defend_away.text = intent.getStringExtra("defenderAway")
        tv_midle_home.text = intent.getStringExtra("midleHome" )
        tv_midle_away.text = intent.getStringExtra("midleAway")
        tv_forward_home.text = intent.getStringExtra("forwardHome" )
        tv_forward_away.text = intent.getStringExtra("forwardAway" )
        tv_detail_tanggal.text = intent.getStringExtra("tanggal")

        val ref = asReference()
        async(UI) {
            val data = bg {

            }
            ref().getHome(data.await())
            ref().getaway(data.await())
        }
    }

    private fun getHome(await: Unit){
        val service: Service = Client.provideApi(Service::class.java)
        service.getTeam(intent.getStringExtra("idHome"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.teams as MutableList<Prev>
                            var images = (items.get(0).teamBadge)

                            Glide.with(applicationContext)
                                    .load(images)
                                    .into(img_home)

                        },
                        { error ->
                            toast(""+error.message) }
                )
    }
    private fun getaway(await: Unit){
        val service: Service = Client.provideApi(Service::class.java)
        service.getTeam(intent.getStringExtra("idAway"))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            items.clear()
                            items = result.teams as MutableList<Prev>
                            var images = (items.get(0).teamBadge)

                            Glide.with(applicationContext)
                                    .load(images)
                                    .into(img_away)

                        },
                        { error ->
                            toast(""+error.message) }
                )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu2, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (nilai.equals("favorit")){
                    removeFromFavorite()
                    setFavorite()
                    true
                }
                else {
                    addToFavorite()
                    setFavorite()
                    true
                }
//                isFavorite = !isFavorite
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.LAGA_ID to intent.getStringExtra("lagaId"),
                        Favorite.TEAM_HOME to intent.getStringExtra("teamHome"),
                        Favorite.TEAM_AWAY to intent.getStringExtra("teamAway"),
                        Favorite.SCORE_HOME to intent.getStringExtra("scoreHome"),
                        Favorite.SCORE_AWAY to intent.getStringExtra("scoreAway"),
                        Favorite.FHOME to intent.getStringExtra("formasiHome"),
                        Favorite.FAWAY to intent.getStringExtra("formasiAway"),
                        Favorite.GOALHOME to intent.getStringExtra("goalHome"),
                        Favorite.GOALAWAY to intent.getStringExtra("goalAway"),
                        Favorite.SHOTHOME to intent.getStringExtra("shotsHome"),
                        Favorite.SHOTAWAY to intent.getStringExtra("shotsAway"),
                        Favorite.KIPERHOME to intent.getStringExtra("kiperHome"),
                        Favorite.KIPER_AWAY to intent.getStringExtra("kiperAway"),
                        Favorite.DEFENDHOME to intent.getStringExtra("defenderHome"),
                        Favorite.DEFEND_AWAY to intent.getStringExtra("defenderAway"),
                        Favorite.MIDLEHOME to intent.getStringExtra("midleHome"),
                        Favorite.MIDLE_AWAY to intent.getStringExtra("midleAway"),
                        Favorite.FORWARDHOME to intent.getStringExtra("forwardHome"),
                        Favorite.FORWARDAWAY to intent.getStringExtra("forwardAway"),
                        Favorite.IDHOME to intent.getStringExtra("idHome"),
                        Favorite.IDAWAY to intent.getStringExtra("idAway"),
                        Favorite.TANGGAL to intent.getStringExtra("tanggal"),
                        Favorite.TEAM_BADGE to "team")
            }
            toast("added to favorite")
//            isFavorite = true
            nilai = "favorit"
//            snackbar(swipeRefresh, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(""+e.localizedMessage)
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(LAGA_ID = {id})",
                        "id" to intent.getStringExtra("id"))
            }
            toast("removed to favorite")
//            isFavorite = false
            nilai = "not"
//            snackbar(swipeRefresh, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            toast(""+e.localizedMessage)
//            snackbar(swipeRefresh, e.localizedMessage).show()
        }
    }
    private fun setFavorite() {
        if (nilai.equals("not"))
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_border_black_24dp)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_black_24dp)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                    .whereArgs("(LAGA_ID = {id})",
                            "id" to intent.getStringExtra("id"))
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.size != 0){
                nilai = "favorit"
//                toast(nilai)
                setFavorite()
            }else{
                nilai = "not"
//                toast(nilai)
                setFavorite()
//                isFavorite = false
            }
        }
    }
}
