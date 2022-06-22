package Steps

import Fragments.AddCycleStepFragment
import Fragments.CycleFragment
import javafx.stage.StageStyle


open class AllCycleSteps() : AllSteps() {
    var tableAllCycleSteps = javafx.scene.control.TableView<Step>()
    var allCycleSteps = ArrayList<Step>()
    var cycleIterator = String()
    val cycleFragment = CycleFragment(this)
    val addCycleStepFragment = AddCycleStepFragment(this)


    fun removeAllSteps(){
        allCycleSteps.clear()
    }

    fun openCycleStepFragment(){
        cycleFragment.openModal(StageStyle.UTILITY)
    }

    fun openAddCycleStepFragment(){
        addCycleStepFragment.openModal(StageStyle.UTILITY)
    }
}