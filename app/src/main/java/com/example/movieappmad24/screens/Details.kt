package com.example.movieappmad24.screens

import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import coil.compose.AsyncImage
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.movieappmad24.components.GenTopAppBar
import com.example.movieappmad24.components.GenerateMovieCard
import com.example.movieappmad24.dependency_injection.Injector
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.viewmodel.DetailsMovieViewModel


@Composable
fun Details(navController: NavController, id: String?) {
    val viewModel: DetailsMovieViewModel = viewModel(factory = Injector.provideMovieViewModelFactory(context = LocalContext.current))
    val movie = viewModel.getMovieById(id = id)?.collectAsState()?.value
    Log.d("test", "test")
    if (movie != null) {
        Scaffold(
            topBar = {
                GenTopAppBar(
                    title = movie.movie.title,
                    details = true,
                    navController = navController
                )
            }
        ) { paddingValues ->
            GenerateMovieDetails(
                movie = movie,
                paddingValues = paddingValues,
                viewModel = viewModel
            )
        }
    }
}


@Composable
fun GenerateMovieDetails(movie: MovieWithImages, paddingValues: PaddingValues, viewModel: DetailsMovieViewModel) {
    Column(modifier = Modifier.padding(paddingValues)) {
        GenerateMovieCard(movie = movie, liking = { viewModel.updateFavourite(movie) })
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            ExoPlayerView(movie.movie.trailer)

            LazyRow {
                items(movie.movieImages.drop(1)) { image ->
                    AsyncImage(
                        model = image.url, contentDescription = "null"
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

