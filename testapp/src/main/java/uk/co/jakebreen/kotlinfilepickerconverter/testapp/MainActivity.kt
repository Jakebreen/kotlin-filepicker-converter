package uk.co.jakebreen.kotlinfilepickerconverter.testapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

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
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            type = "*/*"
        }
        startActivityForResult(intent,
            REQUEST_CODE
        )
    }

    private fun pickImageMultiple() {
        intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            type = "image/*"
        }
        startActivityForResult(intent,
            REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (REQUEST_CODE == requestCode && Activity.RESULT_OK == resultCode) {
            data?.data.also { uri -> if (uri != null) Log.d("XXX", "onActivityResult() i=$uri") }
            data?.clipData.also { clip -> if (clip != null) Log.d("XXX", "onActivityResult() i=$clip") }
        }
    }
}
