package com.basalama.codephone.ui.components

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


import com.basalama.codephone.R
import com.basalama.codephone.viewmodel.CodeModel
import com.basalama.codephone.viewmodel.myThemes

@Composable
fun CardsList(index: Int, context: Context, services: List<CodeModel>) {
    val listState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 6.dp,start = 6.dp, end = 6.dp, bottom = 130.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { BannerAdView(false) }
        item {
            for (s in services)
                JetpackCard(index,context,s)
        }
        item { BannerAdView(false) }
    }
}

@Composable
fun JetpackCard(index: Int, context: Context, s: CodeModel) {

    var isExpanded by remember { mutableStateOf(false) }

    Card(
        colors = CardColors(containerColor= myThemes[index].primary,
             contentColor=myThemes[index].onPrimary ,
            disabledContainerColor=MaterialTheme.colorScheme.primaryContainer,
             disabledContentColor=MaterialTheme.colorScheme.secondaryContainer

        )
    ,

        //colors = CardDefaults.cardColors(),
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { isExpanded = !isExpanded } //padding(16.dp)

        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

            ) {
                Text(
                    color = Color.White ,
                    text = s.title,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.wrapContentWidth()
                )

                if (isExpanded) {
                    HorizontalDivider(
                        modifier = Modifier.padding(4.dp),
                        color= myThemes[index].background )
                    Text(

                        text = s.description,
                        modifier = Modifier.padding(top = 8.dp),
                        color = myThemes[index].background,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    HorizontalDivider(modifier = Modifier.padding(2.dp),color= myThemes[index].background)

                    //val context = LocalContext.current

                    //[0] only description only [1] call only,  [2] for activate/deactivate,
                    // [c] select contact to call,  [m] send sms message ,  [s] scan recharge card

                    //[f] for forwarding messages (2 buttons + contact textField)  , [M] send contact as message  to code number
                    //[t] transform balance (*130c*v#)2 textField a button [T]
                    when (s.type) {

                        '1','n' -> {
                            DialInCodeView(
                                index,
                                context,if(s.type == '1'){
                                stringResource(R.string.activate)}else{"اتصل"},
                                s.code1,
                                16.dp,
                                16.dp
                            )
                        }

                        '2' -> {
                            Row {
                                DialInCodeView(
                                    index,
                                    context,
                                    stringResource(R.string.activate),
                                    s.code1,
                                    0.dp,
                                    16.dp
                                )
                                DialInCodeView(
                                    index,
                                    context,
                                    stringResource(R.string.deactivate),
                                    s.code2,
                                    16.dp,
                                    0.dp
                                )
                            }
                        }

                        '6' -> {
                            Row(verticalAlignment= Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                Text(modifier = Modifier.weight(3F),textAlign =  TextAlign.Center  , text = "للإشتراك بهذه الخدمة")
                                Box(modifier = Modifier.weight(1F)) {
                                    DialInCodeView(
                                        index,
                                        context,
                                        stringResource(R.string.activate),
                                        "*554*1#",
                                        16.dp,
                                        16.dp
                                    )
                                }
                            }



                            Row(verticalAlignment= Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                Text(modifier = Modifier.weight(3F),textAlign =  TextAlign.Center  , text = "لإضافة رقم إلى قائمة الخدمة")
                                Box(modifier = Modifier.weight(1F)) {
                                    DialInCodeView(
                                        index,
                                        context,
                                        stringResource(R.string.activate),
                                        "*554*2#",
                                        16.dp,
                                        16.dp
                                    )
                                }
                            }


                            Row(verticalAlignment= Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                Text(modifier = Modifier.weight(3F),textAlign =  TextAlign.Center  , text = "لتعديل أي رقم من القائمة الحالية")
                                Box(modifier = Modifier.weight(1F)) {
                                    DialInCodeView(
                                        index,
                                        context,
                                        stringResource(R.string.activate),
                                        "*554*3#",
                                        16.dp,
                                        16.dp
                                    )
                                }
                            }


                            Row(verticalAlignment= Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                Text(modifier = Modifier.weight(3F),textAlign =  TextAlign.Center  , text = "للإستعلام عن الأرقام التي اخترتها مسبقاً")
                                Box(modifier = Modifier.weight(1F)) {
                                    DialInCodeView(
                                        index,
                                        context,
                                        stringResource(R.string.activate),
                                        "*554*4#",
                                        16.dp,
                                        16.dp
                                    )
                                }
                            }
                            Row(verticalAlignment= Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                                Text(modifier = Modifier.weight(3F),textAlign =  TextAlign.Center  , text = "لإلغاء أي رقم من القائمة الحالية")
                                Box(modifier = Modifier.weight(1F)) {
                                    DialInCodeView(
                                        index,
                                        context,
                                        stringResource(R.string.activate),
                                        "*554*5#",
                                        16.dp,
                                        16.dp
                                    )
                                }
                            }
                            Row( verticalAlignment= Alignment.CenterVertically,modifier = Modifier.fillMaxWidth()) {
                                Text(modifier = Modifier.weight(3F),textAlign =  TextAlign.Center  , text = "للإلغاء الإشتراك في هذه الخدمة")
                                Box(modifier = Modifier.weight(1F)) {
                                    DialInCodeView(
                                        index,
                                        context,
                                        stringResource(R.string.activate),
                                        "*554*6#",
                                        16.dp,
                                        16.dp
                                    )
                                }
                            }

                        }

                        'm' -> {
                            SendSms( index ,context = context, s.code1, s.code2)
                        }

                        'c','f','M','t' ,'T'-> {
                            ContactPicker(index ,context = context, s)
                        }

                        'x'->{

                            Column (verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,){
                                var old by remember { mutableStateOf("1234") }
                                var new by remember { mutableStateOf("") }
                                Row (
                                    horizontalArrangement= Arrangement.SpaceEvenly,
                                    verticalAlignment= Alignment.CenterVertically,
                                ){


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

                                        value = old,
                                        onValueChange = { old = it },
                                        label = { Text("الرقم القديم") },


                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                        modifier = Modifier
                                            .weight(1F)
                                            //.fillMaxWidth()
                                            .padding(horizontal = 8.dp, vertical = 8.dp)
                                    )

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

                                        value = new,
                                        onValueChange = { new = it },
                                        label = { Text(" الرقم الجديد") },


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
                                    dialInCode = "${s.code1}$old*$new${s.code2}",
                                    s = 16.dp,
                                    e = 16.dp
                                )
                            }
                        }


                        'v'->{

                            Column (verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally,){
                                var key by remember { mutableStateOf("") }

                                Row (
                                    horizontalArrangement= Arrangement.SpaceEvenly,
                                    verticalAlignment= Alignment.CenterVertically,
                                ){


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

                                        value = key,
                                        onValueChange = { key = it },
                                        label = { Text("رقم الشحن الذكي") },


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
                                    dialInCode = "${s.code1}$key${s.code2}",
                                    s = 16.dp,
                                    e = 16.dp
                                )
                            }
                        }

                    }


                }
            }




    }
}
