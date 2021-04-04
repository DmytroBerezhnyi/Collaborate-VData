package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.fragent.company

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.example.dmytroberezhnyi_vdatatesttask.data.entity.Company

val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)

val primary = Color(0xFF1976D2)
val primaryLight = Color(0xFF63A4FF)
val primaryDark = Color(0xFF004BA0)

val secondary = Color(0xFF9B27AF)
val secondaryLight = Color(0xFFCF5CE2)
val secondaryDark = Color(0xFF69007F)

@Composable
fun CompanyItem(
    company: Company,
    onCompanyItemClicked: (company: Company) -> Unit,
    onCompanyItemLongPressed: (company: Company) -> Unit
) {
    Card(
        Modifier
            .height(50.dp)
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    onCompanyItemLongPressed.invoke(company)
                }, onTap = {
                    onCompanyItemClicked.invoke(company)
                })
            },
        backgroundColor = primaryLight,
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = company.companyName, color = white)
        }
    }
}

@Composable
fun Companies(
    companyList: State<List<Company>?>,
    onCompanyItemClicked: (company: Company) -> Unit,
    onCompanyItemLongPressed: (company: Company) -> Unit
) {
    LazyColumn {
        items(companyList.value ?: listOf()) {
            CompanyItem(company = it, onCompanyItemClicked, onCompanyItemLongPressed)
        }
    }
}