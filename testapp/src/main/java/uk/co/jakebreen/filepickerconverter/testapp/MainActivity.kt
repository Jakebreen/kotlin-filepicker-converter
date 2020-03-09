package uk.co.jakebreen.filepickerconverter.testapp

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.jakebreen.filepickerconverter.FilePicker


class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_CODE: Int = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnImageMulti.setOnClickListener { pickImageMultiple() }
        btnAnyMulti.setOnClickListener { pickAnyMultiple() }
    }

    private fun pickAnyMultiple() {
        intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            addFlags(FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            type = "*/*"
        }
        startActivityForResult(intent, REQUEST_CODE)
    }

    private fun pickImageMultiple() {
        intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            addFlags(FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            type = "image/*"
        }
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            val picker = FilePicker(applicationContext)
            data?.let { intentData ->
                if (intentData.clipData != null) {
                    intentData.clipData?.run {
                        for (i in 0 until itemCount) {
                            val uri: Uri = intentData.clipData!!.getItemAt(i).uri
                            picker.readUri(uri)
                        }
                    }
                } else {
                    picker.readUri(intentData.data!!)
                }
            }
        }
    }

}
