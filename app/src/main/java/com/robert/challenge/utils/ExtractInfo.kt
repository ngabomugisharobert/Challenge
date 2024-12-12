package com.robert.challenge.utils

import android.os.Build
import android.text.format.DateUtils.formatDateTime
import androidx.annotation.RequiresApi
import com.robert.challenge.data.model.PhotoModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

object ExtractInfo {

    fun extractPhotoDetails(photo: PhotoModel): Map<String, String> {
        val details = mutableMapOf<String, String>()

        // Extract width and height using regex
        val widthRegex = """width="(\d+)"""".toRegex()
        val heightRegex = """height="(\d+)"""".toRegex()
        val descriptionRegex = """<p>(.*?)</p>""".toRegex()

        val widthMatch = widthRegex.find(photo.description)
        val heightMatch = heightRegex.find(photo.description)
        val descriptionMatch = descriptionRegex.findAll(photo.description)

        if (widthMatch != null) {
            details["width"] = widthMatch.groupValues[1]
        }
        if (heightMatch != null) {
            details["height"] = heightMatch.groupValues[1]
        }

        // Extract the real description
        descriptionMatch.forEach { matchResult ->
            val paragraph = matchResult.groupValues[1]
            if (!paragraph.contains("posted a photo") && !paragraph.contains("<a")) {
                details["description"] = paragraph.replace("<br />", "\n")
            }
        }

        details["author"] = extractAuthor(photo.author)
        details["published"] = formatDateTime(photo.published)

        return details
    }

    private fun extractAuthor(emailWithAuthor: String): String {
        val authorRegex = """.* \("(.+?)"\)""".toRegex()
        val matchResult = authorRegex.find(emailWithAuthor)
        return matchResult?.groupValues?.get(1) ?: ""
    }

    private fun formatDateTime(dateTimeString: String): String {

        val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        isoDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = isoDateFormat.parse(dateTimeString)
        val desiredDateFormat = SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
        desiredDateFormat.timeZone =
            TimeZone.getDefault() // Format the Date object to the desired format
        return desiredDateFormat.format(date)
    }
}

