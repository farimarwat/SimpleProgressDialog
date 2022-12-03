package pk.farimarwat.simpleprogressbarexample

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import pk.farimarwat.progressdialog.SimpleProgressDialog
import pk.farimarwat.simpleprogressbarexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mContext:Context
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    lateinit var mDialog:SimpleProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mContext =  this
        mDialog = SimpleProgressDialog.Builder(mContext)
            .setMessage("Working... in progress")
            .build()

        binding.button.setOnClickListener {
            //Change properties dynamically after initialization
            mDialog.setMessage("Second message")
            mDialog.setProgressBarColor(Color.RED)
            mDialog.setBackgroundDrawable(
                ContextCompat.getDrawable(mContext,R.drawable.background)
            )
            mDialog.show()
        }
        binding.button2.setOnClickListener {
            mDialog.hide()
        }
    }
}