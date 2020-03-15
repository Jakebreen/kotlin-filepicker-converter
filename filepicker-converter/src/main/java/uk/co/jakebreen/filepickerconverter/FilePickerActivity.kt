package uk.co.jakebreen.filepickerconverter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FilePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_picker)
    }

    fun pick() {
        startActivityForResult(intent, FilePicker.REQUEST_CODE)
    }

}
