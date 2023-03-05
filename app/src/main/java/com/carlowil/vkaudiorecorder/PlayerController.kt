package com.carlowil.vkaudiorecorder


import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer


class PlayerController() {

    private val mediaPLayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
    }

    fun startAudio(path : String) {
        if(mediaPLayer.isPlaying) {
            stopAudio()
        } else {
            mediaPLayer.setDataSource(path)
            mediaPLayer.prepare()
            mediaPLayer.start()
        }
    }

    private fun stopAudio() {
        mediaPLayer.stop()
        mediaPLayer.reset()
        mediaPLayer.release()
    }

}