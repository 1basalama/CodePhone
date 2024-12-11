package com.basalama.codephone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.basalama.codephone.ui.theme.CodePhoneTheme
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.unit.dp
import com.basalama.codephone.viewmodel.MyViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        /*MobileAds.initialize(this) {
        }*/
        super.onCreate(savedInstanceState)

        //FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(true)

        // Set the app's locale to Arabic
        val locale = Locale("en")
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

        setContent {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                BackHandler {}
                MainView()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    val context = LocalContext.current
    val viewModel = MyViewModel(context)
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        sheetContainerColor = Color.DarkGray,
        scaffoldState = scaffoldState,
        sheetDragHandle = {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowUp,
                contentDescription = null
            )
        },
        sheetPeekHeight = 23.dp, sheetTonalElevation = 8.dp,
        sheetContent = {

            DeveloperInfoView(context)
        }) {

        TabScreen(context, viewModel)


    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodePhoneTheme {
        Greeting("Android")
    }
}