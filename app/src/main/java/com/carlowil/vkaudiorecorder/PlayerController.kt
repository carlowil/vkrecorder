package com.carlowil.vkaudiorecorder


import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer


class PlayerController(val path : String) {
    private var wasPlaying = false

    private var mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        setDataSource(path)
    }


    fun play() {
        if(mediaPlayer.isPlaying) {
            clearMediaPLayer()
            wasPlaying = true
        }
        if (!wasPlaying) {
            mediaPlayer.prepare()
            mediaPlayer.start()
        }
        wasPlaying = false
    }


    private fun clearMediaPLayer() {
        mediaPlayer.stop()
        mediaPlayer.reset()
        mediaPlayer.release()
    }
}