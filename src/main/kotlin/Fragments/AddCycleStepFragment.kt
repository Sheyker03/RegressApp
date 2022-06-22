package Fragments

import Steps.AllCycleSteps
import Steps.AllSteps
import Steps.AllSteps.Companion.typesCycle
import Steps.Step
import Styles.MainStyle
import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import javafx.geometry.Pos
import javafx.scene.control.ToggleGroup
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.stage.DirectoryChooser
import javafx.stage.FileChooser
import tornadofx.*

class AddCycleStepFragment(cycleSteps: AllCycleSteps) : Fragment() {
    var inputURL = SimpleStringProperty()
    var inputFieldClick = SimpleStringProperty()
    var inputDataEnter = SimpleStringProperty()
    var inputTimeout = SimpleStringProperty()



    var inputFieldLoad = SimpleStringProperty()
    var inputFieldDownloadClick = SimpleStringProperty()
    var inputDataPath = SimpleStringProperty()


    var inputFileWayCycleIter = SimpleStringProperty()
    var inputListTxtIter = SimpleStringProperty()




    private val toggleMainButtons = ToggleGroup()
    private val toggleAddableButtons = ToggleGroup()
    private val toggleCycleButtons = ToggleGroup()


    val variableStepVisible = FXCollections.observableArrayList<SimpleStringProperty>()

//    lateinit var tempType : TypeAction // заглушка :" cry/// todo незнаю как подругому :=(
//
//    enum class TypeData(inputType : SimpleStringProperty) {
//        CLICK(inputFieldClick),
//        FOLLOW_LINK(inputURL),
//        INPUT_DATA(inputDataEnter),
//        TIMEOUT(inputTimeout),
//        LOAD_DATA(inputFieldLoad),
//        DOWNLOAD_DATA(inputDataPath);
//        //CYCLE();
//    }

