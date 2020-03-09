package uk.co.jakebreen.filepickerconverter

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

class FilePicker(private val context: Context) {

    fun readUri(uri: Uri) {
        context.contentResolver.query(uri, null, null, null, null)
            ?.use { cursor ->
                val indexName = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                val indexSize = cursor.getColumnIndex(OpenableColumns.SIZE)
                cursor.moveToFirst()

                val fileName = cursor.getString(indexName)
                val fileSize = cursor.getString(indexSize)
                val file = File(context.externalCacheDir, fileName)
                val outputStream = FileOutputStream(file)
                val inputStream = context.contentResolver.openInputStream(uri)

                inputStream?.copyTo(
                    outputStream,
                    fileSize.toInt()
                )

                outputStream.close()
                inputStream?.close()
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