package eb.training.sevenminutesworkout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import eb.training.sevenminutesworkout.databinding.ActivityExerciseBinding
import eb.training.sevenminutesworkout.databinding.DialogCustomBackBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var timer: CountDownTimer? = null
    private var restProgress = 10
    private lateinit var binding: ActivityExerciseBinding
    private lateinit var exeConstant: ArrayList<ExerciseModel>
    private var position = -1
    private var tts: TextToSpeech? = null
    private var mediaPlay: MediaPlayer? = null
    private var exerciseAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tts = TextToSpeech(this, this)
//add action bar
        setSupportActionBar(binding.toolbarExercise)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding.toolbarExercise.setNavigationOnClickListener {
            dialogCustom()
                    }

        exeConstant = Constants.getConstants()
        playMedia()
        setProgressbar()


    }


    override fun onBackPressed() {
        dialogCustom()
    }

    //play music media file
    private fun playMedia() {
        try {
            val fileVRI =
                Uri.parse("android.resource://eb.training.sevenminutesworkout/" + R.raw.press_start)
            mediaPlay = MediaPlayer.create(applicationContext, fileVRI)
            mediaPlay?.isLooping = false
            mediaPlay?.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setProgressbar() {
        if (timer != null) {
            timer?.cancel()
            restProgress = 10
            binding.progressBar.progress = restProgress
        }


        timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress -= 1
                binding.progressBar.progress = restProgress
                binding.tvMinutes.text = restProgress.toString()
                speak(restProgress.toString())
            }

            override fun onFinish() {
                binding.frameProgressbar.visibility = View.GONE
                binding.frameExe.visibility = View.VISIBLE
                binding.imageExe.visibility = View.VISIBLE
                changeExe()
                setExeProgressbar()
                setupExercisesStatusRecycleView()

            }

        }.start()
    }

    @SuppressLint("SetTextI18n")
    private fun setExeProgressbar() {

        if (timer != null) {
            timer?.cancel()
            restProgress = 30
            binding.progressBar.progress = restProgress
            binding.tvMinutes.text = restProgress.toString()
        }

        timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress -= 1
                binding.progressBarExe.progress = restProgress
                binding.tvMinutesExe.text = restProgress.toString()
                speak(restProgress.toString())
            }

            override fun onFinish() {
                if (position < exeConstant.size - 1) {
                    speak(exeConstant[position].getName())
//set recycle view button color
                    exeConstant[position].setSelect(false)
                    exeConstant[position].setComplete(true)
                    exerciseAdapter?.notifyDataSetChanged()

                    changeExe()
                    setExeProgressbar()
                } else {
                    val intent = Intent(this@ExerciseActivity, FinishActivity::class.java)
                    startActivity(intent)

                }
            }

        }.start()

    }

    private fun changeExe() {
        position++
        binding.tvTitle.text = exeConstant[position].getName()
        binding.imageExe.setImageResource(exeConstant[position].getImage())
       //set recycle view button color
        exeConstant[position].setSelect(true)
        exerciseAdapter?.notifyDataSetChanged()

    }

    override fun onInit(p0: Int) {
        if (p0 == TextToSpeech.SUCCESS) {
            tts?.setLanguage(Locale.CANADA)
        }
    }

    //speaker work
    private fun speak(speak: String) {
        tts?.speak(speak, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    //RecycleView adapter
    private fun setupExercisesStatusRecycleView() {
        binding.rvExerciseStatus.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        exerciseAdapter = ExerciseStatusAdapter(exeConstant)
        binding.rvExerciseStatus.adapter = exerciseAdapter
    }

    override fun onDestroy() {
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
            super.onDestroy()
        }
        if (mediaPlay != null) {
            mediaPlay?.stop()
        }
    }
//custom dialog
    private fun dialogCustom() {
        var dialogBinding:DialogCustomBackBinding? = null
        dialogBinding = DialogCustomBackBinding.inflate(layoutInflater)
        var finishDialog = Dialog(this)
        finishDialog.setContentView(dialogBinding.root)
        finishDialog.setCanceledOnTouchOutside(false)
        dialogBinding.buttonYes.setOnClickListener(){
            this@ExerciseActivity.finish()
            finishDialog.dismiss()
        }
        dialogBinding.buttonNo.setOnClickListener(){
            finishDialog.dismiss()
        }
        finishDialog.show()
    }
}