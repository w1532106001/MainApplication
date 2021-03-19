package com.whc.asmrMusic.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.whc.asmrMusic.R
import com.whc.asmrMusic.databinding.FragmentPlay1Binding
import com.whc.asmrMusic.ui.base.BaseFragment


class Play1Fragment : BaseFragment() {

    lateinit var binding: FragmentPlay1Binding

    override fun createContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentPlay1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jumpButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.add(R.id.layout_content, Play2Fragment())?.addToBackStack(null)?.commit()
//            fragmentManager?.beginTransaction()?.add(R.id.layout_base,Play2Fragment(),null)?.commit()
//            activity?.supportFragmentManager?.beginTransaction()?.add(Play2Fragment(),null)?.commit()
//            findNavController().navigate(R.id.action_play1Fragment_to_play2Fragment,null)
        }
        play("http://test2.smartmicky.com/media/clip/101395-0.000-10000.000.mp4")
    }


    private fun play(destFile: String?) {
        binding.apply {
            if (simpleExoPlayerView?.player != null) {
                simpleExoPlayerView?.player?.stop()
                simpleExoPlayerView?.player?.release()
                simpleExoPlayerView?.player = null
            }
            context?.let { mContext ->
                destFile?.let {
                    val uri = Uri.parse(destFile)
                    val adaptiveTrackSelectionFactory = AdaptiveTrackSelection.Factory(
                        DefaultBandwidthMeter()
                    )
                    val player = ExoPlayerFactory.newSimpleInstance(
                        mContext, DefaultRenderersFactory(mContext),
                        DefaultTrackSelector(adaptiveTrackSelectionFactory),
                        DefaultLoadControl()
                    )
                    player.repeatMode = Player.REPEAT_MODE_ALL
                    simpleExoPlayerView?.player = player
                    simpleExoPlayerView?.keepScreenOn = true
                    (simpleExoPlayerView?.player as? SimpleExoPlayer)?.videoScalingMode =
                        C.VIDEO_SCALING_MODE_SCALE_TO_FIT
                    player.prepare(buildMediaSource(uri))
                    player.playWhenReady = true
                }
            }
        }

    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val extractorFactory = DefaultExtractorsFactory()
        extractorFactory.setMp4ExtractorFlags(Mp4Extractor.FLAG_WORKAROUND_IGNORE_EDIT_LISTS)
        return ExtractorMediaSource.Factory(DefaultDataSourceFactory(context, "exoplayer"))
            .setExtractorsFactory(extractorFactory).createMediaSource(uri)
    }
}