package learningAndroid.com;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FlashLightBCActivity extends Activity {
    /** Called when the activity is first created. */
	/**手电筒程序
	 * 当前拥有功能：
	 * 1. 打开闪光，关闭闪光，退出。
	 * 2. 能够后台工作，即不在当前打开程序时同时可以闪光。
	 * 需求：
	 * 1. 加入about
	 * 2. 检测有无闪光灯，如无可显示白屏来照明。
	 * 
	 */

	private View view;
	private Camera camera;
	private boolean isOpen=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	Button btnOn = (Button)findViewById(R.id.btnOn);
    	Button btnOff = (Button)findViewById(R.id.btnOff);
    	Button btnEx = (Button)findViewById(R.id.btnEx);
        btnOn.setOnClickListener(onListener);
        btnOff.setOnClickListener(offListener);
        btnEx.setOnClickListener(exListener);
        
        //Open camera only one time, or the app become un-responding.
        camera=Camera.open();
        

            
    }
    
    /**
     * 打开闪光灯
     * 
     */
    View.OnClickListener onListener = new View.OnClickListener() {
        public void onClick(View v){
            Parameters p = camera.getParameters(); 
            p.setFlashMode(Parameters.FLASH_MODE_TORCH);
            camera.setParameters(p);
            camera.startPreview();
    	}
    };
    
    /**
     * 关闭闪光灯
     */
    View.OnClickListener offListener = new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//flashLightOff();
	    	Parameters p = camera.getParameters();
	    	p.setFlashMode(Parameters.FLASH_MODE_OFF);
	    	camera.setParameters(p);
	    	camera.stopPreview();
		}
	};
	
	/**
	 * 退出程序
	 */
	View.OnClickListener exListener = new View.OnClickListener() {
		
		public void onClick(View v) {
			// TODO Auto-generated method stub
	    	Parameters p = camera.getParameters();
	    	p.setFlashMode(Parameters.FLASH_MODE_OFF);
	    	camera.setParameters(p);
			camera.stopPreview();
			camera.release();
			android.os.Process.killProcess(android.os.Process.myPid());
		}
	};
	
	/**
	 * 因BACK键后重新打开程序会导致停止响应，故重写onBackPressed程序。
	 */
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

}