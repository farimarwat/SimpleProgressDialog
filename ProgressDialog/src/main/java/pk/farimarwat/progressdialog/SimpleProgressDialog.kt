package pk.farimarwat.progressdialog

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

@SuppressLint("InflateParams")
class SimpleProgressDialog private constructor(builder: Builder){
    private var mDialog:AlertDialog? = null
    private var mMsg:String? = null
    private var mBackground:Drawable? = null
    private var mProgresssColor = 0
    private var mTextColor = 0
    private  var mTextViewMessage:TextView
    private var mProgressBar:ProgressBar
    private var mContainer:LinearLayout

    class Builder(var context: Context){
        private var mMsg:String? = null
        private var mBackground:Drawable? = null
        private var mProgresssColor = 0
        private var mTextColor = 0

        fun setMessage(msg:String) = apply { this.mMsg = msg }
        fun getMessage() = this.mMsg

        fun setBackgroundDrawable(background:Drawable?) = apply { this.mBackground = background }
        fun getBackgroundDrawable() = this.mBackground

        //Setting Progress Color
        fun setProgressColor(color:Int) = apply { this.mProgresssColor = color }
        fun getProgressColor() = this.mProgresssColor

        //Setting Text Color
        fun setTextColor(color:Int) = apply { this.mTextColor = color }
        fun getTextColor() = this.mTextColor

        fun build() = SimpleProgressDialog(this)
    }
    init {
        this.mMsg = builder.getMessage()
        this.mBackground = builder.getBackgroundDrawable()
        this.mProgresssColor = builder.getProgressColor()
        this.mTextColor = builder.getTextColor()


        val view = LayoutInflater.from(builder.context)
            .inflate(R.layout.item_dialog,null)

        mTextViewMessage = view.findViewById<TextView>(R.id.txt_progress)
        mMsg?.let {
                mTextViewMessage.text = it
        }
        if(mTextColor != 0){
            mTextViewMessage.setTextColor(mTextColor)
        }
        mContainer = view.findViewById<LinearLayout>(R.id.container)
        if(mBackground == null){
            mContainer.background = ContextCompat.getDrawable(builder.context,
                R.drawable.bg_container_progress)
        } else {
            mContainer.background = mBackground
        }

        mProgressBar = view.findViewById<ProgressBar>(R.id.progress)
        if(mProgresssColor != 0){
            DrawableCompat.setTint(mProgressBar.indeterminateDrawable,mProgresssColor)
        }


        val dialogbuilder = AlertDialog.Builder(builder.context)
        dialogbuilder.setView(view)
        dialogbuilder.setCancelable(false)
        mDialog = dialogbuilder.create()
        mDialog?.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))

    }
    fun show(){
        mDialog?.show()
    }
    fun hide(){
        mDialog?.hide()
    }
    fun setMessage(msg:String){
        mMsg = msg
        mTextViewMessage.text = mMsg
    }
    fun setProgressBarColor(color:Int){
        if(color != 0){
            DrawableCompat.setTint(mProgressBar.indeterminateDrawable,color)
        }
    }
    fun setBackgroundDrawable(background:Drawable?){
        background?.let {
            mContainer.background = it
        }
    }
}