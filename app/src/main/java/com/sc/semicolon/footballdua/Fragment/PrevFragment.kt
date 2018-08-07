package com.sc.semicolon.footballdua.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sc.semicolon.footballdua.Activity.DetailPrevActivity
import com.sc.semicolon.footballdua.Adapter.PrevAdapter
import com.sc.semicolon.footballdua.Model.Prev
import com.sc.semicolon.footballdua.R
import com.sc.semicolon.footballdua.Retrofit.Client
import com.sc.semicolon.footballdua.Retrofit.Service
import kotlinx.android.synthetic.main.fragment_prev.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.progressDialog
import org.jetbrains.anko.support.v4.toast
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


/**
 * A simple [Fragment] subclass.
 */



class PrevFragment : Fragment() {

    private var items: MutableList<Prev> = mutableListOf()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_prev, container, false)

        val ref = asReference()
        async(UI) {
            val data = bg {

            }
            ref().getData(data.await())
        }

        return view

    }
    private fun getData(await: Unit) {
        val dialog = progressDialog(message = "Please wait ", title = "Fetching data")
        dialog.show()
        val service: Service = Client.provideApi(Service::class.java)
        service.getPrev("4328")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            dialog.dismiss()
                            items.clear()
                            items = result.events as MutableList<Prev>
                            rv.layoutManager = LinearLayoutManager(activity)
                            rv.adapter = PrevAdapter(activity, items){
                                startActivity(intentFor<DetailPrevActivity>("teamHome" to it.teamHome,
                                        "teamAway" to it.teamAway,
                                        "scoreHome" to it.scoreHome,
                                        "scoreAway" to it.scoreAway,
                                        "formasiHome" to it.homeFormation,
                                        "formasiAway" to it.awayFormation,
                                        "goalHome" to it.goalHome,
                                        "goalAway" to it.goalAway,
                                        "shotsHome" to it.shotsHome,
                                        "shotsAway" to it.shotsAway,
                                        "kiperHome" to it.keperHome,
                                        "kiperAway" to it.keperAway,
                                        "defenderHome" to it.defenderHome,
                                        "defenderAway" to it.defenderAway,
                                        "midleHome" to it.midleHome,
                                        "midleAway" to it.midleAway,
                                        "forwardHome" to it.forwardHome,
                                        "forwardAway" to it.forwardAway,
                                        "tanggal" to it.dateEvent,
                                        "idHome" to it.idHomeTeam,
                                        "lagaId" to it.lagaId,
                                        "id" to "${it.lagaId}",
                                        "idAway" to it.idAwayTeam))
                            }

                        },
                        { error ->
                            dialog.dismiss()
                            toast(""+error.message) }
                )
    }

}// Required empty public constructor
