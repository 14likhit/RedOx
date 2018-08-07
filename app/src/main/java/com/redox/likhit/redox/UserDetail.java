package com.redox.likhit.redox;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.encoder.QRCode;

public class UserDetail extends AppCompatActivity {

    private static final int QRcodeWidth=500;

    private TextView qrString;
    private ImageView qrCode;
    private String userDetail;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Bundle userInfo=getIntent().getExtras();
        if(userInfo==null){
            return;
        }
        userDetail=userInfo.getString("userdetail");

        qrString=(TextView)findViewById(R.id.qrstring);
        qrString.setText(userDetail);
        qrCode=(ImageView)findViewById(R.id.qrCode);

        try{
            bitmap=textToImageEncode(userDetail);
            qrCode.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }

    }

    private Bitmap textToImageEncode(String value) throws WriterException {
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
