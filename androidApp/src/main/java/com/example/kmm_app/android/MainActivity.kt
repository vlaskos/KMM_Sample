package com.example.kmm_app.android

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.kmm_app.models.GitUser
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val users = viewModel.state.value.allUsers

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainView(users)
                }
            }
        }
    }
}

@Composable
fun MainView(users: List<GitUser>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        users.forEach { user ->
            UserCard(user = user)
        }
    }
}

@Composable
fun UserCard(user: GitUser) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.USER_NAME, user.login)
                intent.putExtra(DetailActivity.USER_URL, user.url)
                intent.putExtra(DetailActivity.USER_IMAGE_URL, user.avatarURL)
                context.startActivity(intent)
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Row() {
//            Image(
//                painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
//                contentDescription = null,
//                modifier = Modifier
//                    .size(50.dp)
//                    .padding(8.dp),
//                contentScale = ContentScale.Fit,
//                colorFilter = ColorFilter.tint(Color.Black)
//            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarURL)
                    .error(R.drawable.ic_launcher_background)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_launcher_background),
                contentDescription = "User avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(50.dp)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = user.login,
                )
                Text(
                    text = user.url,
                    color = Color.Gray
                )
            }

        }
    }
}
@Preview()
@Composable
fun DefaultPreview() {

    val useres = listOf(
        GitUser(1, "url1", "name1", "avatarUrl1"),
        GitUser(2, "url2", "name2", "avatarUrl2"),
        GitUser(3, "url3", "name3", "avatarUrl3")
    )

    MyApplicationTheme {
        MainView(useres)
    }
}
