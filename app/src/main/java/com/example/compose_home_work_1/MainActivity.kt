package com.example.compose_home_work_1

import android.os.Bundle
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_home_work_1.ui.theme.Compose_home_work_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_home_work_1Theme {
                AppContent()
            }
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun AppContent() {
    var itemsList by rememberSaveable() { mutableStateOf(listOf<Int>()) }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                ) {
                    ItemGrid(items = itemsList)
                }
                Button(
                    onClick = {
                        itemsList = itemsList + (itemsList.size)
                    },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(8.dp)
                ) {
                    Text("Добавить квадрат")
                }

            }
        }
    )
}

@Composable
fun ItemGrid(items: List<Int>) {
    val columnCount = if (isInPortrait()) 3 else 4

    LazyVerticalGrid(
        columns = GridCells.Fixed(columnCount),
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .aspectRatio(1f)
                    .background(
                        if (index % 2 == 0) Color.Red else Color.Blue
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = item.toString(),
                    fontSize = 24.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun isInPortrait(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}

