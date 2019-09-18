package com.duyi.viewtoimage

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class MainActivity : Activity() {
    private lateinit var aaa: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        aaa = findViewById(R.id.aaa)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val view = View.inflate(this,R.layout.view_photo,null)
            val tv_name = view.findViewById<TextView>(R.id.tv_name)
            tv_name.text = "杜毅杜毅上课的房间看电视肌肤卡升级地方看电视啊开发的萨肌肤看但是"
            val bitmap = BitmapUtils.viewToBitmap(view)
            aaa.setImageBitmap(bitmap)
        }

/*        val metric = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metric)
        val width = 200     // 屏幕宽度（像素）
        val height = 200   // 屏幕高度（像素）
        val view = LayoutInflater.from(this).inflate(R.layout.view_photo, null, false)
        BitmapUtils.layoutViewAtMost(view, width, height)*/
    }
}
