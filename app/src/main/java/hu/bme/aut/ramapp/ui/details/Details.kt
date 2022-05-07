@file:OptIn(ExperimentalFoundationApi::class)

package hu.bme.aut.ramapp.ui.details

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.analytics.FirebaseAnalytics
import hu.bme.aut.ramapp.model.Character
import hu.bme.aut.ramapp.ui.main.CharacterListItem
import hu.bme.aut.ramapp.ui.theme.ListTheme


@Composable
fun CharacterDetails(cId: Int?, model: DetailsViewModel, firebaseAnalytics: FirebaseAnalytics){


    val character: Character by model.character.observeAsState(Character())

    LaunchedEffect(key1 = cId){
        model.getCharcater(cId!!)
        model.revText = character.rating.review
    }

    
    ListTheme(
        content = {
            Column {
                Row {
                    Column(Modifier
                    .weight(1.2f)) {
                        Image(
                            painter = rememberAsyncImagePainter(character.img_url),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .height(300.dp)
                                .width(220.dp)
                                .padding(10.dp)
                                .clip(RoundedCornerShape(corner = CornerSize(16.dp))),
                        )
                        Row(Modifier.padding(10.dp)) {
                            for (i in 0 until character.rating.points){
                                Icon(Icons.Filled.Star, i.toString(), modifier = Modifier
                                    .size(40.dp)
                                    .combinedClickable(
                                        onClick = {
                                            if (character.rating.points != i + 1) {
                                                model.saveRating(character._id, i + 1)
                                                val bundle = Bundle()
                                                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, character._id.toString())
                                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.name)
                                                bundle.putString("item_rating", character.rating.points.toString())
                                                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Character")
                                                firebaseAnalytics.logEvent("add_rating_to_character", bundle)
                                            }
                                        },
                                        onLongClick = {
                                            if (character.rating.characterId != -1){
                                                model.delWithClear(character._id)
                                                val bundle = Bundle()
                                                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, character._id.toString())
                                                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.name)
                                                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Character")
                                                firebaseAnalytics.logEvent("delete_rating_to_character", bundle)
                                            }
                                        }
                                    )
                                )
                            }
                            for (i in character.rating.points until 5) {
                                Icon(Icons.Filled.StarOutline, i.toString(),
                                    Modifier
                                        .size(40.dp)
                                        .combinedClickable(
                                            onClick = {
                                                if (character.rating.points != i + 1) {
                                                    model.saveRating(character._id, i + 1)
                                                    val bundle = Bundle()
                                                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, character._id.toString())
                                                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.name)
                                                    bundle.putString("item_rating", character.rating.points.toString())
                                                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Character")
                                                    firebaseAnalytics.logEvent("add_rating_to_character", bundle)
                                                }
                                            },
                                            onLongClick = {
                                                if (character.rating.characterId != -1) {
                                                    model.delWithClear(character._id)
                                                    val bundle = Bundle()
                                                    bundle.putString(FirebaseAnalytics.Param.ITEM_ID, character._id.toString())
                                                    bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.name)
                                                    bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "Character")
                                                    firebaseAnalytics.logEvent("delete_rating_to_character", bundle)
                                                }
                                            }
                                        )
                                )
                            }
                        }
                        TextField(value = model.revText, onValueChange = {newT -> model.revText = newT})
                    }
                    Column(Modifier.weight(1f)) {
                        Text(text = character.name, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        Row (
                            Modifier.padding(bottom = 10.dp)
                                ) {
                            when (character.status) {
                                "Alive" -> {
                                    Icon(Icons.Filled.Person, "person", tint = Color.Green)
                                }
                                "Dead" -> {
                                    Icon(Icons.Filled.Person, "person", tint = Color.Red)
                                }
                                else -> {
                                    Icon(Icons.Filled.Person, "person")
                                }
                            }
                            Text(text = "${character.status} (${character.species})", Modifier.padding(start = 10.dp))
                        }
                        Text(text = "From:", color = Color(0xFF909090))
                        Text(text = character.origin.name, fontSize = 18.sp, modifier = Modifier.padding(bottom = 10.dp))
                        Text(text = "Last Location:", color = Color(0xFF909090))
                        Text(text = character.location.name, fontSize = 18.sp, modifier = Modifier.padding(bottom = 10.dp))
                        Text(text = "First seen in:", color = Color(0xFF909090))
                        Text(text = character.firstEp.name, fontSize = 18.sp, modifier = Modifier.padding(bottom = 10.dp))
                    }

                }

                Text(text = "Episodes: (${character.epListToShow.size})", fontSize = 20.sp)
                LazyColumn(
                    Modifier.weight(1f),
                    contentPadding = PaddingValues(10.dp),
                ){
                    items(
                        items = character.epListToShow,
                        itemContent = {
                            Text(text = "${it.episode} - ${it.name}", modifier = Modifier.padding(5.dp))
                        }
                    )
                }
            }
        }
    )
}