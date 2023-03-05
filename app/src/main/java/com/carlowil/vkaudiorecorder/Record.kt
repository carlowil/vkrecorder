package com.carlowil.vkaudiorecorder

import android.net.Uri
import android.os.Environment
import java.io.File

data class Record(val Name : String = "Без имени", val audioFile : String, val Date : String = "", val RecordTime : String = "") {
    companion object {
        fun getRecordList() : ArrayList<Record> {
            val records = ArrayList<Record>()
            val path = Environment.getExternalStorageDirectory().absolutePath + "/Recordings"
            File(path).walk().forEach {
                if(it.isFile) {
                    records.add(Record(audioFile = it.absolutePath))
                }
            }
            return records
        }
    }
}