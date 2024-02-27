package com.agt.ramsomuser.ui.contacts_details


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.agt.ramsomuser.R
import com.agt.ramsomuser.data.model.Location
import com.agt.ramsomuser.tolog
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ContactsDetailsBody(value: com.agt.ramsomuser.data.model.Result?) {

    tolog("${value.toString()}")

    Image(
        painterResource(R.drawable.pic_detail),
        "content description",
        modifier = Modifier
            .height(198.dp)
            .fillMaxWidth()
            .graphicsLayer {
                translationY = -130.dp.toPx()
            } ,
        contentScale = ContentScale.Crop
    )


    Spacer(modifier = Modifier.height(((250)-(77)).dp))

    Column(
        modifier = Modifier
            .padding(all = 0.dp)
            .fillMaxWidth()
    ) {


     //   Spacer(modifier = Modifier.height(160.dp))

        val borderWidth = 4.dp
        
        Image(
            painter = rememberImagePainter(
                data = value?.picture?.thumbnail,
                builder = {
                    transformations(CircleCropTransformation())
                    crossfade(true)
                }
            ),
            contentDescription = null,

            modifier = Modifier
                .size(77.dp)
                .clip(CircleShape)
                .border(
                    BorderStroke(borderWidth, Color.White),
                    CircleShape
                )
                .padding(borderWidth)

        )

        Row(
            modifier = Modifier
                .padding(all = 1.dp)
                .align(Alignment.End)
                .graphicsLayer {
                    translationY = -50.dp.toPx()
                }

        ) {


            Image(
                painterResource(R.drawable.base_button),
                "content description",
                modifier = Modifier.height(48.dp)
            )

            Image(
                painterResource(R.drawable.base_button_1_),
                "content description",
                modifier = Modifier.height(48.dp)
            )

        }




        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 5.dp,
            color = MaterialTheme.colors.surface,
            modifier = Modifier
                .padding(all = 8.dp)
                .fillMaxWidth()
                .fillMaxHeight()
                .graphicsLayer {
                    translationY = -60.dp.toPx()
                }
        ) {


            Row(
                modifier = Modifier
                    .padding(all = 1.dp)
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                        .padding(all = 1.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(1.dp))

                    ItemInfoView(
                        "Nombre y apellidos",
                        " ${value?.name?.title} ${value?.name?.first} ${value?.name?.last}",
                        R.drawable.ic_name, false
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    ItemInfoView("Email", value?.email.toString(), R.drawable.ic_email, false)
                    Spacer(modifier = Modifier.height(2.dp))
                    ItemInfoView("Género", value?.gender.toString(), R.drawable.ic_gender, true)
                    Spacer(modifier = Modifier.height(2.dp))

                    val dateFormat: DateFormat = SimpleDateFormat("dd-MM-yyyy")
                    var strDate: String? = null
                    val dateRegister: Date? =value?.registered?.date
                    if(dateRegister!=null){
                        strDate=dateFormat.format(value?.registered?.date)
                    }

                    ItemInfoView("Fecha de registro", strDate!!, R.drawable.ic_calendar, true)
                    Spacer(modifier = Modifier.height(2.dp))
                    ItemInfoView("Teléfono", value?.phone.toString(), R.drawable.ic_phone, true)
                    Spacer(modifier = Modifier.height(2.dp))
                    ItemInfoViewLocation("Dirección", value?.location)
                }
            }
        }

    }
}

@Composable
fun ItemInfoViewLocation(titulo: String, location: Location?) {

    Row(
        modifier = Modifier
            .padding(all = 1.dp)
            .fillMaxWidth()
            .graphicsLayer {
                translationY = 0.dp.toPx()
            }
    ) {


        Spacer(modifier = Modifier.width(108.dp))

        Column(
            modifier = Modifier
                .padding(all = 1.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = titulo,
                color = Color.Gray,fontSize = 11.sp,fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(all = 2.dp)
            )

            Text(
                text = "${location?.city} ${location?.country}",
                color = Color.Black,fontSize = 14.sp,fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(all = 2.dp)
            )
            Image(
                painterResource(R.drawable.mapa), "content description", modifier = Modifier
                    .height(143.dp)
                    .fillMaxWidth()
                    .padding(all = 2.dp)
            )
        }
    }
}

@Composable
fun ItemInfoView(titulo: String, subtitulo: String, drawable: Int, scale: Boolean) {

    var scaleMode: ContentScale = ContentScale.None

    if (scale) {
        scaleMode = ContentScale.FillWidth
    }


    Row(
        modifier = Modifier
            .padding(all = 1.dp)
            .fillMaxWidth()
            .graphicsLayer {
                translationY = 0.dp.toPx()
            }
    ) {


        Image(
            painterResource(drawable), "content description",
            contentScale = scaleMode,
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .padding(start = 8.dp, end = 0.dp)
                .align(Alignment.CenterVertically)
        )

        Spacer(modifier = Modifier.width(58.dp))
        Column(
            modifier = Modifier
                .padding(all = 1.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = titulo,
                color = Color.Gray,fontSize = 11.sp,fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(all = 2.dp)
            )

            Text(
                text = subtitulo,
                color = Color.Black,fontSize = 14.sp,fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(all = 2.dp)
            )

        }
    }


    //Spacer(modifier = Modifier.height(5.dp))
}
