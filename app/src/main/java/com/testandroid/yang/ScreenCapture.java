package com.testandroid.yang;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yangjiajia on 2018/5/2.
 */
public class ScreenCapture {
    private Context mContext;
//    private OnScreenCaptureListener mOnScreenCaptureListener;
    public MediaProjectionManager mMediaProjectManager;  // mMediaProjectionManager
    private MediaProjection mMediaProjection;  // mMediaProjection
    private VirtualDisplay mVirtualDisplay;  // mVirtualDisplay
//    public static ScreenCaptureAssistantActivity mScreenCaptureActivity;
//    private BroadcastReceiver mScreenBroadcastReceiver = new ScreenBroadcastReceiver(this);

    private int mWidth = 1280;  // mWidth
    private int mHeight = 720;  // mHeight

    public final static int SCREEN_STATE_IDLE = 0;
    public final static int SCREEN_STATE_INITIALIZING = 1;
    public final static int SCREEN_STATE_INITIALIZED = 2;
    public final static int SCREEN_STATE_STOPPING = 3;
    public final static int SCREEN_STATE_CAPTURING = 4;

    public final static int SCREEN_ERROR_SYSTEM_UNSUPPORTED = -1;
    public final static int SCREEN_ERROR_PERMISSION_DENIED = -2;

    public final static int SCREEN_RECORD_STARTED = 4;
    public final static int SCREEN_RECORD_FAILED = 5;

    private final static int MSG_SCREEN_START_SCREEN_ACTIVITY = 1;
    private final static int MSG_SCREEN_INIT_PROJECTION = 2;
    private final static int MSG_SCREEN_START = 3;
    private final static int MSG_SCREEN_RELEASE = 4;
    private final static int MSG_SCREEN_QUIT = 5;

    private final static int RELEASE_SCREEN_THREAD = 1;

    private AtomicInteger mState;

    private GLRender mGLRender;
    private int mTextureId;
    private Surface mSurface;
    private SurfaceTexture mSurfaceTexture;
    private boolean mTexInited = false;
//    private ImgTexFormat mImgTexFormat;

    private Handler mMainHandler;
    private HandlerThread mScreenSetupThread;
    private Handler mScreenSetupHandler;

    private int mScreenDensity;

    // fill extra frame
    private Runnable mFillFrameRunnable;

    private final static boolean TRACE = true;
    // Performance trace
    private long mLastTraceTime;
    private long mFrameDrawed;


    public ScreenCapture(Context context, GLRender render, int density) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            throw new RuntimeException("Need API level " + Build.VERSION_CODES.LOLLIPOP);
        }

        if (context == null || render == null) {
            throw new IllegalArgumentException("the context or render must be not null");
        }

        mContext = context;
        mGLRender = render;
        mScreenDensity = density;

//        mGLRender.addListener(mGLRenderListener);
//        mImgTexSrcConnector = new SrcConnector<>();
//        mMainHandler = new MainHandler(this);
        mState = new AtomicInteger(SCREEN_STATE_IDLE);
        mFillFrameRunnable = new Runnable() {
            @Override
            public void run() {
                if (mState.get() == SCREEN_STATE_CAPTURING) {
//                    mGLRender.requestRender();
                    mMainHandler.postDelayed(mFillFrameRunnable, 100);
                }
            }
        };

//        initScreenSetupThread();
    }
}
