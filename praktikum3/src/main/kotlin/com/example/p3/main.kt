package com.example.p3

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "My Profile App",
        state = rememberWindowState(width = 800.dp, height = 600.dp)
    ) {
        MaterialTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFFF5F5F5)
            ) {
                ProfileScreen()
            }
        }
    }
}

@Composable
fun ProfileScreen() {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val isCompact = maxWidth < 600.dp
        val scrollState = rememberScrollState()
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(
                    horizontal = if (isCompact) 16.dp else 48.dp,
                    vertical = if (isCompact) 16.dp else 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileCard(
                name = "Rajendra Rifandhy Anandarianto",
                bio = "Sibuk Mancing, WhatsApp Saja",
                email = "rajendra.124140099@student.itera.ac.id",
                phone = "082179606003",
                location = "Lampung, Indonesia",
                isCompact = isCompact
            )
        }
    }
}

@Composable
fun ProfileCard(
    name: String,
    bio: String,
    email: String,
    phone: String,
    location: String,
    isCompact: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 800.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = if (isCompact) 16.dp else 32.dp,
                    vertical = if (isCompact) 20.dp else 32.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader(
                name = name,
                bio = bio,
                isCompact = isCompact
            )
            
            Spacer(modifier = Modifier.height(if (isCompact) 12.dp else 16.dp))

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(if (isCompact) 8.dp else 12.dp)
            ) {
                InfoItem(
                    icon = Icons.Default.Email,
                    label = "Email",
                    value = email,
                    isCompact = isCompact
                )
                
                InfoItem(
                    icon = Icons.Default.Phone,
                    label = "Phone",
                    value = phone,
                    isCompact = isCompact
                )
                
                InfoItem(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    value = location,
                    isCompact = isCompact
                )
            }
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    bio: String,
    isCompact: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isCompact) 8.dp else 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val photoSize = if (isCompact) 100.dp else 120.dp
        Box(
            modifier = Modifier
                .size(photoSize)
                .clip(CircleShape)
                .background(
                    color = Color(0xFF6200EE),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource("foto.jpg"),
                contentDescription = "Profile Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(photoSize)
                    .clip(CircleShape)
            )
        }
        Column(
            modifier = Modifier.weight(1f).padding(start = if (isCompact) 8.dp else 16.dp)
        ) {
            Text(
                text = name,
                fontSize = if (isCompact) 24.sp else 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
                textAlign = TextAlign.Start
            )
            Text(
                text = bio,
                fontSize = if (isCompact) 14.sp else 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Start,
                maxLines = 2
            )
        }
        Button(
            onClick = { /* follow */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE)
            )
        ) {
            Text(
                text = "Follow",
                fontSize = if (isCompact) 14.sp else 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }
    }
}


@Composable
fun InfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    isCompact: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF8F8F8)
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = if (isCompact) 12.dp else 16.dp,
                    vertical = if (isCompact) 12.dp else 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iconSize = if (isCompact) 40.dp else 48.dp
            val iconInnerSize = if (isCompact) 20.dp else 24.dp
            
            Box(
                modifier = Modifier
                    .size(iconSize)
                    .clip(CircleShape)
                    .background(Color(0xFFE8DEF8)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = Color(0xFF6200EE),
                    modifier = Modifier.size(iconInnerSize)
                )
            }
            
            Spacer(modifier = Modifier.width(if (isCompact) 12.dp else 16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    fontSize = if (isCompact) 11.sp else 12.sp,
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = value,
                    fontSize = if (isCompact) 14.sp else 16.sp,
                    color = Color(0xFF212121),
                    fontWeight = FontWeight.Normal,
                    maxLines = if (isCompact) 2 else 1
                )
            }
        }
    }
}
