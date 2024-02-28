package  com.agt.ramsomuser.ui.overview

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.agt.ramsomuser.MainActivity
import com.agt.ramsomuser.R
import com.agt.ramsomuser.ui.viewmodel.MainActivityViewModel

@Composable
fun OverviewBody(mainActivityViewModel: MainActivityViewModel, mainActivity: MainActivity) {

    var title: String? = null
    var loading: Boolean = true

    if (mainActivityViewModel.allConstactsListModel.value?.results == null) {
        loading = true
        title = stringResource(R.string.initapp)
    } else {
        loading = false
        title = stringResource(R.string.endapp)
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        if (title != null) {
            Text(
                text = title,
                modifier = Modifier.padding(10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Color.White
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (loading)
            CircularProgressAnimated()
    }
}

@Composable
private fun CircularProgressAnimated() {
    val progressValue = 0.999f
    val infiniteTransition = rememberInfiniteTransition()

    val progressAnimationValue by infiniteTransition.animateFloat(
        initialValue = 0.0f,
        targetValue = progressValue, animationSpec = infiniteRepeatable(animation = tween(900))
    )

    CircularProgressIndicator(progress = progressAnimationValue)
}

