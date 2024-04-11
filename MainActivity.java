// MainActivity.java

package com.example.propetssional;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.propetssional.db.SQLiteControl;
import com.example.propetssional.db.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    SQLiteHelper helper; // 헬퍼 선언
    SQLiteControl sqlite; // 실제로 SQLite를 활용할 Class를 선언합니다.

    EditText etUserId, etUserName, etUserPhone, etUserNick, etUserPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUserId = findViewById(R.id.et_id);
        etUserName = findViewById(R.id.et_name);
        etUserPhone = findViewById(R.id.et_phone_number);
        etUserNick = findViewById(R.id.et_nick_name);
        etUserPass = findViewById(R.id.et_pass);

        // DB를 생성 및 Open합니다.
        helper = new SQLiteHelper(
                MainActivity.this, // context
                "userinfo.db", // DB 파일 이름을 적어주시면 됩니다.
                null, // Factory
                1 // 현재 생성하는 DB의 버전을 설정합니다.
        );

        sqlite = new SQLiteControl(helper);

        // DB 연결
        sqlite.open();
        
        // 회원가입 버튼 클릭 시
        findViewById(R.id.btn_next).setOnClickListener(v -> signUp());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // DB 연결 해제
        sqlite.close();
    }

    private void signUp() {
        String userId = etUserId.getText().toString().trim();
        String userName = etUserName.getText().toString().trim();
        String userPhone = etUserPhone.getText().toString().trim();
        String userNick = etUserNick.getText().toString().trim();
        String userPass = etUserPass.getText().toString().trim();

        // 회원가입 정보를 데이터베이스에 삽입
        long result = sqlite.insert(userId, userName, userPhone, userNick, userPass);
        if (result != -1) {
            Log.d("SignUp", "회원가입 성공");
        } else {
            Log.e("SignUp", "회원가입 실패");
        }
    }
}
