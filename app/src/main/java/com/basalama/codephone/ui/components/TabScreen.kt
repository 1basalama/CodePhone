package com.basalama.codephone.ui.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.basalama.codephone.R
import com.basalama.codephone.viewmodel.MyViewModel
import com.basalama.codephone.viewmodel.myThemes
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(context: Context, viewModel: MyViewModel) {
    val mobileServicesTabs = listOf(

        R.drawable.sabaphone,
        R.drawable.yemen_mobile,
        R.drawable.you,
        //R.drawable.way


    )

    val pagerState = rememberPagerState(
        initialPage = 1,
        initialPageOffsetFraction = 0f,

        ) {
        mobileServicesTabs.size
    }


    val tabIndex = pagerState.currentPage




    val coroutineScope = rememberCoroutineScope()


    // TAB

    Column(
        modifier = Modifier//.background(Color.White)
            .fillMaxSize()
            .padding(0.dp).background(Color.DarkGray),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()

                .padding(horizontal = 4.dp ,0.dp)
                ,
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,

            ) {


            mobileServicesTabs.forEachIndexed { index, icon ->
                //val analytics = LocalAnalytics.current

                Card(

                    shape = RoundedCornerShape(
                        topStart = 12.dp,
                        topEnd =  12.dp,
                        bottomEnd = if (tabIndex == index) 0.dp else 12.dp,
                        bottomStart = if (tabIndex == index) 0.dp else 12.dp
                    ),
                    colors = CardColors(
                        containerColor = myThemes[index].background,
                        contentColor = myThemes[index].background,
                        disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        disabledContentColor = MaterialTheme.colorScheme.secondaryContainer


                    ),

                    //colors = CardDefaults.cardColors(),
                    //elevation =     CardDefaults.elevatedCardElevation(5.dp,5.dp,5.dp,5.dp,5.dp,5.dp),
                    modifier = Modifier
                        //.background(bg[index])
                        .weight(1F)
                        .padding(
                            start = 4.dp,
                            end = 4.dp,
                            top = 4.dp,
                            bottom = if (tabIndex == index) 0.dp else 8.dp
                        )
                        .clickable {
                            // Track an event

                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                                //analytics.trackEvent("Tab pressed", mapOf("tab" to index))
                            }
                        } //padding(16.dp)

                ) {
                   // Box( )
                    Image(modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
                        alignment = Alignment.Center,
                        //modifier = Modifier.fillMaxWidth(),
                        imageVector = ImageVector.vectorResource(id = icon),
                        contentDescription = "My Icon",
                        contentScale = ContentScale.FillWidth
                    )
                }

            }
        }



        // PAGER
        HorizontalPager(
            modifier = Modifier.background(Color.DarkGray),
            contentPadding = PaddingValues(horizontal = 4.dp),

            state = pagerState
        ) { index ->
            Column(

                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .fillMaxSize()
                    .background(myThemes[index].background),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Spacer(modifier = Modifier.padding(4.dp))
                CardsList(index ,context, viewModel.mobile_groups[index])


            }
        }
    }
}
