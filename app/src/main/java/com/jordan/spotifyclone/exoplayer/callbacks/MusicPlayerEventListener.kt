package com.jordan.spotifyclone.exoplayer.callbacks

import android.widget.Toast
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.jordan.spotifyclone.exoplayer.MusicService

class MusicPlayerEventListener(
    private val musicService: MusicService
): Player.Listener {

    private var readyState: Boolean = false

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        if (playbackState == Player.STATE_READY) {
            readyState = true
        }
    }

    override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
        super.onPlayWhenReadyChanged(playWhenReady, reason)
        if (readyState && !playWhenReady) {
            // 這個是结束前台的服物，通知欄中該通知會隨著點擊或者滑動而删除。
            // 參數是：是否删除之前發送的通知，true：删除。false：不删除 （用手滑動或者點擊通知會被删除）
            musicService.stopForeground(false)
        }
    }

    override fun onPlayerError(error: PlaybackException) {
        super.onPlayerError(error)
        Toast.makeText(musicService, "An unknown error occurred!", Toast.LENGTH_SHORT).show()
    }
}