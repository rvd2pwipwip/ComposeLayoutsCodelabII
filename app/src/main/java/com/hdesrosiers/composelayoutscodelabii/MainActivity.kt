package com.hdesrosiers.composelayoutscodelabii

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.hdesrosiers.composelayoutscodelabii.ui.theme.ComposeLayoutsCodelabIITheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeLayoutsCodelabIITheme {
        // A surface container using the 'background' color from the theme
        LayoutsCodelab()
      }
    }
  }
}

@Composable
fun LayoutsCodelab() {

  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = "LayoutsCodelab")
        },
        actions = {
          IconButton(onClick = { /*TODO*/ }) {
            Icon(
              imageVector = Icons.Filled.Favorite,
              contentDescription = null
            )
          }
        }
      )
    },
  ) { innerPadding ->
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = 50)
    ImageList(scrollState = scrollState)
  }
}

@Composable
fun ImageList(scrollState: LazyListState) {
  val listSize = 100
  // save the coroutine scope where the animated scroll will be executed
  val coroutineScope = rememberCoroutineScope()

  Column {
    Row {
      Button(onClick = {
        coroutineScope.launch {
          // 0 is the first item index
          scrollState.animateScrollToItem(0)
        }
      }) {
        Text("Scroll to the top")
      }

      Button(onClick = {
        coroutineScope.launch {
          // listSize - 1 is the last index of the list
          scrollState.animateScrollToItem(listSize - 1)
        }
      }) {
        Text("Scroll to the end")
      }
    }
    LazyColumn(
      state = scrollState,
      content = {
        items(count = 100) { item ->
          ImageListItem(index = item)
        }
      }
    )
  }
}

@Composable
fun ImageListItem(index: Int) {
  Row(verticalAlignment = Alignment.CenterVertically) {
    Image(
      painter = rememberImagePainter(
        data = "https://developer.android.com/images/brand/Android_Robot.png"
      ),
      contentDescription = "Android Logo",
      modifier = Modifier.size(50.dp)
    )
    Spacer(modifier = Modifier.width(10.dp))
    PhotographerCard()
    Spacer(modifier = Modifier.width(10.dp))
    Text(
      text = "Item #${index.plus(1)}",
      style = MaterialTheme.typography.subtitle1
    )
  }
}

@Composable
fun SimpleList() {
  val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = 10)
  LazyColumn(
    state = scrollState
  ) {
    items(100) {
      Text(text = "Item #$it")
    }
  }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
  Column(
    modifier = modifier
      .fillMaxWidth()
      .background(color = Color.Yellow)
  ) {
    Text(text = "Hi there!")
    Text(text = "Thanks for going through the Layouts codelab")
  }
}

@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
  Row {
    Surface(
      modifier = Modifier
        .size(50.dp),
      shape = CircleShape,
      color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
    ) {

    }
    Column(
      modifier = Modifier
        .padding(start = 10.dp)
        .align(alignment = Alignment.CenterVertically)
    ) {
      Text(
        text = "Alfred Sisley",
        fontWeight = FontWeight.Bold
      )
      CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
        Text(
          text = "3 minutes ago",
          style = MaterialTheme.typography.body2
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LayoutsCodelabPreview() {
  ComposeLayoutsCodelabIITheme {
    LayoutsCodelab()
  }
}