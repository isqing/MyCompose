package com.liyaqing.mycompose.home.ui.theme

import android.text.TextUtils
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.liyaqing.mycompose.R
import com.liyaqing.mycompose.home.data.SmallTheaterViewModel
import com.liyaqing.mycompose.home.ui.screen.GoTheaterScreen
import com.liyaqing.mycompose.home.ui.screen.MineScreen
import com.liyaqing.mycompose.home.ui.screen.SmallTheaterScreen

/**
 * @Author: liyaqing
 * @Date: Create in 4:35 下午 2022/9/20
 * @Description:
 */
object RouteUrl {
    const val SmallTheater: String = "SmallTheater";
    const val GoTheater: String = "GoTheater";
    const val Mine: String = "Mine";
}

sealed class BottomBarBean(
    val route: String,
    val iconName: String,
    @DrawableRes val selectIconUrl: Int,
    @DrawableRes val unSelectIconUrl: Int
) {
    object SmallTheater : BottomBarBean(
        RouteUrl.SmallTheater,
        "小剧场",
        R.drawable.icon_small_theater_select,
        R.drawable.icon_small_theater_unselect
    );
    object GoTheater : BottomBarBean(
        RouteUrl.GoTheater,
        "追剧",
        R.drawable.icon_go_theater_select,
        R.drawable.icon_go_theater_unselect
    );

    object Mine : BottomBarBean(
        RouteUrl.Mine,
        "我的",
        R.drawable.icon_mine_select,
        R.drawable.icon_mine_unselect
    );
}

@Composable
fun HomeNavigation(
    mOwner: LifecycleOwner,
    viewModel: SmallTheaterViewModel = viewModel()
) {
    val navController = rememberNavController()
    val items = listOf(
        BottomBarBean.SmallTheater,
        BottomBarBean.GoTheater,
        BottomBarBean.Mine,
    )
    var currentRoute by remember { mutableStateOf(RouteUrl.SmallTheater) }
    var selectIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                for (i in items.indices) {
                    var screen: BottomBarBean = items.get(i);
                    BottomNavigationItem(
                        modifier = Modifier.background(Color.White),
                        icon = {
//                            Log.d("qing==index", "$selectIndex == $i");

                            Icon(
                                painter = painterResource(
//                                    id = if (selectIndex == i)  screen.selectIconUrl
                                     screen.unSelectIconUrl
                                ),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp, 24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = screen.iconName,
                                color = if (selectIndex == i) colorResource(id = R.color.color_FFB13E)
                                else colorResource(id = R.color.color_332823)
                            )
                        },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        selectedContentColor = colorResource(id = R.color.color_FFB13E),
                        unselectedContentColor = colorResource(id = R.color.color_332823),
                        onClick = {
                            //不判断会报导航报错
                            selectIndex=i;
//                            if (selectIndex == items.) return@BottomNavigationItem
                            currentRoute = screen.route;
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = RouteUrl.SmallTheater,
            Modifier.padding(innerPadding)
        ) {

            composable(RouteUrl.SmallTheater) {
                if (!TextUtils.equals(
                        currentRoute,
                        RouteUrl.SmallTheater
                    )
                ) return@composable
                SmallTheaterScreen(
                    viewModel
                )
            }


            composable(RouteUrl.GoTheater) {
                if (!TextUtils.equals(
                        currentRoute,
                        RouteUrl.GoTheater
                    )
                ) return@composable
                Log.d("qing==v1", "=====$currentRoute")
                GoTheaterScreen()
            }


            composable(RouteUrl.Mine) {
                if (!TextUtils.equals(
                        currentRoute,
                        RouteUrl.Mine
                    )
                ) return@composable
                Log.d("qing==v2", "=====")
                MineScreen()
            }

        }
    }
}