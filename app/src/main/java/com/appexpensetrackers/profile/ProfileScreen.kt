package com.appexpensetrackers

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.appexensetrackers.R
import com.appexpensetrackers.ui.theme.zinc

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current // Reuse LocalContext

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Profile Section
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profiles), // Ensure drawable exists
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "John Doe", // Replace with dynamic username if needed
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = zinc,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileOptionItem(
                icon = R.drawable.baseline_share_24,
                label = "Invite Friends"
            ) {
                // Action for Invite Friends
            }

            ProfileOptionItem(
                icon = R.drawable.person,
                label = "Account Info"
            ) {
                // Action for Account Info
            }

            ProfileOptionItem(
                icon = R.drawable.baseline_message_24,
                label = "Message Center"
            ) {
                // Action for Message Center
            }

            ProfileOptionItem(
                icon = R.drawable.baseline_login_24,
                label = "Login and Security"
            ) {
                // Action for Login and Security
                openWebPage(context, "https://spending-tracker.app/privacy-policy/")

            }

            ProfileOptionItem(
                icon = R.drawable.baseline_lock_24,
                label = "Data and Privacy"
            ) {
                openWebPage(context, "https://spending-tracker.app/privacy-policy/")
            }
        }
    }
}

/**
 * Helper function to open a web page in the default browser or handle gracefully.
 */
fun openWebPage(context: android.content.Context, url: String) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    } catch (e: ActivityNotFoundException) {
        // Fallback in case no browser app is installed
        e.printStackTrace()
    }
}

@Composable
fun ProfileOptionItem(icon: Int, label: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() } // Properly pass and handle the lambda
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = label,
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = label,
            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                color = zinc
            ),
            modifier = Modifier.weight(1f)
        )
    }

    Divider(
        color = Color.LightGray.copy(alpha = 0.5f),
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}
