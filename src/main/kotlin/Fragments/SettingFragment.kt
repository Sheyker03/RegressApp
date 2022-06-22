package Fragments

import javafx.beans.property.SimpleStringProperty

import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class SettingFragment: Fragment() {

    companion object {
        private val inputPathToDriver = SimpleStringProperty()
        private val inputStandTimeout = SimpleStringProperty()
        private val inputCycleNumbers = SimpleStringProperty()
        private val allSettings = mapOf<String ,SimpleStringProperty>(
                "pathToDriver" to inputPathToDriver,
                "StandartTimeout" to inputStandTimeout,
                "CycleNumbers" to inputCycleNumbers
        )
//        val allSettings = FXCollections.observableArrayList(
//                "pathToDriver" to inputPathToDriver,
//                "StandartTimeout" to inputStandTimeout,
//                "CycleNumbers" to inputCycleNumbers
//        )

        fun loadSettings() {
            if(File("Settings.ini").exists()){
                File("Settings.ini").forEachLine {i ->
                    allSettings.forEach { j ->
                        if(i.contains(j.key))
                            j.value.value = i.replace(j.key, "").trimStart().trimEnd()
                    }
                }
            }
        }

        fun saveSettings() {
            File("Settings.ini").printWriter().use { out ->
                allSettings.forEach {
                    out.println("${it.key} ${it.value.value}")
                }
            }
        }


        fun getPathToDriver() : String {
            return inputPathToDriver.value
        }

        fun getStandTimeout() : Int {
            return inputStandTimeout.value.toInt()
        }

        fun getCycleNumbers() : Int {
            return inputCycleNumbers.value.toInt()
        }


        fun timeout(time : Int){
            Thread.sleep(1000 * time.toLong())
        }

//        fun loadSettings() {
//            if(File("Settings.txt").exists()){
//                File("Settings.txt").forEachLine {i ->
//                    allSettings.forEach { j ->
//                        if(i.contains(j.first))
//                            j.second.value = i.replace(j.first, "").trimStart().trimEnd()
//                    }
//                }
//            }
//        }
//
//        fun saveSettings() {
//            File("Settings.txt").printWriter().use { out ->
//                allSettings.forEach {
//                    out.println("${it.first} ${it.second.value}")
//                }
//            }
//        }
    }




    override val root = form {
        minWidth = 250.0
        minHeight = 120.0
        fieldset("Settings") {
            field() {
                label("Path to Driver")
                textfield(inputPathToDriver) {
                    setOnMousePressed {
                        var fileChooser = FileChooser().showOpenDialog(primaryStage)
                        if(fileChooser != null){
                            inputPathToDriver.value = fileChooser.absolutePath
                        }
                    }
                }
            }
            field() {
                label("Standart Timeout")
                textfield(inputStandTimeout){

                }
            }
            field {
                label("Cycle numbers")
                textfield(inputCycleNumbers) {

                }
            }
            button("Сохранить") {
                action {
                    saveSettings()
                }
            }
        }
    }
}