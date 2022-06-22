package Views

import Fragments.AddStepFragment
import Fragments.SettingFragment
import Fragments.SettingFragment.Companion.loadSettings
import Steps.AllSteps
import Steps.AllSteps.*
import Steps.AllSteps.Companion.beginTest
import Steps.AllSteps.Companion.getLast
import Steps.AllSteps.Companion.mainTable
import Steps.AllSteps.Companion.removeAllSteps
import Steps.AllSteps.Companion.saveJsonTest
import Steps.AllSteps.Companion.typesCycle
import Steps.AllSteps.TypeAction.*

import Steps.Step

import Styles.MainStyle.Companion.buttonBox
import javafx.event.EventTarget

import javafx.geometry.Pos

import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.stage.FileChooser

import tornadofx.*
import javax.swing.text.TableView

class MainView : View("My View") {







    init {
        loadSettings()
    }

    override val root = vbox(){

        borderpane(){
            top{
                hbox(){
                    menubar {
                        menu("ACTIONS"){
                            item("Open test"){
                                action {

                                }
                            }
                            item("Save test"){
                                action {
                                    val fileChooser = FileChooser().showSaveDialog(primaryStage)
                                    if(fileChooser != null)
                                        saveJsonTest(fileChooser.absolutePath)
                                }
                            }
                            style{
                                minWidth = 100.px
                                minHeight = 40.px
                            }
                        }
                    }
                    style{
                        borderColor += box(Color.BLACK)
                        hgrow = Priority.SOMETIMES
                        alignment = Pos.CENTER
                    }
                }
            }
            left{
                vbox {
                    hbox{
                        button("Settings"){
                            action {
                                openInternalWindow<SettingFragment>()
                            }
                        }
                        addClass(buttonBox)
                    }
                    style {
                        minWidth = 200.px
                        borderColor += box(Color.BLACK)
                        alignment = Pos.BOTTOM_CENTER
                    }
                }
                vgrow = Priority.SOMETIMES
            }
            center {

                /** main central block **/

                vbox{
                    style {
                        borderColor += box(Color.RED)
                        backgroundColor += Color.DARKCYAN
                    }

                    /** table block **/

                    vbox {
                        vbox{
                            mainTable = tableview<Step>{
                                readonlyColumn("#", Step::number).minWidth(15.0)
                                readonlyColumn("Spec Data", Step::specificData){
                                    minWidth(50.0)
                                }
                                readonlyColumn("Action", Step::actionName){
                                    minWidth(50.0)
                                }
                                readonlyColumn("Action Type", Step::actionType) {
                                    minWidth(50.0)
                                }
                                onDoubleClick {
                                    if(typesCycle.contains(selectedItem!!.actionType)){
                                        selectedItem!!.cycle!!.openCycleStepFragment()
                                    }
                                }


//                                subscribe<Companion.AddStepEvent> { addStepEvent -> items.add(getLast()) }
//                                subscribe<Companion.DeleteAllStepsEvent> { deleteAllStepsEvent -> items.clear() }
                                style{
                                    vgrow = Priority.SOMETIMES;
                                }
                            }

                            style{backgroundColor += Color.DARKGRAY; vgrow = Priority.ALWAYS; borderColor += box(Color.RED)}
                        }
                        style {alignment = Pos.TOP_CENTER; vgrow = Priority.SOMETIMES; padding = box(5.px, 5.px, 0.px, 5.px);}
                    }



                    hbox {

                        /** buttons block **/

                        hbox{
                            button("Add"){
                                action{
                                    openInternalWindow<AddStepFragment>(movable = false)
                                }
                            }
                            style{
                                hgrow = Priority.SOMETIMES
                                alignment = Pos.CENTER_LEFT
                            }
                            addClass(buttonBox)
                        }
                        hbox{
                            button("Clear"){
                                action{
                                    removeAllSteps();
                                    mainTable.items.clear()
                                    //fire(Companion.DeleteAllStepsEvent)
                                }
                            }
                            style{
                                hgrow = Priority.SOMETIMES
                                alignment = Pos.CENTER_RIGHT
                            }
                            addClass(buttonBox)
                        }
                    }
                }
                vgrow = Priority.SOMETIMES
            }
            right {
                vbox {
                    hbox {
                        button("Begin"){
                            action {
                                runAsync {
                                    beginTest()
                                }
                            }
                        }
                        addClass(buttonBox)
                    }
                    style {
                        minWidth = 200.px
                        borderColor += box(Color.BLACK)
                        alignment = Pos.BOTTOM_CENTER
                    }
                }
                vgrow = Priority.SOMETIMES
            }
        }
    }
}
