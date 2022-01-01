package xyz.theprogramsrc.translationsmodule.objects

import xyz.theprogramsrc.filesmodule.config.YmlConfig
import xyz.theprogramsrc.filesmodule.utils.folder
import xyz.theprogramsrc.translationsmodule.TranslationManager
import java.io.File

/**
 * Representation of a translation.
 * @param id The id of the translation
 * @param defaultValue The default value of the translation.
 * @param language The language of the translation. (Defaults to "en")
 * @param colors The colors to use in the translation replacing strings. Example (using color '&c'): '**test**' should return '&ctest'. Defaults to empty array.
 * @param placeholders The placeholders to use in the translation replacing strings. Example (using placeholder id 'test' and value 'test_value'): '{test}' should return 'test_value'.
 *                      You can use '{}' or '%%' as placeholder identifiers like '{test}' or '%test%'. Defaults to empty map.
 */
data class Translation(val id: String, val defaultValue: String, val language: String = "en", val colors: Array<String> = emptyArray()) {

    /**
     * Translates this [Translation] to the current language.
     * @param group The group (folder) of the translation. Defaults to "common"
     * @param language The language of the translation. Set to null to use the default language. Defaults to null
     * @return The translated string.
     */
    fun translate(group: String = "common", language: String? = null): String {
        val file = YmlConfig(File(File("translations/${if(group.endsWith("/")) group else "$group/"}").folder(), (language ?: TranslationManager.getCurrentLanguage()) + ".lang"))
        var translation = if(file.has(id)) file.getString(id) else defaultValue
        for(i in colors.indices) {
            try {
                val color = colors[i]
                val string = Regex("\\*\\*(.+?)\\*\\*").findAll(translation).first().groupValues[1]
                translation = translation.replaceFirst("**$string**", "$color$string")
            }catch (_: Exception){}
        }

        return translation
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Translation

        if (id != other.id) return false
        if (defaultValue != other.defaultValue) return false
        if (!colors.contentEquals(other.colors)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + defaultValue.hashCode()
        result = 31 * result + colors.contentHashCode()
        return result
    }
}
