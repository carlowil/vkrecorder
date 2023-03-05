package com.carlowil.vkaudiorecorder

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.OvershootInterpolator
import androidx.core.app.ActivityCompat
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.material.card.MaterialCardView
import com.google.android.material.datepicker.MaterialCalendar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlin.math.min

class MainActivity : AppCompatActivity() {
    private lateinit var audioButton : FloatingActionButton
    private lateinit var rvRecords : RecyclerView
    private lateinit var records : ArrayList<Record>
    private lateinit var adapter : RecordsAdapter
    private val recordController = RecordController()

    private var countDownTimer : CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        rvRecords = findViewById(R.id.rvRecords)
        records = Record.getRecordList()
        adapter = RecordsAdapter(records)
        rvRecords.adapter = adapter
        rvRecords.layoutManager = LinearLayoutManager(this)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            777,
        )

        audioButton = findViewById<FloatingActionButton>(R.id.start_button).apply {
            setOnClickListener { onRecordStartStop() }
        }


    }

    private fun onRecordStartStop() {
        if(recordController.isAudioRecording()) {
            recordController.stopRecord()
            countDownTimer?.cancel()
            countDownTimer = null
        } else {
            recordController.startRecord()
//            countDownTimer = object  : CountDownTimer(60_000, VOLUME_UPDATE_DURATION) {
//                override fun onTick(p0: Long) {
//                    val volume = recordController.getVolume()
//                    Log.d(TAG, "Valome = $volume")
//
//                }
//            }
        }
    }

//    private fun handleVolume(volume: Int) {
//        val scale = min(8.0, volume / MAX_RECORD_AMPLITUDE + 1.0).toFloat()
//        Log.d(TAG, "Scale = $scale")
//
//        audioButton.animate()
//            .scaleX(scale)
//            .scaleY(scale)
//            .setInterpolator(interpolator)
//            .duration = VOLUME_UPDATE_DURATION
//    }

    private companion object {
        private val TAG = MainActivity::class.java.name
        private const val MAX_RECORD_AMPLITUDE = 32768.0
        private const val VOLUME_UPDATE_DURATION = 100L
        private val interpolator = OvershootInterpolator()
    }
}