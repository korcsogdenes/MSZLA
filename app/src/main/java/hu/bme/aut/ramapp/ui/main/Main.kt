package hu.bme.aut.ramapp.ui.main

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.ui.details.CharacterDetails
import hu.bme.aut.ramapp.ui.theme.ListTheme
import hu.bme.aut.ramapp.ui.theme.lightGray

@Composable
fun NavFun(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "characterlist") {
        composable("characterlist") { MainScreen(navController) }
        composable("characterdetails/{cId}",
            arguments = listOf(navArgument("cId") {type = NavType.IntType})) {
            CharacterDetails(it.arguments?.getInt("cId"), hiltViewModel())
        }
    }

}

@Composable
fun MainScreen(navController: NavController, model: MainViewModel = hiltViewModel()){

    LaunchedEffect(key1 = ""){
        model.loadFirstPage()
    }

    val characterList: List<Character> by model.characterListLD.observeAsState(listOf())

    Column {
                LazyColumn(
                    Modifier.weight(1f),
                    contentPadding = PaddingValues(10.dp)
                ) {
                    items(
                        items = characterList,
                        itemContent = {
                            CharacterListItem(navController, character = it)
                        }
                    )
                }
                Button(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = { model.loadNextCharacters() }) {
                    Text(text = "Next Page",
                        color = Color.White)
                }
            }
}

@Composable
fun CharacterListItem(navController: NavController, character: Character){

    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.secondary,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(Modifier.clickable {
            navController.navigate("characterdetails/${character._id}")
        }) {
            Image(
                painter = rememberAsyncImagePainter(character.img_url),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(84.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
            ) {
                Text(text = character.name,
                    color = Color.White)
            }
        }
    }


}
