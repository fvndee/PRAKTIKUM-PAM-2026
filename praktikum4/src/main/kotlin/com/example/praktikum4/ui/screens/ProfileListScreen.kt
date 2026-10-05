package com.example.praktikum4.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktikum4.model.Profile
import com.example.praktikum4.viewmodel.ProfileViewModel

@Composable
fun ProfileListScreen(
    viewModel: ProfileViewModel,
    isCompact: Boolean,
    onAddProfileClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val profiles by viewModel.profiles.collectAsState()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Daftar Profile",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF212121),
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        // Button untuk tambah profile baru
        Button(
            onClick = onAddProfileClick,
            modifier = Modifier.padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE)
            )
        ) {
            Text(
                text = "+ Tambah Profile Baru",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
        }

        if (profiles.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 800.dp)
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F8F8))
            ) {
                Text(
                    text = "Belum ada profile tersimpan.\nTambahkan profile baru di atas.",
                    modifier = Modifier.padding(32.dp),
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
            ) {
                items(profiles) { profile ->
                    ProfileCard(
                        profile = profile,
                        isCompact = isCompact
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileCard(
    profile: Profile,
    isCompact: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .widthIn(max = 800.dp)
            .padding(4.dp),
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
                name = profile.name,
                bio = profile.bio,
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
                    value = profile.email,
                    isCompact = isCompact
                )

                InfoItem(
                    icon = Icons.Default.Phone,
                    label = "Phone",
                    value = profile.phone,
                    isCompact = isCompact
                )

                InfoItem(
                    icon = Icons.Default.LocationOn,
                    label = "Location",
                    value = profile.location,
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

        // Menggunakan icon default karena tidak ada resource foto
        Box(
            modifier = Modifier
                .size(photoSize)
                .clip(CircleShape)
                .background(Color(0xFFE8DEF8)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "Profile Photo",
                tint = Color(0xFF6200EE),
                modifier = Modifier.size(photoSize * 0.6f)
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
