package com.carlowil.vkaudiorecorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.OvershootInterpolator
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        }
    }


    private companion object {
        private val TAG = MainActivity::class.java.name
        private const val MAX_RECORD_AMPLITUDE = 32768.0
        private const val VOLUME_UPDATE_DURATION = 100L
        private val interpolator = OvershootInterpolator()
    }
}