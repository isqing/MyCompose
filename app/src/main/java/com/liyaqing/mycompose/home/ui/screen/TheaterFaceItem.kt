package com.liyaqing.mycompose.home.ui.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.liyaqing.mycompose.home.data.bean.TheaterNodeItemBean
import java.net.URLDecoder

/**
 * @Author: liyaqing
 * @Date: Create in 3:29 下午 2022/10/14
 * @Description:
 */
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun TheaterFaceItem(item: TheaterNodeItemBean) {
        ConstraintLayout(
            Modifier.wrapContentWidth()
        ) {
            val context  = LocalContext.current
            var titleValue by remember { mutableStateOf(item.title) }

            val (image, title, subTitle) = createRefs()
            val model = URLDecoder.decode(item.cover, "utf-8")
            AsyncImage(
                model = model,
                contentDescription = null,
                modifier = Modifier
                    .clickable(true){
                        titleValue="我是测试"
                        Toast.makeText(context,"好看",Toast.LENGTH_LONG).show()
                    }
                    .constrainAs(image) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top)
                    }
                    .size(98.dp, 117.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Crop,

            )

            Text(
                text = titleValue,
                fontSize = 14.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .constrainAs(title) {
                        start.linkTo(image.start)
                        end.linkTo(image.end)
                        top.linkTo(image.bottom)
                        width = Dimension.fillToConstraints

                    }
                    .clickable {

                    }
            )
            Text(
                text = item.update_text,
                fontSize = 12.sp,
                color = Color.Gray,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .constrainAs(subTitle) {
                        start.linkTo(image.start)
                        end.linkTo(image.end)
                        top.linkTo(title.bottom)
                        width = Dimension.fillToConstraints
                    },
                maxLines = 1
            )
        }

}
