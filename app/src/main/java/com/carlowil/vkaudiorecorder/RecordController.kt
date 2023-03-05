package com.carlowil.vkaudiorecorder

import android.media.MediaRecorder
import android.os.Environment
import android.util.Log


class RecordController() {

    private var recorder : MediaRecorder? = null
    fun startRecord() {
        Log.d(TAG, "Start recorder")

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioChannels(1);
            setOutputFile(getAudioPath())
            prepare()
            start()
        }

    }

    fun stopRecord() {
        recorder?.let {
            Log.d(TAG, "Stop recorder")
            it.stop()
            it.release()
        }
        recorder = null
    }

    fun isAudioRecording() = recorder != null

    fun getVolume() = recorder?.maxAmplitude ?: 0

    private fun getAudioPath(): String {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/Recordings/${System.currentTimeMillis()}.wav"
        Log.i(TAG, "Recorded: $path")
        return path
    }

    private companion object {
        private val TAG = RecordController::class.java.name
    }
}

