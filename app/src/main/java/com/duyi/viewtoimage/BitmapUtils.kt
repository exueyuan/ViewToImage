package com.duyi.viewtoimage

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Environment
import android.view.View
import java.io.File
import java.io.FileOutputStream

class BitmapUtils{
    companion object {
        //然后View和其内部的子View都具有了实际大小，也就是完成了布局，相当与添加到了界面上。接着就可以创建位图并在上面绘制了：
        fun layoutViewAtMost(v: View) {
            // 整个View的大小 参数是左上角 和右下角的坐标
            val measuredWidth = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.AT_MOST)
            val measuredHeight = View.MeasureSpec.makeMeasureSpec(10000, View.MeasureSpec.AT_MOST)
            /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
             * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
             */
            v.measure(measuredWidth, measuredHeight)
            v.layout(0, 0, v.measuredWidth, v.measuredHeight)
        }

        fun layoutViewExactly(v: View, width: Int, height: Int) {
            // 整个View的大小 参数是左上角 和右下角的坐标
            v.layout(0, 0, width, height)
            val measuredWidth = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY)
            val measuredHeight = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY)
            /** 当然，measure完后，并不会实际改变View的尺寸，需要调用View.layout方法去进行布局。
             * 按示例调用layout函数后，View的大小将会变成你想要设置成的大小。
             */
            v.measure(measuredWidth, measuredHeight)
            v.layout(0, 0, v.measuredWidth, v.measuredHeight)
        }

        fun loadBitmapFromView(v: View): Bitmap {
            val w = v.width
            val h = v.height
            val bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            val c = Canvas(bmp)

            c.drawColor(Color.WHITE)
            /** 如果不设置canvas画布为白色，则生成透明  */

            v.layout(0, 0, w, h)
            v.draw(c)

            return bmp
        }

        fun viewToBitmap(v: View):Bitmap {
            layoutViewAtMost(v)
            return BitmapUtils.loadBitmapFromView(v)
        }

        fun bitmapSaveToImage(cachebmp:Bitmap){
            //保存在本地 产品还没决定要不要保存在本地
            val fos: FileOutputStream
            try {
                // 判断手机设备是否有SD卡
                val isHasSDCard = Environment.getExternalStorageState() == android.os.Environment.MEDIA_MOUNTED
                if (isHasSDCard) {
                    // SD卡根目录
                    val sdRoot = Environment.getExternalStorageDirectory()
                    val file = File(sdRoot, "test.png")
                    fos = FileOutputStream(file)
                } else
                    throw Exception("创建文件失败!")
                //压缩图片 30 是压缩率，表示压缩70%; 如果不压缩是100，表示压缩率为0
                cachebmp.compress(Bitmap.CompressFormat.PNG, 90, fos)

                fos.flush()
                fos.close()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}