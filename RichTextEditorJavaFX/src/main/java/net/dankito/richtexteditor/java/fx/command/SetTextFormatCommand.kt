package net.dankito.richtexteditor.java.fx.command

import net.dankito.richtexteditor.Icon
import net.dankito.richtexteditor.JavaScriptExecutorBase
import net.dankito.richtexteditor.command.CommandName
import net.dankito.richtexteditor.java.fx.JavaFXIcon
import net.dankito.richtexteditor.java.fx.localization.Localization


class SetTextFormatCommand(localization: Localization, icon: Icon = JavaFXIcon.fromResourceName("ic_text_format_black_36dp.png"))
    : SelectValueCommand(CommandName.FORMATBLOCK, icon) {

    private val defaultFormat = localization.getLocalizedString("text.format.paragraph")

    private val textFormats = listOf(
            localization.getLocalizedString("text.format.heading.1"),
            localization.getLocalizedString("text.format.heading.2"),
            localization.getLocalizedString("text.format.heading.3"),
            localization.getLocalizedString("text.format.heading.4"),
            localization.getLocalizedString("text.format.heading.5"),
            localization.getLocalizedString("text.format.heading.6"),
            defaultFormat,
            localization.getLocalizedString("text.format.preformat"),
            localization.getLocalizedString("text.format.block.quote")
    )

    override fun getItemNames(): List<String> {
        return textFormats
    }

    override fun getDefaultItemName(): String {
        return defaultFormat
    }

    override fun getItemStyle(itemName: String): String {
        getIndexOfItem(itemName)?.let { index ->
            return getItemStyle(index)
        }

        return ""
    }

    private fun getItemStyle(index: Int): String {
        // see for example https://www.w3schools.com/tags/tag_hn.asp
        return when(index) {
            0 -> "-fx-font-size: 2em; -fx-font-weight: bold"
            1 -> "-fx-font-size: 1.5em; -fx-font-weight: bold"
            2 -> "-fx-font-size: 1.17em; -fx-font-weight: bold"
            3 -> "-fx-font-size: 1em; -fx-font-weight: bold"
            4 -> "-fx-font-size: 0.83em; -fx-font-weight: bold"
            5 -> "-fx-font-size: 0.67em; -fx-font-weight: bold"
            7 -> "-fx-font-family: monospace;" // https://www.w3schools.com/tags/tag_pre.asp
            8 -> "-fx-padding: 0 0 0 40px;" // https://www.w3schools.com/tags/tag_blockquote.asp
            else -> "" // Text body, the default
        }
    }

    override fun valueSelected(executor: JavaScriptExecutorBase, position: Int, itemName: String) {
        when(position) {
            0 -> executor.setHeading(1)
            1 -> executor.setHeading(2)
            2 -> executor.setHeading(3)
            3 -> executor.setHeading(4)
            4 -> executor.setHeading(5)
            5 -> executor.setHeading(6)
            6 -> executor.setFormattingToParagraph()
            7 -> executor.setPreformat()
            8 -> executor.setBlockQuote()
        }
    }

}