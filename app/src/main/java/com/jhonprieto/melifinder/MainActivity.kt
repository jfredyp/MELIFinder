package com.jhonprieto.melifinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.jhonprieto.melifinder.ui.navigation.navGraph
import com.jhonprieto.melifinder.ui.theme.melifinderTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            melifinderTheme {
                val navController = rememberNavController()
                navGraph(navController = navController)
/*                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/
            }
        }
    }
}

@Composable
fun greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun greetingPreview() {
    melifinderTheme {
        greeting("Android")
    }
}
