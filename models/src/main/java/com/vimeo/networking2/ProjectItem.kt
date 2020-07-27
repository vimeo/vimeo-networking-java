@file:JvmName("ProjectItemUtils")

package com.vimeo.networking2

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.vimeo.networking2.enums.ProjectItemType
import com.vimeo.networking2.enums.asEnum

/**
 * A container that encapsulates [Video] or [Folder] data.
 */
@JsonClass(generateAdapter = true)
data class ProjectItem(

    /**
     *  The type of the item.
     */
    @Json(name = "type")
    val rawType: String? = null,

    /**
     *  The item [Folder] if [type] == FOLDER, null otherwise.
     */
    @Json(name = "folder")
    val folder: Folder? = null,

    /**
     *  The item [Video] if [type] == VIDEO, null otherwise.
     */
    @Json(name = "video")
    val video: Video? = null
)

/**
 * @see ProjectItem.rawType
 * @see ProjectItemType
 */
val ProjectItem.type: ProjectItemType
    get() = rawType.asEnum(ProjectItemType.UNKNOWN)
