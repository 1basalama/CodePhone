package com.basalama.codephone.ui.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basalama.codephone.R
import com.basalama.codephone.viewmodel.CodeModel
import com.basalama.codephone.viewmodel.myThemes

import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.analytics.FirebaseAnalytics


@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("Range", "Recycle")
@Composable
fun ContactPicker(

    index: Int,
    context: Context,
    codeModel: CodeModel,

    ) {

    var name by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }

    var text by remember { mutableStateOf("") }

    val contactsPermissionState = rememberPermissionState(
        Manifest.permission.READ_CONTACTS
    )
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickContact(),
        onResult = {
            val contentResolver: ContentResolver = context.contentResolver
            //var name = ""
            //var number = ""
            val cursor: Cursor? = contentResolver.query(it!!, null, null, null, null)
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    Log.d("Name", name)
                    val id =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val phones: Cursor? = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                        null,
                        null
                    )
                    if (phones != null) {
                        while (phones.moveToNext()) {
                            number =
                                phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            Log.d("Number", number)
                        }
                        phones.close()
                    }
                }
            }

            text = number

        }
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {



        OutlinedTextField(colors =  OutlinedTextFieldDefaults.colors(
            focusedBorderColor = myThemes[index].onPrimary,
            focusedTextColor = myThemes[index].onPrimary
            , focusedTrailingIconColor = myThemes[index].onPrimary ,
            focusedLabelColor = myThemes[index].onPrimary,

            unfocusedBorderColor = myThemes[index].background,
            unfocusedTextColor = myThemes[index].background
            , unfocusedTrailingIconColor = myThemes[index].onPrimary ,
            unfocusedLabelColor = myThemes[index].background
        ),

            value = text,
            onValueChange = {
                text = it
                number = it
            },
            label = { Text(stringResource(R.string.number)) },
            placeholder = {
                Text(
                    color = Color.White,
                    fontSize = 12.sp,
                    text = stringResource(R.string.contact_placeholder_des)
                )
            },
            singleLine = true,
            maxLines = 1,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            trailingIcon = {
                IconButton(onClick = {
                    if (!contactsPermissionState.status.isGranted) {
                        contactsPermissionState.launchPermissionRequest()
                    }
                    try {


                        launcher.launch()
                    } catch (e: Exception) {

                        Toast.makeText(
                            context,
                            "Error : " + e.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }


                }) {
                    Icon(Icons.Filled.AccountBox, contentDescription = "Search")
                }
            },
            modifier = Modifier.padding(bottom = 12.dp)
        )

        //[f] for forwarding messages (2 buttons + contact textField)  , [M] send contact as message  to code number
        //[t] transform balance (*130c*v#)2 textField a button

        when (codeModel.type) {
            'c' -> {
                MyTextButton(index, s = 16.dp, e = 16.dp, text = stringResource(R.string.activate)) {
                    if( number.length >= 9) {
                        try {
                            number = number.substring(number.length - 9)
                            text = number
                            val u = Uri.parse("tel:${codeModel.code1}$number${codeModel.code2}")

                            // Create the intent and set the data for the
                            // intent as the phone number.
                            val i = Intent(Intent.ACTION_DIAL, u)


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


            }

            'f' -> {
                Row {
                    MyTextButton(
                        index,
                        s = 0.dp,
                        e = 16.dp,
                        text = stringResource(R.string.activate)
                    ) {
                        if( number.length >= 9) {
                            try {
                                number = number.substring(number.length - 9)

                                text = number
                                val u = Uri.parse("tel:${codeModel.code1}$number")

                                // Create the intent and set the data for the
                                // intent as the phone number.
                                val i = Intent(Intent.ACTION_DIAL, u)


                                // Launch the Phone app's dialer with a phone
                                // number to dial a call.
                                context.startActivity(i)
                            } catch (s: SecurityException) {

                                // show() method display the toast with
                                // exception message.
                                Toast.makeText(context, "An error occurred", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }

                        FirebaseAnalytics.getInstance(context).logEvent("Service_click", null)
                    }
                    DialInCodeView(
                        index,
                        context,
                        stringResource(R.string.deactivate),
                        codeModel.code2,
                        16.dp,
                        0.dp
                    )
                }

            }
            'M'->{

                SendSms(index, context = context, number, codeModel.code1)

            }

            't'->{
                //[t] transform balance (*130c*v#)2 textField a button
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    var amount by remember { mutableStateOf("") }
                    var pin by remember { mutableStateOf("1234") }
                Row {



                    OutlinedTextField(
                        colors =  OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = myThemes[index].onPrimary,
                            focusedTextColor = myThemes[index].onPrimary
                            , focusedTrailingIconColor = myThemes[index].onPrimary ,
                            focusedLabelColor = myThemes[index].onPrimary,

                            unfocusedBorderColor = myThemes[index].background,
                            unfocusedTextColor = myThemes[index].background
                            , unfocusedTrailingIconColor = myThemes[index].onPrimary ,
                            unfocusedLabelColor = myThemes[index].background

                        ),
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("اختر قيمة الرصيد") },


                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1F)
                            //.fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    )

                    OutlinedTextField(
                        colors =  OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = myThemes[index].onPrimary,
                            focusedTextColor = myThemes[index].onPrimary
                            , focusedTrailingIconColor = myThemes[index].onPrimary ,
                            focusedLabelColor = myThemes[index].onPrimary,

                            unfocusedBorderColor = myThemes[index].background,
                            unfocusedTextColor = myThemes[index].background
                            , unfocusedTrailingIconColor = myThemes[index].onPrimary ,
                            unfocusedLabelColor = myThemes[index].background

                        ),
                        value = pin,
                        onValueChange = { pin = it },
                        label = { Text("ادخل الرقم السري") },


                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier
                            .weight(1F)
                            //.fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp)
                    )
                }
                    DialInCodeView(
                        index = index,
                        context = context,
                        txt = stringResource(R.string.activate),
                        dialInCode ="${codeModel.code1}$pin*$number*$amount${codeModel.code2}",
                        s =16.dp,
                        e = 16.dp
                    )

                }

            }
            'T'->{
                //[t] transform balance (*130c*v#)2 textField a button

                Row ( horizontalArrangement= Arrangement.SpaceEvenly,
                    verticalAlignment= Alignment.CenterVertically,){

                    var amount by remember { mutableStateOf("") }

                    OutlinedTextField(
                        colors =  OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = myThemes[index].onPrimary,
                            focusedTextColor = myThemes[index].onPrimary
                            , focusedTrailingIconColor = myThemes[index].onPrimary ,
                            focusedLabelColor = myThemes[index].onPrimary,

                            unfocusedBorderColor = myThemes[index].background,
                            unfocusedTextColor = myThemes[index].background
                            , unfocusedTrailingIconColor = myThemes[index].onPrimary ,
                            unfocusedLabelColor = myThemes[index].background

                        ),
                        value = amount,
                        onValueChange = { amount = it },
                        label = { Text("اختر قيمة الرصيد") },


                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.weight(2.5F)
                            //.fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    )

                    DialInCodeView(
                        index = index,
                        context = context,
                        txt = stringResource(R.string.activate),
                        dialInCode ="${codeModel.code1}$number*$amount${codeModel.code2}",
                        s =16.dp,
                        e = 16.dp
                    )

                }

            }



        }


    }

}

