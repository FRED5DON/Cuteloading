# Cuteloading

![](loading.png "")  

###CirclePlayBar 为仿IOS播放按钮
```
<com.freddon.android.app.cuteloading.CirclePlayBar
            android:layout_width="wrap_content"
            android:layout_height="48dp"
            android:layout_marginRight="10dp"
            android:clickable="true"
            app:cpb_background_progressbar_color="#F45B4F"
            app:cpb_background_progressbar_width="2dp"
            app:cpb_progress="52"
            app:cpb_progress_loading="66"
            app:cpb_progressbar_color="#F45B4D"
            app:cpb_progressbar_width="4dp"
            app:state="loading" />
```

###CircleLoading 为自定义Loading
可用vector替换
```
<com.freddon.android.app.cuteloading.CircleLoading
            android:layout_width="wrap_content"
            android:layout_height="48dp"
            android:layout_marginRight="10dp"
            android:clickable="true"
            app:cl_ball_color="#F45B4D"
            app:cl_ball_radius="4dp"
            app:cl_line_color="#F45B4F"
            app:cl_line_width="2dp"
            app:cl_progress="100" />
```

####Vector ( loading )
ProgressBar
```
<ProgressBar
            style="?android:attr/progressBarStyle"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:indeterminate="true"
            android:indeterminateDrawable="@anim/animate_v_loading"
            android:orientation="vertical" />
```

```
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="48dp"
    android:height="48dp"
    android:viewportHeight="120"
    android:viewportWidth="120">
    <path
        android:pathData="M 60,60 m -44,0 a 44,44 0 1,0 88,0 a 44,44 0 1,0 -88,0"
        android:strokeColor="#E44B4D"
        android:strokeWidth="8" />
    <group
        android:name="ball"
        android:rotation="0">
        <path
            android:fillColor="#E44B4D"
            android:pathData="M 60,6 A 6,6 0 1,1 60,28 A 6,6 0 1,1 60,6"
            android:strokeColor="#80E44B4D"
            android:strokeLineCap="round"
            android:strokeWidth="0.5" />
    </group>
</vector>
```

animate_v_loading.xml
```
<?xml version="1.0" encoding="utf-8"?>
<rotate xmlns:android="http://schemas.android.com/apk/res/android"
    android:drawable="@drawable/vector_loading"
    android:fromDegrees="0"
    android:pivotX="50%"
    android:pivotY="50%"
    android:toDegrees="360" />
```
