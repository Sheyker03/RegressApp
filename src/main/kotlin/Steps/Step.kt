package Steps



open class Step(
    var number : Int = 0,
    var specificData : String,
    var actionName : String = "",
    var actionType : TypeAction = TypeAction.NONE,
    var dataEnter : String? = null,
    var pathForDownload : String? = null,
    var pathForLoad : String? = null,
    var cycle : AllCycleSteps? = null
) : AllSteps()


