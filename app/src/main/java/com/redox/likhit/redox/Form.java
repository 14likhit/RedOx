package com.redox.likhit.redox;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class Form extends AppCompatActivity {

    private static final int QRcodeWidth=500;

    private EditText inputString;
    private RadioGroup userAgeInput,genderInput,trainingTypeInput;
    private String userAge="",gender="",training="",userDetail="";
    private RadioButton cardio,strength,stamina,muscle,swimming,running,football,rugby,dance;
    private int a=0,b=0,c=0,d=0,p=0,q=0,r=0,s=0,vi=0,w=0,x=0,y=0,z=0;
    private Button generateQR;
    private ImageView qr;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        inputString=(EditText)findViewById(R.id.nameInput);

        userAgeInput=(RadioGroup)findViewById(R.id.userAgeInput);
//        userAgeInput.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                int ageId=userAgeInput.getCheckedRadioButtonId();
//                RadioButton ageButton=(RadioButton)findViewById(ageId);
//                userAge=ageButton.getText().toString();
//            }
//        });

        genderInput=(RadioGroup)findViewById(R.id.genderInput);
//        genderInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int genderId=genderInput.getCheckedRadioButtonId();
//                RadioButton genderButton=(RadioButton)findViewById(genderId);
//                gender=genderButton.getText().toString();
//            }
//        });

        trainingTypeInput=(RadioGroup)findViewById(R.id.traniningTypeInput);
//        trainingTypeInput.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int trainingId=trainingTypeInput.getCheckedRadioButtonId();
//                RadioButton trainingButton=(RadioButton)findViewById(trainingId);
//                training=trainingButton.getText().toString();
//            }
//        });

        cardio=(RadioButton)findViewById(R.id.cardio);
        strength=(RadioButton)findViewById(R.id.strength);
        stamina=(RadioButton)findViewById(R.id.stamina);
        muscle=(RadioButton)findViewById(R.id.muscle);
        swimming=(RadioButton)findViewById(R.id.swimming);
        running=(RadioButton)findViewById(R.id.running);
        football=(RadioButton)findViewById(R.id.football);
        rugby=(RadioButton)findViewById(R.id.rugby);
        dance=(RadioButton)findViewById(R.id.dance);

//        qr=(ImageView)findViewById(R.id.qr);
        generateQR=(Button)findViewById(R.id.submit);
        generateQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(userAgeInput.getCheckedRadioButtonId() == -1) ){
                    int ageId=userAgeInput.getCheckedRadioButtonId();
                    RadioButton ageButton=(RadioButton)findViewById(ageId);
                    userAge=ageButton.getText().toString();
                }
                if (!(genderInput.getCheckedRadioButtonId() == -1) ){
                    int genderId=genderInput.getCheckedRadioButtonId();
                    RadioButton genderButton=(RadioButton)findViewById(genderId);
                    gender=genderButton.getText().toString();
                }
                if(!(trainingTypeInput.getCheckedRadioButtonId() == -1)){
                    int trainingId=trainingTypeInput.getCheckedRadioButtonId();
                    RadioButton trainingButton=(RadioButton)findViewById(trainingId);
                    training=trainingButton.getText().toString();
                }

                if(inputString.getText().toString().trim().length()==0 || userAge.isEmpty()
                        || gender.isEmpty() || training.isEmpty() ||
                        !(cardio.isChecked() || strength.isChecked() || stamina.isChecked() ||
                        muscle.isChecked()) || !(swimming.isChecked() || running.isChecked() ||
                        football.isChecked() || rugby.isChecked() || dance.isChecked())){
                    Toast.makeText(Form.this,"Please fill the form",Toast.LENGTH_SHORT).show();
                }else{
                    if((userAge.equalsIgnoreCase("less than 10")|| userAge.equalsIgnoreCase("between 10-15"))
                            || gender.equalsIgnoreCase("Female")){
                        if(training.equalsIgnoreCase("Maui Thai")){
                            a=1;
                        }else if(training.equalsIgnoreCase("Jujutsu")){
                            b=1;
                        }else if(training.equalsIgnoreCase("Karate")){
                            c=1;
                        }else{
                            d=1;
                        }
                    }
                    if((userAge.equalsIgnoreCase("between 16-40")|| userAge.equalsIgnoreCase("greater than 40"))
                            || !(gender.equalsIgnoreCase("Female"))){
                        if(training.equalsIgnoreCase("Maui Thai")){
                            a=2;
                        }else if(training.equalsIgnoreCase("Jujutsu")){
                            b=2;
                        }else if(training.equalsIgnoreCase("Karate")){
                            c=2;
                        }else{
                            d=2;
                        }
                    }
                    if(cardio.isChecked()){
                        p=1;
                    }
                    if(strength.isChecked()){
                        q=1;
                    }
                    if(stamina.isChecked()){
                        r=1;
                    }
                    if(muscle.isChecked()){
                        s=1;
                    }
                    if(swimming.isChecked()){
                        vi=1;
                    }
                    if(running.isChecked()){
                        w=1;
                    }
                    if(football.isChecked()){
                        x=1;
                    }
                    if(rugby.isChecked()){
                        y=1;
                    }
                    if(dance.isChecked()){
                        z=1;
                    }

                    userDetail=Integer.toString(a)+Integer.toString(b)+Integer.toString(c)
                            +Integer.toString(d)+Integer.toString(p)+Integer.toString(q)+Integer.toString(r)
                            +Integer.toString(s)+Integer.toString(vi)+Integer.toString(w)+
                            Integer.toString(x)+Integer.toString(y)+Integer.toString(z);

                    Intent intent=new Intent(Form.this,UserDetail.class);
                    intent.putExtra("userdetail",userDetail);
                    Form.this.startActivity(intent);

//                        try{
//                            bitmap=textToImageEncode(inputString.getText().toString());
//                            qr.setImageBitmap(bitmap);
//                        }catch (WriterException e){
//                            e.printStackTrace();
//                        }
                }
            }
        });
    }



    private Bitmap textToImageEncode(String value) throws WriterException{
        BitMatrix bitMatrix;
        try{

            bitMatrix=new MultiFormatWriter().encode(
                    value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth,QRcodeWidth,null
            );

        }catch (IllegalArgumentException Illegalargumentexception){
            return null;
        }

        int bitMatrixWidth=bitMatrix.getWidth();
        int bitMatrixHeight=bitMatrix.getHeight();

        int[] pixels=new int[bitMatrixWidth*bitMatrixHeight];

        for(int y=0;y<bitMatrixHeight;y++){
            int offset=y*bitMatrixHeight;
            for(int x=0;x<bitMatrixWidth;x++){
                pixels[offset+x]=bitMatrix.get(x,y)?getResources().getColor(R.color.black):getResources().getColor(R.color.white);
            }
        }
        Bitmap bitmap= Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels,0,500,0,0,bitMatrixWidth,bitMatrixHeight);
        return bitmap;

    }

}
