package com.agt.ramsomuser


import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.agt.ramsomuser.ui.characterslist.ContactsListBody
import com.agt.ramsomuser.ui.contacts_details.ContactsDetailsBody
import com.agt.ramsomuser.ui.overview.OverviewBody
import com.agt.ramsomuser.ui.theme.RamdomUserScreen
import com.agt.ramsomuser.ui.theme.RamdomUserTheme
import com.agt.ramsomuser.ui.viewmodel.MainActivityViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    private lateinit var navController: NavHostController
    private var subContent: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val activityKiller: () -> Unit = {
            this.finish()
        }


        WindowCompat.setDecorFitsSystemWindows(window, false)


        setContent {
            RamdomUserApp(mainActivityViewModel, this, activityKiller = activityKiller)
        }



        mainActivityViewModel.allConstactsListModel.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                navController.navigate(RamdomUserScreen.ContactsList.name) {
                    it
                }
            }
        })

        mainActivityViewModel.onCreate()


        mainActivityViewModel.contactSelected.observe(this, androidx.lifecycle.Observer {
            if (it != null) {
                navController.navigate(RamdomUserScreen.ContactsDetails.name) {
                    it
                }
            }
        })

    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun RamdomUserApp(
        mainActivityViewModel: MainActivityViewModel,
        mainActivity: MainActivity,
        activityKiller: () -> Unit
    ) {
        RamdomUserTheme() {
            navController = rememberNavController()
            val systemUiController = rememberSystemUiController()
            val allScreens = RamdomUserScreen.values().toList()
            val backstackEntry = navController.currentBackStackEntryAsState()
            val currentScreen = RamdomUserScreen.fromRoute(backstackEntry.value?.destination?.route)
            val dispatcher = LocalOnBackPressedDispatcherOwner.current!!.onBackPressedDispatcher
            var backPressedCount by remember { mutableStateOf(0) }
            var title: String? = "CONTACTOS"

            var titleColor:Color=Color.Black
            var backColor:Color=Color.White

            if (mainActivityViewModel.contactSelected.value != null) {
                val value = mainActivityViewModel.contactSelected.value
                title = " ${value?.name?.first} ${value?.name?.last}".toUpperCase()
                titleColor=Color.White
                backColor= Color.Transparent
                systemUiController.setStatusBarColor( backColor,false)
            }else{
                titleColor=Color.Black
                backColor= Color.White
                systemUiController.setStatusBarColor( backColor,true)
            }


            BackHandler {
                activityKiller()
                tolog("backstackEntry.value ${backstackEntry.value?.id} ")
                tolog("backPressedCount ${backPressedCount} ")
                backPressedCount++

            }


            Scaffold(
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = backColor,
                            titleContentColor = titleColor,
                            actionIconContentColor =  titleColor,
                            navigationIconContentColor = titleColor
                        ),

                        title = {
                            Text(text = title!!)
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                mainActivityViewModel.contactSelected.postValue(null)
                                dispatcher.onBackPressed()
                            }) {
                                Icon(currentScreen.icon, "toolbarIcon")
                            }
                        },


                    )
                }
            ) { innerPadding ->
                RamdomUserNavHost(
                    navController,
                    modifier = Modifier.padding(innerPadding),
                    mainActivityViewModel,
                    mainActivity
                )
            }
        }
    }


    @Composable
    fun RamdomUserNavHost(
        navController: NavHostController,
        modifier: Modifier = Modifier,
        mainActivityViewModel: MainActivityViewModel,
        mainActivity: MainActivity
    ) {
        NavHost(
            navController = navController,
            startDestination = RamdomUserScreen.Overview.name,
            modifier = modifier
        ) {


            composable(RamdomUserScreen.Overview.name) {
                OverviewBody(mainActivityViewModel, mainActivity)
            }

            composable(RamdomUserScreen.ContactsList.name) {
                ContactsListBody(
                    mainActivityViewModel.allConstactsListModel.value
                ) {
                    mainActivityViewModel.onContactSelected(it)
                }

            }

            composable(RamdomUserScreen.ContactsDetails.name) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    ContactsDetailsBody(
                        mainActivityViewModel.contactSelected.value
                    )
                }
            }


            val characterName = RamdomUserScreen.ContactsDetails.name
            composable(
                route = "$characterName/{name}",
                arguments = listOf(
                    navArgument("name") {
                        type = NavType.StringType
                    }
                )

            ) { entry ->
                val characterName = entry.arguments?.getString("name")

            }
        }
    }

}

fun tolog(msg: String) {
    Log.d("TEST", " MainActivity  + $msg")
}