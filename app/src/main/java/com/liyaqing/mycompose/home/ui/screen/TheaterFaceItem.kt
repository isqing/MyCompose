package com.liyaqing.mycompose.home.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
@Composable
fun TheaterFaceItem(item: TheaterNodeItemBean) {
        ConstraintLayout() {
            val (image, title, subTitle) = createRefs()

            val model = URLDecoder.decode(item.cover, "utf-8")
            Log.d("qing--", model);
            AsyncImage(

                model = model,
                contentDescription = null,
                modifier = Modifier
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
                text = item.title,
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
