package com.example.calculate;





import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
     Button btn_1,btn_2,btn_clear,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,btn_0;
     Button btn_jian,btn_jia,btn_cheng,btn_chu,btn_equal,btn_point,btn_kuahao,btn_back;
     EditText etresult;
     String TAG="MaintActivity";

     @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        btn_1=(Button)findViewById(R.id.btn_1);
        btn_2=(Button)findViewById(R.id.btn_2);
        btn_3=(Button)findViewById(R.id.btn_3);
        btn_4=(Button)findViewById(R.id.btn_4);
        btn_5=(Button)findViewById(R.id.btn_5);
        btn_6=(Button)findViewById(R.id.btn_6);
        btn_7=(Button)findViewById(R.id.btn_7);
        btn_8=(Button)findViewById(R.id.btn_8);
        btn_9=(Button)findViewById(R.id.btn_9);
        btn_0=(Button)findViewById(R.id.btn_0);
        btn_jia=(Button)findViewById(R.id.btn_jia);
        btn_jian=(Button)findViewById(R.id.btn_jian);
        btn_cheng=(Button)findViewById(R.id.btn_cheng);
        btn_chu=(Button)findViewById(R.id.btn_chu);
        btn_point=(Button)findViewById(R.id.btn_point);
        btn_clear=(Button)findViewById(R.id.btn_clear);
        btn_equal=(Button)findViewById(R.id.btn_equal);
        etresult=(EditText)findViewById(R.id.etresult);
        btn_back=(Button)findViewById(R.id.btn_back);
        btn_kuahao=(Button)findViewById(R.id.btn_kuahao);
        ClickListener myClickListener=new ClickListener(); 
		btn_clear.setOnClickListener(myClickListener);
		btn_1.setOnClickListener(myClickListener);
		btn_2.setOnClickListener(myClickListener);
		btn_3.setOnClickListener(myClickListener);
		btn_4.setOnClickListener(myClickListener);
		btn_5.setOnClickListener(myClickListener);
		btn_6.setOnClickListener(myClickListener);
		btn_7.setOnClickListener(myClickListener);
		btn_8.setOnClickListener(myClickListener);
		btn_9.setOnClickListener(myClickListener);
		btn_0.setOnClickListener(myClickListener);
		btn_jian.setOnClickListener(myClickListener);
		btn_jia.setOnClickListener(myClickListener);
		btn_cheng.setOnClickListener(myClickListener);
		btn_chu.setOnClickListener(myClickListener);
		btn_point.setOnClickListener(myClickListener);	
		btn_equal.setOnClickListener(myClickListener);	
		btn_back.setOnClickListener(myClickListener);
		btn_kuahao.setOnClickListener(myClickListener);
		
		

		
     }
     class  ClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()){
			case R.id.btn_1:
				//Toast.makeText(MainActivity.this, "»¶Ó­Äú£¡", 
						//Toast.LENGTH_SHORT).show();
				gengerateBDS("1");
				break;
			case R.id.btn_2:
				gengerateBDS("2");
				//Toast.makeText(MainActivity.this, "Äú£¡", 
						//Toast.LENGTH_SHORT).show();
				break;
			case R.id.btn_clear:
				etresult.setText("");
				break;
			case R.id.btn_3:
				gengerateBDS("3");
				break;
			case R.id.btn_4:
				gengerateBDS("4");
				break;
			case R.id.btn_5:
				gengerateBDS("5");
				break;
			case R.id.btn_6:
				gengerateBDS("6");
				break;
			case R.id.btn_7:
				gengerateBDS("7");
				break;
			case R.id.btn_8:
				gengerateBDS("8");
				break;
			case R.id.btn_9:
				gengerateBDS("9");
				break;
			case R.id.btn_0:
				gengerateBDS("0");
				break;
			case R.id.btn_jia:
				gengerateBDS("+");
				break;
			case R.id.btn_jian:
				gengerateBDS("-");
				break;
			case R.id.btn_cheng:

				gengerateBDS("*");
				break;
			case R.id.btn_chu:
				gengerateBDS("/");
				break;
			case R.id.btn_point:
				gengerateBDS(".");
				break;
			case R.id.btn_kuahao:
				gengerateBDS("(");
				break;
			case R.id.btn_back:
				gengerateBDS(")");
				break;
		
			
			case R.id.btn_equal:
				String result=Caculator.caculateFromString(etresult.getText().toString());
				Toast.makeText(getApplication(),result,Toast.LENGTH_SHORT).show();
				break;
			
				default:break;
			}
		}
    	 
     }
     private void gengerateBDS(String str){
 		String oldStr="";
 		oldStr=etresult.getText().toString();
 		if(oldStr.equals("")||oldStr=="" || oldStr.length()==0 ){
 			etresult.setText(str);
 		}else if(oldStr.equals("0")){
 			etresult.setText(str);
 		}else{
 			etresult.setText(oldStr+str);
 		}
 	}
     private void calBDS(String str){
 		String oldStr="";// null
 		oldStr=etresult.getText().toString();
 			if(str.equals("/")||str.equals("*")){
 				etresult.setText("("+oldStr+")"+str);
 			}else{
 				etresult.setText(oldStr+str);
 			}
 		}		
 	
 	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
