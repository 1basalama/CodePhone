package com.basalama.codephone.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.basalama.codephone.viewmodel.myThemes

@Composable
fun MyTextButton(index: Int, s: Dp, e: Dp, text: String, onClick: () -> Unit) {
    Button(
        colors = ButtonColors(containerColor=myThemes[index].onPrimary,
             contentColor= myThemes[index].onPrimary ,
         disabledContainerColor=myThemes[index].onPrimary ,
     disabledContentColor= myThemes[index].onPrimary) ,
        onClick = onClick,
        modifier = Modifier.padding(1.dp),
        shape = RoundedCornerShape(topStart = e, topEnd = s, bottomEnd = s, bottomStart = e),
        contentPadding = PaddingValues()
    ) {
        Text( color = Color.Black.copy(alpha = 0.8f),
            text = text,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp)
                //.clickable(onClick = onClick)
        )
    }
}
