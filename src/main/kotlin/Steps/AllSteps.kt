package Steps

import Fragments.SettingFragment.Companion.getCycleNumbers
import Fragments.SettingFragment.Companion.getPathToDriver
import Fragments.SettingFragment.Companion.getStandTimeout
import Fragments.SettingFragment.Companion.timeout
import com.sun.javafx.geom.Edge
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject
import netscape.javascript.JSObject
import org.openqa.selenium.*
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import tornadofx.Component
import tornadofx.FXEvent
import java.io.File
import javax.json.JsonObject


open class AllSteps {

    companion object {
        var mainTable = javafx.scene.control.TableView<Step>()

        val typesCycle = arrayListOf<TypeAction>(TypeAction.CYCLE_WAY_TO_FILES, TypeAction.CYCLE_TXT_LIST)

        object AddStepEvent : FXEvent()
        object DeleteAllStepsEvent : FXEvent()


        private var allSteps = ArrayList<Step>()
        lateinit var driver : WebDriver
        lateinit var el : WebElement

        fun addStepToArr(step : Step) {
            allSteps.add(step)
        }

        fun getLast() : Step{
            return allSteps.last()
        }

        fun removeAllSteps() {
            allSteps.clear()
        }

        fun getStepsSize() : Int {
            return allSteps.size
        }



        fun beginTest() {
            System.setProperty("webdriver.edge.driver", getPathToDriver())
            driver = EdgeDriver()
            try {
                driver.manage().window().maximize()
                for (i in 0..getCycleNumbers()){
                    allSteps.forEach {
                        timeout(getStandTimeout())
                        it.actionType.makeAction(it)
                    }
                    timeout(getStandTimeout())
                }
            } catch (ex : Exception) {
                println(ex)
            } finally {
                driver.quit()
            }
        }



        fun saveJsonTest(path: String){
            var jsonObjectStep = Json.encodeToJsonElement(allSteps)
            println(jsonObjectStep)
        }

        fun loadJsonTest(){

        }
    }


    interface Action {
        fun makeAction(step: Step){

        }
    }


    enum class TypeAction : Action {
        NONE {

        },
        CLICK {
            override fun makeAction(step: Step) {
                el = driver.findElement(By.ByXPath(step.specificData))
                el.click()
            }
        },
        FOLLOW_LINK {
            override fun makeAction(step: Step) {
                driver.get(step.specificData)
            }
        },
        INPUT_DATA {
            override fun makeAction(step: Step) {
                el = driver.findElement(By.ByXPath(step.specificData))
                el.sendKeys(step.dataEnter)
            }
        },
        TIMEOUT {
            override fun makeAction(step: Step) {
                timeout(step.specificData.toInt())
            }
        },
        LOAD_DATA {
            override fun makeAction(step: Step) {

            }
        },
        DOWNLOAD_DATA{
            override fun makeAction(step: Step) {

            }
        },
        CYCLE_WAY_TO_FILES {
            override fun makeAction(step: Step) {

            }
        },
        CYCLE_TXT_LIST {
            override fun makeAction(step: Step) {

            }
        }
    }





}