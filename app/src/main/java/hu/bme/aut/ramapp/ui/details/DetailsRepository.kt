package hu.bme.aut.ramapp.ui.details

import android.util.Log
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.model.Episode
import hu.bme.aut.ramapp.model.Rating
import hu.bme.aut.ramapp.network.CharacterService
import hu.bme.aut.ramapp.persistence.AppDatabase
import hu.bme.aut.ramapp.persistence.dao.RatingDao
import retrofit2.Response
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val characterService: CharacterService,
    private val ratingDao: RatingDao
) {

    suspend fun saveRating(rating: Rating){
        var res = ratingDao.getRatingForCharacter(rating.characterId)
        if(res != null){
            rating._id = res._id
        }
        ratingDao.insertRating(rating)
    }

    suspend fun delRating(rating: Rating){
        ratingDao.delRating(rating)
    }

    suspend fun getRating(cId: Int): Rating? {
        return ratingDao.getRatingForCharacter(cId)
    }

    suspend fun getEpisode(eps: List<String>): Response<Episode>{
        val lflat = eps.joinToString()
        Log.e("DDDDDDDDDD", lflat)
        return characterService.getEpisode(lflat)
    }

    suspend fun getEpisodes(eps: List<String>): Response<List<Episode>>{
        val lflat = eps.joinToString()
        Log.e("DDDDDDDDDD", lflat)
        return characterService.getEpisodes(lflat)
    }

    suspend fun getCharacter(id: Int): Character? {
        val res = characterService.getCharacter(id)
        if(res.isSuccessful){
            val r = characterService.getCharacter(id).body()
            val rat = ratingDao.getRatingForCharacter(r!!._id)
            val epNums = mutableListOf<String>()
            for(ep in r.episodeList){
                epNums.add(ep.substring(40))
            }
            if(r.episodeList.size>1){
                val epRes = getEpisodes(epNums)

                if(epRes.isSuccessful){
                    val mutL = mutableListOf<Episode>()
                    for(e in epRes.body()!!){
                        mutL.add(Episode(e.name, e.episode))
                    }
                    r.epListToShow = mutL
                }
            }
            else{
                val epRes = getEpisode(epNums)

                if(epRes.isSuccessful){
                    val mutL = mutableListOf<Episode>()
                    val e = epRes.body()!!
                    mutL.add(Episode(e.name, e.episode))
                    r.epListToShow = mutL
                }
            }
            if(rat != null)
                r.rating = rat
            if(r.epListToShow.size >= 0){
                r.firstEp = r.epListToShow[0]
            }
            else{
                r.firstEp = Episode("Unkown", "")
            }

            return r
        }
        else{
            Log.e("ERROR", "Retrieving character failed.")
            return null
        }

    }

}