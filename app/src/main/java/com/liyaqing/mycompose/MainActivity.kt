package com.liyaqing.mycompose

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.liyaqing.mycompose.ui.theme.MyComposeTheme

class MainActivity : ComponentActivity() {
    val mInstance by lazy { this }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val messages = GetData();
                    Conversation(messages)
                }
            }
        }
    }

    fun GetData(): List<Message> {
        val messages: MutableList<Message> = ArrayList();
        for (i in 1..10) {
            messages.add(
                Message(
                    "lily" + i, "eyedfkdjlsflsdjfkfdkjsfk" +
                            "l东方健康落实到积分看电康落实到积分看电康落实到积分看电康落实到积分看电视机房"
                )
            )
        }
        return messages
    }

    data class Message(val author: String, val body: String)

    @Composable
    fun MessageCard(message: Message) {

        Row(
            modifier = Modifier.padding(8.dp, 16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_pic),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.dp, color = MaterialTheme.colors.secondaryVariant, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            var isExpanded by remember {
                mutableStateOf(false)
            }

            val onClick: () -> Unit = {
                isExpanded = !isExpanded
                Toast.makeText(mInstance, "aaa", Toast.LENGTH_SHORT).show()
            }
            Column(modifier = Modifier.clickable(onClick = onClick)) {
                Text(
                    text = "Hello ${message.author}!",
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle1
                )

                Text(
                    text = "Hello ${message.body}!",
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1
                )
            }
        }
    }

    @Composable
    fun Conversation(messages: List<Message>) {
        Column() {
            MyAppNavHost();
            LazyColumn() {
                items(messages) { message ->
                    MessageCard(message = message)
                }
            }
        }

    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MessageCard(message = Message("3333", "look the eye"))

    }
    @Composable
    fun MyAppNavHost(
        modifier: Modifier = Modifier,
        navController: NavHostController = rememberNavController(),
        startDestination: String = "profile"
    ) {
        NavHost(
            modifier = modifier,
            navController = navController,
            startDestination = startDestination
        ) {
            composable("profile") {
                var userId:String="jkj";
                ProfileScreen(
                    onNavigateToProfileaa = { navController.navigate("profileaa/userId=$userId"){
                    }},
                    /*...*/
                )
            }
            composable("profileaa/userId={userId}",
                arguments = listOf(navArgument("userId") { defaultValue = "me" })
            ) {
                var str: String? =""
                it.arguments?.getString("userId")?.let { it1 ->str=it1 }
                ProfileScreenaa(
                    str,
                    onNavigateToFriends = { navController.navigate("friendsList"){
                        popUpTo("profile")
                    } },
                    /*...*/
                )
            }
            composable("friendslist") {
                FriendsListScreen(navController)
            }

        }
    }

    @Composable
    fun ProfileScreen(
        onNavigateToProfileaa: () -> Unit,
        /*...*/
    ) {
        /*...*/
        Button(onClick = onNavigateToProfileaa) {
            Text(text = "See friends list")
        }
    }
    @Composable
    fun ProfileScreenaa(
        userId:String?,
        onNavigateToFriends: () -> Unit,
        /*...*/
    ) {
        /*...*/
        Button(onClick = onNavigateToFriends,
        modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
        Text(text = "$userId aa friends list")
        }
    }
    @Composable
    fun FriendsListScreen(
        navController: NavController
        /*...*/
    ) {

        Button(onClick = {navController.navigateUp()},
        modifier = Modifier.fillMaxHeight().fillMaxWidth()) {
            Text(text = "hello friends list")
        }
    }
}