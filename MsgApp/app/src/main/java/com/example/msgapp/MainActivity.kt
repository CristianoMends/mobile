package com.example.msgapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log // Importe Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.msgapp.ui.theme.MsgAppTheme
import com.example.msgapp.ui.view.ChatScreen
import com.example.msgapp.ui.view.RoomSelector
import com.example.msgapp.ui.view.notifyNewMessage
import com.example.msgapp.viewModel.MsgViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            MsgAppTheme {
                MsgAppRoot()
            }
        }
    }
}

@Composable
fun AppDefaultTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF1976D2),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFBBDEFB),
            onPrimaryContainer = Color(0xFF0D47A1),
            secondary = Color(0xFF42A5F5),
            onSecondary = Color.White,
            secondaryContainer = Color(0xFFE3F2FD),
            onSecondaryContainer = Color(0xFF1A237E),
            surface = Color.White,
            onSurface = Color.Black,
            surfaceVariant = Color(0xFFF0F0F0),
            onSurfaceVariant = Color(0xFF424242)
        ),
        content = content
    )
}

@SuppressLint("MissingPermission")
@Composable
fun MsgAppRoot(vm: MsgViewModel = viewModel()) {
    val context = LocalContext.current

    val firebaseAuth = remember { FirebaseAuth.getInstance() }
    val user by produceState(initialValue = firebaseAuth.currentUser) {
        if (value == null) {
            firebaseAuth.signInAnonymously()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        value = firebaseAuth.currentUser
                        Log.d("MsgApp", "Login anônimo bem-sucedido. UID: ${value?.uid}")
                    } else {
                        Log.e("MsgApp", "Falha no login anônimo", task.exception)
                    }
                }
        } else {
            Log.d("MsgApp", "Usuário já logado. UID: ${value?.uid}")
        }
    }

    val userId = user?.uid ?: "anonymous_fallback" // Mude para um fallback mais descritivo
    val userName by remember(userId) { mutableStateOf("Usuário-${userId.takeLast(4)}") }

    // --- LOGS ADICIONADOS AQUI ---
    LaunchedEffect(userId, userName) {
        Log.d("MsgApp", "Dispositivo atual - User ID: $userId, User Name: $userName")
    }
    // --- FIM DOS LOGS ADICIONADOS ---

    var selectedRoom by remember { mutableStateOf<String?>(null) }
    var lastNotifiedId by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selectedRoom) {
        selectedRoom?.let { room ->
            vm.switchRoom(room)
        }
    }

    if (selectedRoom == null) {
        RoomSelector(
            onRoomSelected = { room ->
                if (room.isNotBlank()) {
                    selectedRoom = room
                }
            }
        )
    } else {
        ChatScreen(
            username = userName,
            userId = userId,
            messages = vm.messages.collectAsState().value,
            onSend = { text -> vm.sendMessage(userId, userName, text) },
            currentRoom = selectedRoom!!,
            lastNotifiedId = lastNotifiedId,
            onNotify = { msg ->
                notifyNewMessage(context, msg)
                lastNotifiedId = msg.id
            },
            onLeaveRoom = {
                selectedRoom = null
            }
        )
    }
}