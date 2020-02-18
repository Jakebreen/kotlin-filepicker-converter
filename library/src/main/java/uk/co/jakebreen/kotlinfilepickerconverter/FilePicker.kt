package uk.co.jakebreen.kotlinfilepickerconverter

import android.content.Context
import android.net.Uri
import android.util.Log

class FilePicker(val context: Context) {

    fun readUri(uri: Uri) {
        val resolver = context.contentResolver
        resolver.openInputStream(uri).use { stream ->
            // Perform operations on "stream".
            Log.d("XXX %s", stream.toString())
        }
    }

//    image/jpeg
//    audio/mpeg4-generic
//    text/html
//    audio/mpeg
//    audio/aac
//    audio/wav
//    audio/ogg
//    audio/midi
//    audio/x-ms-wma
//    video/mp4
//    video/x-msvideo
//    video/x-ms-wmv
//    image/png
//    image/jpeg
//    image/gif
//    .xml ->text/xml
//    .txt -> text/plain
//    .cfg -> text/plain
//    .csv -> text/plain
//    .conf -> text/plain
//    .rc -> text/plain
//    .htm -> text/html
//    .html -> text/html
//    .pdf -> application/pdf
//    .apk -> application/vnd.android.package-archive

}