package eb.training.sevenminutesworkout

import android.view.ViewParent

data class ExerciseModel(
    private var id: Int,
    private var name: String,
    private var image: Int,
    private var isComplete: Boolean,
    private var isSelected: Boolean
){
    fun getId():Int {
        return id
    }

    fun setId(i : Int){
        this.id =i
    }

    fun getName():String{
        return name
    }

    fun setName(i : String){
        this.name = i

    }
    fun getImage():Int{
        return image
    }

    fun setImage(i : Int){
        this.image = i
            }

    fun getIsComplete(): Boolean{
        return isComplete
    }

    fun setComplete(i : Boolean){
        this.isComplete = i
    }

    fun getIsSelected(): Boolean{
        return isSelected
    }

    fun setSelect(i : Boolean){
        this.isSelected = i
    }
}
