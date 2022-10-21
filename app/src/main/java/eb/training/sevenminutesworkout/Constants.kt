package eb.training.sevenminutesworkout

object Constants {

    fun getConstants(): ArrayList<ExerciseModel> {
        val exeContants = ArrayList<ExerciseModel>()
        var exeItem: ExerciseModel =
            ExerciseModel(
                0,
                "Push Up and Rotation",
                R.drawable.ic_push_up_and_rotation,
                false,
                false
            )
        exeContants.add(exeItem)

        exeItem = ExerciseModel(1, "Jumping Jacks", R.drawable.ic_jumping_jacks, false, false)
        exeContants.add(exeItem)

        exeItem = ExerciseModel(2, "Abdominal Crunch", R.drawable.ic_abdominal_crunch, false, false)
        exeContants.add(exeItem)

        exeItem = ExerciseModel(
            3,
            "High Knees Running",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        exeContants.add(exeItem)

        exeItem = ExerciseModel(4, "Lunge", R.drawable.ic_lunge, false, false)
        exeContants.add(exeItem)

        exeItem = ExerciseModel(5, "Plank", R.drawable.ic_plank, false, false)
        exeContants.add(exeItem)

        exeItem = ExerciseModel(6, "Push Up", R.drawable.ic_push_up, false, false)
        exeContants.add(exeItem)

        exeItem = ExerciseModel(7, "Squat", R.drawable.ic_squat, false, false)
        exeContants.add(exeItem)

        exeItem =
            ExerciseModel(8, "Step Up onto Chair", R.drawable.ic_step_up_onto_chair, false, false)
        exeContants.add(exeItem)

        exeItem = ExerciseModel(
            9,
            "Triceps Dip on Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        exeContants.add(exeItem)

        return exeContants
    }

}