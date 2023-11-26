package com.example.monylover.models

import androidx.room.Entity


@Entity(tableName = "recurrence")

sealed class Recurrence(val name: String, val target: String) {
        object None : Recurrence("None", "None")
        object Daily : Recurrence("Daily", "Today")
        object Weekly : Recurrence("Weekly", "This week")
        object Monthly : Recurrence("Monthly", "This month")
        object Yearly : Recurrence("Yearly", "This year")
}

class RecurrenceTypeAdapter : TypeAdapter<Recurrence>() {
        override fun write(writer: JsonWriter?, value: Recurrence?) {
                writer?.value(value?.name)
        }

        override fun read(reader: JsonReader?): Recurrence {
                reader?.let {
                        if (it.peek() == JsonToken.NULL) {
                                it.nextNull()
                                return Recurrence.None
                        }

                        it.beginObject() // Start reading the JSON object

                        // Assuming your Recurrence JSON structure has "name" and "target" fields
                        var name = ""
                        var target = ""

                        while (it.hasNext()) {
                                when (it.nextName()) {
                                        "name" -> name = it.nextString()
                                        "target" -> target = it.nextString()
                                        else -> it.skipValue() // Ignore other fields if any
                                }
                        }

                        it.endObject() // End reading the JSON object

                        return when (name) {
                                "None" -> Recurrence.None
                                "Daily" -> Recurrence.Daily
                                "Weekly" -> Recurrence.Weekly
                                "Monthly" -> Recurrence.Monthly
                                "Yearly" -> Recurrence.Yearly
                                else -> Recurrence.None
                        }
                } ?: return Recurrence.None
        }
}