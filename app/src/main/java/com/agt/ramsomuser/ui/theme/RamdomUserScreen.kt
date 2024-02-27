package com.agt.ramsomuser.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Screen metadata for RamdomUser.
 */
enum class RamdomUserScreen(
    val icon: ImageVector,
) {

    ContactsList(
        icon = Icons.Filled.ArrowBack
    ),
    ContactsDetails(
        icon = Icons.Filled.ArrowBack
    ),
    Overview(
        icon = Icons.Filled.ArrowBack
    );


    companion object {
        fun fromRoute(route: String?): RamdomUserScreen =
            when (route?.substringBefore("/")) {
                ContactsList.name -> ContactsList
                ContactsDetails.name -> ContactsDetails
                Overview.name -> Overview
                null -> ContactsList
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }


}


