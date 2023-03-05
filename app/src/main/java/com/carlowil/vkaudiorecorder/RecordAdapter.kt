package com.carlowil.vkaudiorecorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.LinearProgressIndicator


class RecordsAdapter(private val mRecords : List<Record>) : RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {
    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val recordName: TextView = itemView.findViewById(R.id.record_name)
        val playButton: FloatingActionButton = itemView.findViewById(R.id.play_button)
        val recordDate: TextView = itemView.findViewById(R.id.record_time)
        val progressBar: LinearProgressIndicator = itemView.findViewById(R.id.progress_bar)
        val timePlay: TextView = itemView.findViewById(R.id.time_play)
    }

    override fun onBindViewHolder(holder: RecordsAdapter.ViewHolder, position: Int) {
        val record : Record = mRecords[position]
        val player = PlayerController(record.audioFile)
        holder.recordName.text = record.Name
        holder.playButton.setOnClickListener {
            player.play()
        }
    }

    override fun getItemCount(): Int {
        return mRecords.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val recordView = inflater.inflate(R.layout.record_item, parent, false)
        return ViewHolder(recordView)
    }
}