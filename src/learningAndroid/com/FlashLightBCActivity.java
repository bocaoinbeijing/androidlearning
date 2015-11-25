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
	/**�ֵ�Ͳ����
	 * ��ǰӵ�й��ܣ�
	 * 1. �����⣬�ر����⣬�˳���
	 * 2. �ܹ���̨�����������ڵ�ǰ�򿪳���ʱͬʱ�������⡣
	 * ����
	 * 1. ����about
	 * 2. �����������ƣ����޿���ʾ������������
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
     * �������
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
     * �ر������
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
	 * �˳�����
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
	 * ��BACK�������´򿪳���ᵼ��ֹͣ��Ӧ������дonBackPressed����
	 */
	@Override
	public void onBackPressed(){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

}