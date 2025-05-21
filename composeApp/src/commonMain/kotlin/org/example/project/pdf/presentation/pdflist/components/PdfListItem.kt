package org.example.project.pdf.presentation.pdflist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import composemultiplaform.composeapp.generated.resources.Res
import composemultiplaform.composeapp.generated.resources.compose_multiplatform
import org.example.project.core.presentation.LightBlue
import org.example.project.core.presentation.SandYellow
import org.example.project.pdf.domain.Pdf
import org.jetbrains.compose.resources.painterResource
import kotlin.math.round

@Composable
fun PdfListItem(
    pdf: Pdf,
    onClick : () -> Unit,
    modifier: Modifier = Modifier
){
    Surface (
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(start = 10.dp, end = 10.dp),
        color = LightBlue.copy(alpha = 0.3f)
    ) {
        Row (
            modifier = Modifier
                .padding(14.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ){
                var imageLoadResult by remember {
                    mutableStateOf<Result<Painter>?>(null)
                }
                val painter = rememberAsyncImagePainter(
                    model = pdf.imageUrl,
                    onSuccess = {
                        if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1 ){
                            Result.success(it.painter)
                        }else{
                            Result.failure(Exception("Invalid Image Size"))
                        }
                    },
                    onError = {
                        it.result.throwable.printStackTrace()
                        imageLoadResult = Result.failure(it.result.throwable)
                    }
                )
                when(val result = imageLoadResult){
                    null -> CircularProgressIndicator()
                    else -> {
                        Image(
                            painter = if (result.isSuccess) painter else painterResource(Res.drawable.compose_multiplatform),
                            contentDescription = pdf.title,
                            contentScale = if (result.isSuccess){
                                ContentScale.Crop
                            }else{
                                ContentScale.Fit
                            },
                            modifier = Modifier
                                .aspectRatio(
                                    ratio = 0.65f,
                                    matchHeightConstraintsFirst = true
                                )
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ){
                Text(
                    text = pdf.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                pdf.authors.firstOrNull()?.let { authorName ->
                    Text(
                        text = authorName,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                pdf.averageRating?.let { rating ->
                    Row (
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Text(
                            //round to pick first decimal only eg 4.333 to 4.3
                            text = "${round(rating * 10) / 10.0}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Default.Star,
                            tint = SandYellow,
                            contentDescription = null,
                        )
                    }
                }
            }
            Icon(

                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}