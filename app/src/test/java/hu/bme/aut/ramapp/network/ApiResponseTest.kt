package hu.bme.aut.ramapp.network

import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ApiResponseTest: ApiAbstract<CharacterService>() {

    lateinit var characterService: CharacterService

    @Before
    fun init(){
        characterService = createService(CharacterService::class.java)

    }

    @Test
    fun getAllCharactersApiTest(){
        runBlocking {
            enqueueResponse("characters.json")
            val b = characterService.getCharacters(1).body()
            val res = mockWebServer.takeRequest()

            assert(res.path == "/character?page=1")
            assert(res.method == "GET")
            assert(b != null)
        }
    }

    @Test
    fun getOneCharacterApiTest(){
        runBlocking {
            enqueueResponse("character.json")
            val b = characterService.getCharacter(1).body()
            val res = mockWebServer.takeRequest()

            assert(res.path == "/character/1")
            assert(res.method == "GET")
            assert(b != null)
        }
    }

    @Test
    fun getEpisodeListApiTest(){
        runBlocking {
            enqueueResponse("episodes.json")
            val b = characterService.getEpisodes("1,2,3").body()
            val res = mockWebServer.takeRequest()

            assert(res.path == "/episode/1,2,3")
            assert(res.method == "GET")
            assert(b != null)
        }
    }

    @Test
    fun getOneEpisodeApiTest(){
        runBlocking {
            enqueueResponse("episode.json")
            val b = characterService.getEpisode("1").body()
            val res = mockWebServer.takeRequest()

            assert(res.path == "/episode/1")
            assert(res.method == "GET")
            assert(b != null)
        }
    }

}