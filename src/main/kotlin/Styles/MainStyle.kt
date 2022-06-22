package Styles

import javafx.geometry.Pos
import tornadofx.Stylesheet
import tornadofx.*
import java.awt.Color

class MainStyle: Stylesheet() {
    companion object {
        val buttonBox by cssclass()
        val menuSaveLoad by cssclass()
    }

    init {
        menuSaveLoad{
            minWidth = 100.px
            minHeight = 40.px
        }
        buttonBox{
            padding = box(5.px)
            alignment = Pos.CENTER
        }
        button{
            minWidth = 100.px
            minHeight = 40.px
        }
        radioButton{
            minHeight = 30.px
        }
    }
}