package com.jetback

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.UiMode
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.jetback.ui.theme.Compose_TutorialTheme
import java.text.SimpleDateFormat

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContent{
        //MessageCard(Messages("Android","Jetpack Compose"))
        Compose_TutorialTheme() {
            Conversation(SimpleData.conversationSample)
        }
    }
    }
    @Composable
    fun MessageCard(message:Messages){
        Row(Modifier.padding(8.dp)) {
            Image(painter = painterResource(id = R.drawable.jetback),
                contentDescription
                ="this is jet pack image",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, MaterialTheme.colors.secondary, RoundedCornerShape(5.dp))
            )
            Spacer(modifier = Modifier.width(10.dp))
            var isExpanded by remember { mutableStateOf(false) }
            // We toggle the isExpanded variable when we click on this Column
            // surfaceColor will be updated gradually from one color to the other
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
            )
            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = message.author,
                    color = MaterialTheme.colors.secondaryVariant,
                    style = MaterialTheme.typography.subtitle2
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    elevation = 1.dp,
                    // surfaceColor color will be changing gradually from primary to surface
                    color = surfaceColor,
                    // animateContentSize will change the Surface size gradually
                    modifier = Modifier.animateContentSize().padding(1.dp)

                ) {
                    Text(
                        text = message.body,
                        modifier = Modifier.padding(all = 4.dp),
                        // If the message is expanded, we display all its content
                        // otherwise we only display the first line
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }

    }

    @Composable
    fun Conversation(message:List<Messages>){
        LazyColumn {
            items(message) { message ->
                MessageCard(message)
            } 
    }
}
    @Preview
    @Composable
    fun PreviewConversation(){
        Compose_TutorialTheme() {
            Conversation(SimpleData.conversationSample)
        }
    }
}
