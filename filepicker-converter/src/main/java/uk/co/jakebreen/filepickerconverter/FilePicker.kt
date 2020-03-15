package uk.co.jakebreen.filepickerconverter

import android.app.Activity
import android.content.Intent

class FilePicker {

    companion object Builder {
        const val REQUEST_CODE = 1

        const val FILTER_VIDEO = "video/*"
        const val FILTER_IMAGE = "image/*"
        const val FILTER_AUDIO = "audio/*"
        const val FILTER_TEXT = "text/*"
        const val FILTER_ANY = "*/*"

        const val EXTRA_FILTERS = "EXTRA_FILTERS"

        var filter: MutableList<String> = mutableListOf()

        fun filter(type: String) = apply { this.filter.add(type) }

        fun with(activity: Activity) = createIntent(activity)

        private fun createIntent(activity: Activity) {
            if (filter.isEmpty()) filter.add(FILTER_ANY)

            val intent = Intent(activity, FilePickerActivity::class.java).apply {
                action = Intent.ACTION_OPEN_DOCUMENT
                type = "*/*"
                addCategory(Intent.CATEGORY_OPENABLE)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                putExtra(EXTRA_FILTERS, filter.toTypedArray())
                putExtra(Intent.EXTRA_MIME_TYPES, filter.toTypedArray())
            }

            //Todo handle elsewhere
            activity.startActivityForResult(intent, REQUEST_CODE)
        }

    }

}