package hu.bme.aut.ramapp.persistance

import androidx.compose.animation.core.updateTransition
import hu.bme.aut.ramapp.model.Rating
import hu.bme.aut.ramapp.persistence.dao.RatingDao
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [21])
class RatingDaoTest : LocalDatabase() {

    private lateinit var ratingDao: RatingDao

    @Before
    fun init(){
        ratingDao = db.ratingDao()
    }

    @Test
    fun insertAndLoadRatingTest(){
        runBlocking {
            val mockData = Rating.mock()
            ratingDao.insertRating(mockData)

            val loadFromDB = ratingDao.getRatingForCharacter(mockData.characterId)
            assertThat(loadFromDB.toString(), `is`(mockData.toString()))
        }
    }

    @Test
    fun insertAndRemoveRatingTest(){
        runBlocking {
            val mockData = Rating.mock()
            ratingDao.insertRating(mockData)

            val loadFromDB = ratingDao.getRatingForCharacter(mockData.characterId)
            assertThat(loadFromDB.toString(), `is`(mockData.toString()))

            ratingDao.delRating(loadFromDB!!)

            val loadFromDBAfterRemove = ratingDao.getRatingForCharacter(mockData.characterId)
            assert(loadFromDBAfterRemove == null)
        }
    }

    @Test
    fun insertAndUpdateRatingTest(){
        runBlocking {
            val mockData = Rating.mock()
            ratingDao.insertRating(mockData)

            val loadFromDB = ratingDao.getRatingForCharacter(mockData.characterId)
            assertThat(loadFromDB.toString(), `is`(mockData.toString()))

            val updMockData = Rating(mockData.characterId, 5, "UpdatedRev")
            updMockData._id = loadFromDB!!._id
            ratingDao.insertRating(updMockData)

            val loadFromDBAfterUpdate = ratingDao.getRatingForCharacter(loadFromDB.characterId)
            assertThat(loadFromDBAfterUpdate.toString(), `is`(updMockData.toString()))
        }
    }

}