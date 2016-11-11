package safebox.yiye.com.fortest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import safebox.yiye.com.fortest.qrCode.QrCodeActivity;
import safebox.yiye.com.fortest.tanMo.TanmoActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button erweima;
    private Button tanmo;
    private Button qrerweima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tanmo = (Button) findViewById(R.id.button1);
        erweima = (Button) findViewById(R.id.button2);
        qrerweima = (Button) findViewById(R.id.button3);

        tanmo.setOnClickListener(this);
        erweima.setOnClickListener(this);
        qrerweima.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent = new Intent(MainActivity.this, TanmoActivity.class);
                startActivity(intent);

                break;

            case R.id.button2:

                Intent intent2 = new Intent(MainActivity.this, QrCodeActivity.class);
                startActivity(intent2);

                break;
            case R.id.button3:

                break;
        }
    }
}
