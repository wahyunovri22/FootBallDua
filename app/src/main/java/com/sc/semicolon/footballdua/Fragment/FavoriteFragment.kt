package com.sc.semicolon.footballdua.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.semicolon.footballdua.Activity.DetailFavoritActivity
import com.sc.semicolon.footballdua.Activity.DetailPrevActivity
import com.sc.semicolon.footballdua.Adapter.FavoriteAdapter
import com.sc.semicolon.footballdua.Helper.Favorite
import com.sc.semicolon.footballdua.Helper.database
import com.sc.semicolon.footballdua.R
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.*


/**
 * A simple [Fragment] subclass.
 */
class FavoriteFragment : Fragment() {

    private var favorites: MutableList<Favorite> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_favorite, container, false)
        val list = view.findViewById<RecyclerView>(R.id.rv)
        val dialog = progressDialog(message = "Please wait ", title = "Fetching data")
        dialog.show()
        context?.database?.use {
            dialog.dismiss()
//            swipeRefresh.isRefreshing = false
            val result = select(Favorite.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<Favorite>())
            favorites.addAll(favorite)
            if (favorite.size != 0){
//                toast(""+favorites.get(0).lagaId)
                list.layoutManager = LinearLayoutManager(ctx)
                list.adapter = FavoriteAdapter(favorites) {
                    ctx.startActivity(intentFor<DetailFavoritActivity>("id" to "${it.lagaId}",
                            "teamHome" to it.teamHome,
                            "teamAway" to it.teamAway,
                            "scoreHome" to it.scoreHome,
                            "scoreAway" to it.scoreAway,
                            "formasiHome" to it.fHome,
                            "formasiAway" to it.fAway,
                            "goalHome" to it.goalHome,
                            "goalAway" to it.goalAway,
                            "shotsHome" to it.shotHome,
                            "shotsAway" to it.shotAway,
                            "kiperHome" to it.kiperHome,
                            "kiperAway" to it.kiperAway,
                            "defenderHome" to it.defendHome,
                            "defenderAway" to it.defendAway,
                            "midleHome" to it.midleHome,
                            "midleAway" to it.midleAway,
                            "forwardHome" to it.forwardHome,
                            "forwardAway" to it.forwardAway,
                            "tanggal" to it.tanggal,
                            "idHome" to it.idHome,
                            "idAway" to it.idAway))
                }
            }else{
                toast("data kosong")
            }
//            adapter.notifyDataSetChanged()
        }
//        showFavorite()

        return view
    }

    private fun showFavorite() {

    }

}// Required empty public constructor
