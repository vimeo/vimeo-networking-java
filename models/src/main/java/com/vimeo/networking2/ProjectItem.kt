@file:JvmName("ProjectItemUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ProjectItemType
import com.vimeo.networking2.enums.asEnum

/**
 * A container that encapsulates [Video] or [Folder] data.
 *
 * @param rawType The type of the item. See [ProjectItem.type].
 * @param folder The item is [Folder] if [type] == [ProjectItemType.FOLDER], `null` otherwise.
 * @param video The item is [Video] if [type] == [ProjectItemType.VIDEO], `null` otherwise.
 */
@JsonClass(generateAdapter = true)
data class ProjectItem(

    @Json(name = "type")
    val rawType: String? = null,

    @Json(name = "folder")
    val folder: Folder? = null,

    @Json(name = "video")
    val video: Video? = null
)

/**
 * @see ProjectItem.rawType
 * @see ProjectItemType
 */
val ProjectItem.type: ProjectItemType
    get() = rawType.asEnum(ProjectItemType.UNKNOWN)