    override val root = vbox {
        setPrefSize(500.0, 500.0)

        /** Main rule - first field is main for Step. Check class Step - specificData**/
        tabpane {





            tab("Usual"){
                vbox {
                    vbox {
                        fieldset {
                            radiobutton("Follow the link", toggleMainButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputURL)
                                }
                            }
                            radiobutton("Click", toggleMainButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputFieldClick)
                                }
                            }
                            radiobutton("Input data for enter", toggleMainButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputDataEnter, inputFieldClick)
                                }
                            }
                            radiobutton("Timeout", toggleMainButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputTimeout)
                                }
                            }
                        }
                        style{
                            padding = box(8.px, 0.px, 0.px, 0.px)
                        }
                    }
                    vbox() {
                        fieldset() {
                            vbox{
                                children.bind(variableStepVisible){
                                    vbox {
                                        when(it){
                                            inputURL -> {
                                                label("Follow the link")
                                                textfield(it) {

                                                }
                                            }
                                            inputFieldClick -> {
                                                label("Field click")
                                                textfield(it) {

                                                }
                                            }
                                            inputDataEnter -> {
                                                label("Input field")
                                                textarea(it) {

                                                }
                                            }
                                            inputTimeout -> {
                                                label("Timeout")
                                                textfield(it) {

                                                }
                                            }
                                        }
                                        style{minHeight = 40.px; alignment = Pos.CENTER}
                                    }
                                }
                            }
                            style {
                                alignment = Pos.TOP_CENTER
                                padding = box(5.px)
                                borderColor += box(Color.BLACK)
                                vgrow = Priority.SOMETIMES
                            }
                        }
                        style{
                            borderColor += box(Color.BLACK)
                            vgrow = Priority.SOMETIMES
                        }
                    }
                }
                style{
                    hgrow = Priority.SOMETIMES
                    vgrow = Priority.SOMETIMES
                }
            }






            tab("Addable"){
                vbox {
                    vbox {
                        fieldset {
                            radiobutton("Load Data", toggleAddableButtons) {

                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputDataPath, inputFieldLoad)
                                }
                            }
                            radiobutton("Download Data", toggleAddableButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputDataPath, inputFieldClick)
                                }
                            }
                        }
                        style{
                            padding = box(8.px, 0.px, 0.px, 0.px)
                        }
                    }
                    vbox() {
                        fieldset() {
                            vbox{
                                children.bind(variableStepVisible){
                                    vbox {
                                        when(it){
                                            inputFieldDownloadClick -> {
                                                label("Click for download")
                                                textfield(it) {

                                                }
                                            }
                                            inputFieldLoad -> {
                                                label("Input field")
                                                textfield(it) {

                                                }
                                            }
                                            inputDataPath -> {
                                                label("Path to folder")
                                                textfield(it) {

                                                }
                                            }
                                        }
                                        style{minHeight = 40.px; alignment = Pos.CENTER}
                                    }
                                }
                            }
                            style {
                                alignment = Pos.TOP_CENTER
                                padding = box(5.px)
                                borderColor += box(Color.BLACK)
                                vgrow = Priority.SOMETIMES
                            }
                        }
                        style{
                            borderColor += box(Color.BLACK)
                            vgrow = Priority.SOMETIMES
                        }
                    }
                }
                style{
                    hgrow = Priority.SOMETIMES
                    vgrow = Priority.SOMETIMES
                }
            }






            tab("Cycle"){
                vbox {
                    vbox {
                        fieldset {
                            radiobutton("Cycle Files", toggleCycleButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputFileWayCycleIter)
                                }
                            }
                            radiobutton("Cycle List", toggleCycleButtons) {
                                setOnMousePressed {
                                    variableStepVisible.clear()
                                    variableStepVisible.addAll(inputListTxtIter)
                                }
                            }
                        }
                        style{
                            padding = box(8.px, 0.px, 0.px, 0.px)
                        }
                    }
                    vbox() {
                        fieldset() {
                            vbox{
                                children.bind(variableStepVisible){
                                    vbox {
                                        when(it){
                                            inputFileWayCycleIter -> {
                                                label("File way iterator")
                                                textfield(it) {
                                                    setOnMousePressed {
                                                        val dirChooser = DirectoryChooser().showDialog(primaryStage)
                                                        if(dirChooser != null){
                                                            inputFileWayCycleIter.value = dirChooser.absolutePath
                                                        }
                                                    }
                                                }
                                            }
                                            inputListTxtIter -> {
                                                label("Choose your list")
                                                textfield(it) {
                                                    setOnMousePressed {
                                                        val fileChooser = FileChooser().showOpenDialog(primaryStage)
                                                        if(fileChooser != null){
                                                            inputListTxtIter.value = fileChooser.absolutePath
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        style{minHeight = 40.px; alignment = Pos.CENTER}
                                    }
                                }
                            }
                            style {
                                alignment = Pos.TOP_CENTER
                                padding = box(5.px)
                                borderColor += box(Color.BLACK)
                                vgrow = Priority.SOMETIMES
                            }
                        }
                        style{
                            borderColor += box(Color.BLACK)
                            vgrow = Priority.SOMETIMES
                        }
                    }
                }
                style{
                    hgrow = Priority.SOMETIMES
                    vgrow = Priority.SOMETIMES
                }
            }
        }



        hbox {
            button("Save") {
                action {
                    val tempStep = Step(cycleSteps.allCycleSteps.size + 1, variableStepVisible[0].value, actionType = getType())

                    if(typesCycle.contains(getType())){
                        tempStep.cycle = AllCycleSteps()
                    }

                    if(variableStepVisible.size > 1) {
                        variableStepVisible.forEach {
                            when(it){
                                inputDataEnter -> tempStep.dataEnter = it.value
                            }
                        }
                    }
                    cycleSteps.tableAllCycleSteps.items.add(tempStep)
                    cycleSteps.allCycleSteps.add(tempStep)
                }
            }
            addClass(MainStyle.buttonBox)
        }
    }


    fun getType() : AllSteps.TypeAction { //TODO конченная заглушка, понять как правильно
        var localVariable : AllSteps.TypeAction = AllSteps.TypeAction.NONE
        variableStepVisible.forEach {
            when(variableStepVisible[0]) {
                inputURL -> localVariable = AllSteps.TypeAction.FOLLOW_LINK
                inputFieldClick -> localVariable = AllSteps.TypeAction.CLICK
                inputDataEnter -> localVariable = AllSteps.TypeAction.INPUT_DATA
                inputTimeout -> localVariable = AllSteps.TypeAction.TIMEOUT

                inputFieldLoad -> localVariable = AllSteps.TypeAction.LOAD_DATA
                inputFieldDownloadClick -> localVariable = AllSteps.TypeAction.DOWNLOAD_DATA

                inputFileWayCycleIter -> localVariable = AllSteps.TypeAction.CYCLE_WAY_TO_FILES
                inputListTxtIter -> localVariable = AllSteps.TypeAction.CYCLE_TXT_LIST
            }
        }
        return localVariable
    }


}