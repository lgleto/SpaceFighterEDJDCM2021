package ipca.game.example.spacefighter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.annotation.RequiresApi

//
// Created by lourencogomes on 12/2/20.
//
class GameView : SurfaceView ,Runnable {

    var playing = false
    var gameThread : Thread? = null

    var surfaceHolder : SurfaceHolder? = null
    var canvas : Canvas? = null

    var paint : Paint = Paint()

    lateinit var player : Player
    var stars = arrayListOf<Star>()
    var enemys = arrayListOf<Enemy>()
    lateinit var boom : Boom

    var score  = 0

    private fun init(context: Context?, width:Int, height: Int){
        player = Player(context!!, width, height)
        boom = Boom(context!!, width, height)
        surfaceHolder = holder

        for (i in 1..100) {
            stars.add(Star(context, width, height))
        }

        for (i in 1..3) {
            enemys.add(Enemy(context, width, height))
        }
    }

    constructor(context: Context?, width:Int, height: Int) : super(context){
        init(context, width, height)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context,0,0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context,0,0)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        init(context,0,0)
    }

    override fun run() {
        while (playing){
            update()
            draw()
            control()
        }
    }

    private fun update(){
        player.update()

        boom.x = -300
        boom.y = -300

        for(s in stars){
            s.update(player.speed)
        }
        for(e in enemys){
            e.update(player.speed)
            if(Rect.intersects(player.detectCollision, e.detectCollision)){
                boom.x = e.x
                boom.y = e.y

                e.x = -300
                score += 10
            }
        }


    }

    private fun draw(){
        surfaceHolder?.let {
            if (it.surface.isValid){
                canvas = surfaceHolder?.lockCanvas()
                canvas?.drawColor(Color.BLACK)

                paint.color = Color.WHITE
                for(s in stars){
                    paint.strokeWidth = s.getStarWidth()
                    canvas?.drawPoint(s.x.toFloat(), s.y.toFloat(), paint)
                }

                for(e in enemys){
                    canvas?.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
                }
                canvas?.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)
                canvas?.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)
                paint.color = Color.GREEN
                paint.textSize = 60f
                canvas?.drawText("Score :${score}", 0f, 80f, paint)
                surfaceHolder?.unlockCanvasAndPost(canvas)
            }
        }
    }

    private fun control(){
        Thread.sleep(17L)
    }

    fun pause(){
        playing = false
        gameThread?.join()
    }

    fun resume(){
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action){
            MotionEvent.ACTION_UP ->{
                player.boosting = false
            }
            MotionEvent.ACTION_DOWN ->{
                player.boosting = true
            }
        }
        return true
    }

}