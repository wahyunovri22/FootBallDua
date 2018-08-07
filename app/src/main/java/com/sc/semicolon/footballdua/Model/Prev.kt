package com.sc.semicolon.footballdua.Model

import com.google.gson.annotations.SerializedName

/**
 * Created by cis on 27/07/2018.
 */
data class Prev(@SerializedName("idTeam")
                var teamId: String? = null,

                @SerializedName("idEvent")
                var lagaId: String? = null,

                @SerializedName("strTeam")
                var teamName: String? = null,
                @SerializedName("strEvent")
                var event: String? = null,

                @SerializedName("strHomeTeam")
                var teamHome: String? = null,

                @SerializedName("strAwayTeam")
                var teamAway: String? = null,

                @SerializedName("intHomeScore")
                var scoreHome: String? = null,

                @SerializedName("intAwayScore")
                var scoreAway: String? = null,

                @SerializedName("dateEvent")
                var dateEvent: String? = null,

                @SerializedName("strHomeFormation")
                var homeFormation: String? = null,

                @SerializedName("strAwayFormation")
                var awayFormation: String? = null,

                @SerializedName("strHomeGoalDetails")
                var goalHome: String? = null,

                @SerializedName("strAwayGoalDetails")
                var goalAway: String? = null,

                @SerializedName("strHomeLineupGoalkeeper")
                var keperHome: String? = null,
                @SerializedName("strHomeLineupDefense")
                var defenderHome: String? = null,
                @SerializedName("strHomeLineupMidfield")
                var midleHome: String? = null,
                @SerializedName("strHomeLineupForward")
                var forwardHome: String? = null,

                @SerializedName("strAwayLineupGoalkeeper")
                var keperAway: String? = null,
                @SerializedName("strAwayLineupDefense")
                var defenderAway: String? = null,
                @SerializedName("strAwayLineupMidfield")
                var midleAway: String? = null,
                @SerializedName("strAwayLineupForward")
                var forwardAway: String? = null,

                @SerializedName("intHomeShots")
                var shotsHome: String? = null,
                @SerializedName("intAwayShots")
                var shotsAway: String? = null,

                @SerializedName("idHomeTeam")
                var idHomeTeam: String? = null,

                @SerializedName("idAwayTeam")
                var idAwayTeam: String? = null,

                @SerializedName("strTeamBadge")
                var teamBadge: String? = null)