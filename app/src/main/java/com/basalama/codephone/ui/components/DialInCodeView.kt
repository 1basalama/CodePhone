package com.basalama.codephone.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import com.google.firebase.analytics.FirebaseAnalytics


@Composable
fun DialInCodeView(index: Int, context: Context, txt: String, dialInCode: String, s: Dp, e: Dp) {
    //val ctx = LocalContext.current
    MyTextButton(index,s, e, text = txt) { // on below line we are opening the dialer of our
        // phone and passing phone number.
        // Use format with "tel:" and phoneNumber created is
        // stored in u.

        val u = Uri.parse("tel:$dialInCode")

        // Create the intent and set the data for the
        // intent as the phone number.
        val i = Intent(Intent.ACTION_DIAL, u)
        try {

            // Launch the Phone app's dialer with a phone
            // number to dial a call.
            context.startActivity(i)
        } catch (s: SecurityException) {

            // show() method display the toast with
            // exception message.
            Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                .show()
        }

        FirebaseAnalytics.getInstance(context).logEvent("Service_click", null)
    }


}

