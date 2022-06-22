import Styles.MainStyle
import Views.MainView
import javafx.stage.Stage
import tornadofx.*


class MainApp : App(MainView::class, MainStyle::class) {
    override fun start(stage: Stage) {
        with(stage) {
            minWidth = 800.0
            minHeight= 600.0
            super.start(this)
        }
    }
}
fun main(args: Array<String>){
    launch<MainApp>(args)
}