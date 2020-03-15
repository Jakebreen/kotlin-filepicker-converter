package uk.co.jakebreen.filepickerconverter.testapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.jakebreen.filepickerconverter.FilePicker
import uk.co.jakebreen.filepickerconverter.FilePicker.Builder.FILTER_IMAGE
import uk.co.jakebreen.filepickerconverter.FilePickerConverter


class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnImageMulti.setOnClickListener { pickImageMultiple() }
    }

    private fun pickImageMultiple() {
        FilePicker.filter(FILTER_IMAGE).with(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            data?.let { intentData ->
                if (intentData.clipData != null) {
                    intentData.clipData?.run {
                        for (i in 0 until itemCount) {
                            val uri: Uri = intentData.clipData!!.getItemAt(i).uri
                            val file =  FilePickerConverter.uriToFile(uri, applicationContext)
                        }
                    }
                } else {
                    val file = FilePickerConverter.uriToFile(intentData.data!!, applicationContext)
                }
            }
        }
    }

}
