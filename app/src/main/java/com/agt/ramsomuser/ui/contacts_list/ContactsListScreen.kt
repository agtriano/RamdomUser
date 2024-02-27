package com.agt.ramsomuser.ui.characterslist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

import com.agt.ramsomuser.data.model.Result
import com.agt.ramsomuser.data.model.Results
import com.agt.ramsomuser.tolog

@Composable
fun ContactsListBody(
    myList: Results?,
    onCharacterClick: (Int) -> Unit
) {

    if (myList != null) {



        var text by rememberSaveable { mutableStateOf("") }
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()

        ) {
            TextField(
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth(),
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Buscar") }
            )
            Spacer(modifier = Modifier.height(8.dp))

            tolog(" results size "+myList.results.size)
            ListComposable(myList.results, onCharacterClick = onCharacterClick,text)
        }
    }
}

fun getFilterList(myList: ArrayList<Result>, text: String): List<Result> {
    var filterList: List<Result> = myList.filter {
            result -> result.name?.first?.contains(text) == true
            || result.name?.last?.contains(text) == true
            || result.email?.contains(text)   == true
    }
    return filterList
}


@Composable
fun ListComposable(myList: ArrayList<Result>, onCharacterClick: (Int) -> Unit, text: String) {


   var  myFilterList: ArrayList<Result> = getFilterList(myList,text) as ArrayList<Result>

    LazyColumn(modifier = Modifier.fillMaxHeight()) {

        items(myFilterList) { item ->

            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 5.dp,
                color = MaterialTheme.colors.surface,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(all = 1.dp)
                        .fillMaxWidth()
                        .clickable { onCharacterClick(myList.indexOf(item)) }) {




                    Image(
                        painter = rememberImagePainter(
                            data = item.picture?.thumbnail,
                            builder = {
                                transformations(CircleCropTransformation())
                                crossfade(true)
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(start = 8.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(38.dp))
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)

                            .width(IntrinsicSize.Max)
                    ) {
                        Text(
                            text = " ${item.name?.title} ${item.name?.first} ${item.name?.last}",
                            color = Color.Black,fontSize = 16.sp,fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.h6,
                            modifier = Modifier.padding(all = 4.dp)
                        )

                        Text(
                            text = " ${item.email}",
                            color = Color.Gray,fontSize = 14.sp,fontWeight = FontWeight.Normal,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(all = 4.dp)
                        )
                    }



                }


            }
            Spacer(modifier = Modifier.height(5.dp))
        }
    }




}




