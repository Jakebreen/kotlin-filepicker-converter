package uk.co.jakebreen.filepickerconverter

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import java.io.File
import java.io.FileOutputStream

class FilePickerConverter {

    companion object {
        private const val BYTE_BUFFER_SIZE = 4 * 1024

        fun uriToFile(uri: Uri, context: Context): File {
            var file = File("")
            context.contentResolver.query(uri, null, null, null, null)
                ?.use { cursor ->
                    cursor.moveToFirst()
                    val fileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                    file = File(context.externalCacheDir, fileName)
                    val outputStream = FileOutputStream(file)
                    val inputStream = context.contentResolver.openInputStream(uri)

                    outputStream.use {
                        it.write(BYTE_BUFFER_SIZE)
                        it.close()
                    }

                    inputStream?.close()
                }
            return file
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