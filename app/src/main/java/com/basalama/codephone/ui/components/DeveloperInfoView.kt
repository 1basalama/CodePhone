package com.basalama.codephone.ui.components


import android.content.Context
import android.content.Intent
import android.net.Uri


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.basalama.codephone.R
import com.basalama.codephone.viewmodel.SocialMedia


@Composable
fun DeveloperInfoView(context: Context) {
    val socialMedias = arrayOf(
        SocialMedia("mailto:0.basalama.1@gmail.com", R.drawable.icons8_gmail),
        SocialMedia("https://t.me/c/1516732913/8", R.drawable.icons8_telegram_app)

        //SocialMedia("https://wa.me/+967775516556", R.drawable.icons8_whatsapp),
        //SocialMedia("https://github.com/1basalama/myApps", R.drawable.ic_update),
    )


    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.my_logo),
            contentDescription = "Developer Logo",
            modifier = Modifier.size(200.dp)

        )


        //Spacer(modifier = Modifier.height(16.dp))


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            socialMedias.forEach { social ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(social.name))
                //val context = LocalContext.current

                Image(
                    modifier = Modifier.clickable { startActivity(context, intent, null) },
                    imageVector = ImageVector.vectorResource(id = social.icon),
                    contentDescription = ""
                )

            }



            //ShareButton("https://github.com/1basalama/myApps")

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "النسخة : 1.3")


    }

}

@Composable
fun ShareButton(url: String) {
    val context = LocalContext.current
    IconButton(
        onClick = {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, url)
            context.startActivity(Intent.createChooser(shareIntent, "Share via"))
        },
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share",
            tint = Color.White
        )
    }
}
@Preview(name = "default")
@Composable
fun MyPrev( ) {
    DeveloperInfoView(LocalContext.current)
}
