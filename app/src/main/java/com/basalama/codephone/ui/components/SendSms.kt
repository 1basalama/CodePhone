package com.basalama.codephone.ui.components

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.basalama.codephone.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.analytics.FirebaseAnalytics

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SendSms(index: Int, context: Context, message: String, recipient: String) {
    val smsPermissionState = rememberPermissionState(Manifest.permission.SEND_SMS)
    MyTextButton(index, 16.dp, 16.dp, text = stringResource(R.string.activate)) {

        if (!smsPermissionState.status.isGranted) {
            smsPermissionState.launchPermissionRequest()
        }

        try {
            // Display the message in the system messaging app
            displayMessageInSystemApp(context, message, recipient)
        } catch (e: Exception) {
            // Handle any errors and display a toast message
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_LONG).show()
        }

        FirebaseAnalytics.getInstance(context).logEvent("Service_click", null)
    }
}

fun displayMessageInSystemApp(context: Context, message: String, recipient: String) {
    // Launch the system messaging app with the message and recipient pre-filled
    val smsUri = Uri.parse("smsto:$recipient")
    val intent = Intent(Intent.ACTION_SENDTO, smsUri)
    intent.putExtra("sms_body", message)
    context.startActivity(intent)
}
