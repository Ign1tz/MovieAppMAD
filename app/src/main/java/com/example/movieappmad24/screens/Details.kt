package com.example.movieappmad24.screens

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movieappmad24.components.GenTopAppBar
import com.example.movieappmad24.components.GenerateMovieCard
import com.example.movieappmad24.components.MovieViewModel
import com.example.movieappmad24.components.Movie


@Composable
fun Details(movie: Movie, navController: NavController, movieViewModel: MovieViewModel) {
    Scaffold(
        topBar = {
            GenTopAppBar(title = movie.title, details = true, navController = navController)
        }
    ) { paddingValues ->
        GenerateMovieDetails(movie = movie, paddingValues = paddingValues, movieViewModel = movieViewModel)
    }
}


@Composable
fun GenerateMovieDetails(movie: Movie, paddingValues: PaddingValues, movieViewModel: MovieViewModel) {
    Column(modifier = Modifier.padding(paddingValues)) {
        GenerateMovieCard(movie = movie, movieViewModel = movieViewModel, liking = { movie -> movieViewModel.liking(movie) })
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            ExoPlayerView(movie.trailer)

            LazyRow {
                items(movie.images) { imageURL ->
                    AsyncImage(
                        model = imageURL, contentDescription = "null"
                    )
                }
            }

        }
    }
}


@OptIn(UnstableApi::class) @Composable
fun ExoPlayerView(trailer: String) {
    val context = LocalContext.current
    val uri = "android.resource://" + context.getPackageName() + "/${context.resources.getIdentifier(trailer, "raw", context.packageName)}" // overrides because kotlin is anoying
    val exoPlayer = ExoPlayer.Builder(context).build()



    val mediaSource = remember(uri) {
        MediaItem.fromUri(uri)
    }

    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }


    LifecycleEventEffect(Lifecycle.Event.ON_STOP){
        exoPlayer.pause()
    }

    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                setShowNextButton(false)
                setShowPreviousButton(false)
            }
        },
        modifier = Modifier
            .height(230.dp)
            .padding(5.dp)
    )
}

