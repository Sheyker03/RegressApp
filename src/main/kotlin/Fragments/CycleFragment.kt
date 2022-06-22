package Fragments

import Steps.AllCycleSteps
import Steps.AllSteps
import Steps.Step
import Styles.MainStyle
import javafx.geometry.Pos
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import tornadofx.*


class CycleFragment(cycleSteps: AllCycleSteps) : Fragment() {
    override val root = vbox {
        setPrefSize(300.0, 300.0)

            /** table block **/
        vbox {
            vbox{
                cycleSteps.tableAllCycleSteps = tableview<Step>{
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
                        if(AllSteps.typesCycle.contains(selectedItem!!.actionType)){
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
                        cycleSteps.openAddCycleStepFragment()
                    }
                }
                style{
                    hgrow = Priority.SOMETIMES
                    alignment = Pos.CENTER_LEFT
                }
                addClass(MainStyle.buttonBox)
            }
            hbox{
                button("Clear"){
                    action{
                        cycleSteps.removeAllSteps();
                        cycleSteps.tableAllCycleSteps.items.clear()
                    }
                }
                style{
                    hgrow = Priority.SOMETIMES
                    alignment = Pos.CENTER_RIGHT
                }
                addClass(MainStyle.buttonBox)
            }

        }
    }
}