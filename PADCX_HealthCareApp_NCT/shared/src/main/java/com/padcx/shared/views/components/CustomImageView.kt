package com.padcx.shared.views.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.withStyledAttributes
import com.padcx.shared.R

class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet,defStyleAttr:Int=0
): AppCompatImageView(context, attrs, defStyleAttr){
    private var cornerRadius = 0f
    private val path = Path()
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    init {
        context.withStyledAttributes(attrs, R.styleable.CustomImageView){
            cornerRadius = getDimension(R.styleable.CustomImageView_cornerRadius,0f)
        }
    }

    override fun onDraw(canvas: Canvas?) {

        val stroke = RectF(5f,5f,width.toFloat()-5,height.toFloat()-5)
        paint.color = Color.rgb(248,248,255)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
        canvas?.drawOval(stroke,paint)

        val rectangle = RectF(10f,10f,width.toFloat()-10,height.toFloat()-10)
        path.addRoundRect(rectangle,cornerRadius,cornerRadius,Path.Direction.CCW)
        canvas?.clipPath(path)
        super.onDraw(canvas)
    }
}